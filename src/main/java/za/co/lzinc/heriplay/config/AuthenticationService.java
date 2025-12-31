package za.co.lzinc.heriplay.config;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import za.co.lzinc.heriplay.domain.Name;
import za.co.lzinc.heriplay.domain.authentication.Role;
import za.co.lzinc.heriplay.domain.authentication.User;
import za.co.lzinc.heriplay.dto.authentication.AuthenticationRequest;
import za.co.lzinc.heriplay.dto.authentication.LoginRequestDTO;
import za.co.lzinc.heriplay.dto.authentication.RegisterRequestDTO;
import za.co.lzinc.heriplay.dto.authentication.UserDTO;
import za.co.lzinc.heriplay.mapper.authentication.UserMapper;
import za.co.lzinc.heriplay.repository.authentication.AuthenticationRepository;
import za.co.lzinc.heriplay.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationRequest register(RegisterRequestDTO request) {
        Name name = request.name() != null ? request.name() : new Name("DefaultFirstName", "DefaultLastName"); // Ensure name is not null
        String userName = request.userName() != null && !request.userName().trim().isEmpty() ? request.userName().trim() : null;

        if (userName != null && authenticationRepository.findByUserName(userName).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role role = request.role() != null ? request.role() : Role.USER; // Assign default role if null

        User user = new User.Builder()
                .setName(name) // Optional name
                .setUserName(userName) // Optional username
                .setEmail(request.email())
                .setPassword(passwordEncoder.encode(request.password()))
                .setRole(role)
                .build();

        authenticationRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationRequest(token, UserMapper.toDTO(user));
    }

    public AuthenticationRequest authenticate(LoginRequestDTO request) {
        try {
            // Authenticate with Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(), // Use email if userName is null
                            request.password()));

            // If authentication successful, load user from database
            User user = authenticationRepository.findByEmail(request.email())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Create Spring Security UserDetails with authorities
            UserDetails userDetails = createUserDetails(user);

            // Generate JWT token with UserDetails (which includes authorities)
            String jwtToken = jwtService.generateToken(userDetails);

            UserDTO userDTO = UserMapper.toDTO(user);
            return new AuthenticationRequest(jwtToken, userDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Incorrect email or password");
        }
    }

    // Helper method to convert User to Spring Security UserDetails with authorities
    private UserDetails createUserDetails(User user) {
        // Convert user role to Spring Security authority with ROLE_ prefix
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // <-- Use userName, not email
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}

package za.co.lzinc.heriplay.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long JWT_EXPIRATION;

    public String extractUsername(String jwtToken) {
        //This method extracts the username (subject) from the JWT token
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of("role", userDetails.getAuthorities().iterator().next().getAuthority()), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
    
        return Jwts
                .builder()
                .claims(extraClaims)  // Changed from setClaims
                .subject(userDetails.getUsername())  // Changed from getSubject to subject
                .issuedAt(new Date(System.currentTimeMillis()))  // Changed from setIssuedAt
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))  // Changed from setExpiration
                .signWith(getSigningKey())  // Removed SignatureAlgorithm parameter
                .compact();
    }

    // Method to validate the JWT token against user details

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    // Method to check if the JWT token has expired
    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    // Method to extract the expiration date from the JWT token
    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }


    // Method to extract all claims from the JWT token
    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(jwtToken)
        .getPayload();
    }

    // The secret key is used to sign and verify the JWT tokens
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

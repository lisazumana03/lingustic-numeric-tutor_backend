package za.co.lzinc.heriplay.controller.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import za.co.lzinc.heriplay.domain.quiz.Subject;
import za.co.lzinc.heriplay.service.quiz.impl.SubjectService;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin(origins = {"http://localhost:4516", "http://172.20.10.9:4516"})
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Subject> create(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
            return ResponseEntity.ok(subjectService.readAll());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer subjectId) {
        Subject subject = subjectService.readById(subjectId);
        if (subject != null) {
            return ResponseEntity.ok(subject);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Integer subjectId, @RequestBody Subject subject) {
        Subject updatedSubject = subjectService.update(subjectId, subject);
        if (updatedSubject != null) {
            return ResponseEntity.ok(updatedSubject);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Integer subjectId) {
        subjectService.delete(subjectId);
        return ResponseEntity.noContent().build();
    }
}

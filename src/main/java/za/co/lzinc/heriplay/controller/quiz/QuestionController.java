package za.co.lzinc.heriplay.controller.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import za.co.lzinc.heriplay.domain.quiz.Question;
import za.co.lzinc.heriplay.service.quiz.impl.QuestionService;

@RestController
@RequestMapping("/api/question")
@CrossOrigin(origins = {"http://localhost:4516", "http://172.20.10.9:4516"})
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Question> create(@RequestBody Question question) {
        questionService.create(question);
        return ResponseEntity.ok(question);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Integer id) {
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/delete")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        questionService.update(question);
        return ResponseEntity.ok(question);
    }
}

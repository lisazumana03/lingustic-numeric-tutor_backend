package za.co.lzinc.heriplay.factory.quiz;

import org.junit.jupiter.api.Test;
import za.co.lzinc.heriplay.domain.quiz.Question;
import za.co.lzinc.heriplay.domain.quiz.Quiz;
import za.co.lzinc.heriplay.domain.quiz.Subject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizFactoryTest {

    @Test
    void createQuiz() {
        String id = "MATH01";
        String title = "Math";
        String category = "Math";
        int timeLimit = 5;
        int points = 5;
        String description = "Math";
        Subject subject = new Subject();
        List<Question> questionList = new ArrayList<>();

        Quiz quiz = QuizFactory.createQuiz(id, title, category, timeLimit, points, description, subject, questionList);
        assertNotNull(quiz);
        System.out.println(quiz);

    }
}
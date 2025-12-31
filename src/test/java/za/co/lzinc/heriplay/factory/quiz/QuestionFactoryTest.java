package za.co.lzinc.heriplay.factory.quiz;

import org.junit.jupiter.api.Test;
import za.co.lzinc.heriplay.domain.quiz.Question;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFactoryTest {

    @Test
    void createQuestion() {
        String question = "What is thte number?";
        int points = 5;
        String answer = "A";
        String correctAnswer = "A";

        Question question1 = QuestionFactory.createQuestion(question, points, answer, correctAnswer);
        assertNotNull(question1);
        System.out.println(question1);
    }
}
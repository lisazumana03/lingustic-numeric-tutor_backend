package za.co.lzinc.heriplay.factory.quiz;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import za.co.lzinc.heriplay.domain.quiz.Question;
import za.co.lzinc.heriplay.domain.quiz.Quiz;
import za.co.lzinc.heriplay.domain.quiz.Subject;

import java.util.List;

public class QuizFactory{
    public static Quiz createQuiz(String id, String title, String category,
                                  int timeLimit, int points, String description, Subject subject,
                                  List<Question> questions ){
        return new Quiz.Builder()
                .setId(id)
                .setTitle(title)
                .setCategory(category)
                .setTimeLimit(timeLimit)
                .setPoints(points)
                .setDescription(description)
                .setSubject(subject)
                .setQuestions(questions)
                .build();
    }
}
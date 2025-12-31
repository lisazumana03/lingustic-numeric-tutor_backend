package za.co.lzinc.heriplay.factory.quiz;

import za.co.lzinc.heriplay.domain.quiz.Question;

public class QuestionFactory {
    public static Question createQuestion(String question, int points, String answer, String correctAnswer) {
        return new Question.Builder()
                .setQuestion(question)
                .setPoints(points)
                .setAnswer(answer)
                .setCorrectAnswer(correctAnswer)
                .build();
    }
}

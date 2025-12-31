package za.co.lzinc.heriplay.domain.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Question {
    @Id
    private int questionId;
    private String question;
    private int points;
    private String answer;
    private String correctAnswer;

    public Question() {}

    private Question(Builder builder) {
        this.questionId = builder.questionId;
        this.question = builder.question;
        this.answer = builder.answer;
        this.correctAnswer = builder.correctAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public int getPoints() {
        return points;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", points=" + points +
                ", answer='" + answer + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }

    public static class Builder{
        private int questionId;
        private String question;
        private int points;
        private String answer;
        private String correctAnswer;

        public Builder setQuestionId(int questionId){
            this.questionId = questionId;
            return this;
        }

        public Builder setQuestion(String question){
            this.question = question;
            return this;
        }

        public Builder setPoints(int points){
            this.points = points;
            return this;
        }

        public Builder setAnswer(String answer){
            this.answer = answer;
            return this;
        }

        public Builder setCorrectAnswer(String correctAnswer){
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder copy(Question question){
            this.questionId = question.questionId;
            this.question = question.question;
            this.points = question.points;
            this.answer = question.answer;
            this.correctAnswer = question.correctAnswer;
            return this;
        }

        public Question build(){
            return new Question(this);
        }
    }
}

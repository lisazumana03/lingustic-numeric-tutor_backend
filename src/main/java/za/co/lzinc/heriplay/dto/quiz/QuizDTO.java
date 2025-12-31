package za.co.lzinc.heriplay.dto.quiz;

import za.co.lzinc.heriplay.domain.quiz.Question;
import za.co.lzinc.heriplay.domain.quiz.Quiz;
import za.co.lzinc.heriplay.domain.quiz.Subject;

import java.io.Serializable;
import java.util.List;

public class QuizDTO implements Serializable {
    private String id;
    private String title;
    private String category;
    private int timeLimit; // in minutes
    private int points;
    private String description;
    private Subject subject;
    private List<Question> questions;

    public QuizDTO(){}

    public QuizDTO(Quiz quiz){
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.category = quiz.getCategory();
        this.timeLimit = quiz.getTimeLimit();
        this.points = quiz.getPoints();
        this.description = quiz.getDescription();
        this.subject = quiz.getSubject();
        this.questions = quiz.getQuestions();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

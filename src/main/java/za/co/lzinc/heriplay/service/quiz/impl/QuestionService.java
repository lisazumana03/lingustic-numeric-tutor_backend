package za.co.lzinc.heriplay.service.quiz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.lzinc.heriplay.domain.quiz.Question;
import za.co.lzinc.heriplay.repository.quiz.QuestionRepository;
import za.co.lzinc.heriplay.service.quiz.IQuestionService;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public void delete(int questionId) {
        if(questionRepository.existsById(questionId)) {
            questionRepository.deleteById(questionId);
        }
    }

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> readAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question readById(Integer integer) {
        return questionRepository.findById(integer).orElse(null);
    }

    @Override
    public Question update(Question question) {
        return questionRepository.save(question);
    }
}

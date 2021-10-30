package com.example.JustAsk.service;

import com.example.JustAsk.dao.AnswerDao;
import com.example.JustAsk.dao.QuestionDao;
import com.example.JustAsk.dao.UserDao;
import com.example.JustAsk.model.Answer;
import com.example.JustAsk.model.PostAnswerRequestBody;
import com.example.JustAsk.model.Question;
import com.example.JustAsk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerDao answerDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    UserDao userDao;

    public void addAnswer(PostAnswerRequestBody postAnswerRequestBody, int userID, int questionID) {
        Answer answer = new Answer();
        Question question = questionDao.getQuestionByID(questionID);
        User user = userDao.getUserByID(userID);
        answer.setDescription(postAnswerRequestBody.getSentence());
        answer.setQuestion(question);
        answer.setUser(user);
        answerDao.addAnswer(answer);
    }

    public List<Answer> getAnswersByQuestionID(int questionID) {
        return answerDao.getAnswersByQuestionID(questionID);
    }
}

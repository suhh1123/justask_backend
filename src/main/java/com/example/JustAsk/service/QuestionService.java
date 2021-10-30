package com.example.JustAsk.service;

import com.example.JustAsk.dao.QuestionDao;
import com.example.JustAsk.dao.UserDao;
import com.example.JustAsk.external.MonkeyLearnClient;
import com.example.JustAsk.model.PostQuestionRequestBody;
import com.example.JustAsk.model.Question;
import com.example.JustAsk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MonkeyLearnClient monkeyLearnClient;

    private static final int LATEST_LIMIT = 5;

    public List<Question> getLatestQuestions() {
        List<Question> result = new ArrayList<>();
        List<Question> questions = questionDao.getAllQuestions();
        // Get the top 5 latest questions
        Queue<Question> minHeap = new PriorityQueue<>(new Comparator<Question>() {
            @Override
            public int compare(Question o1, Question o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        for (int i = 0; i < questions.size(); i++) {
            if (i < LATEST_LIMIT) {
                minHeap.offer(questions.get(i));
            } else if (minHeap.peek().getDate().compareTo(questions.get(i).getDate()) < 0) {
                minHeap.poll();
                minHeap.offer(questions.get(i));
            }
        }
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll());
        }
        return result;
    }

    public List<Question> getQuestionsByUserID(int userID) {
        return questionDao.getQuestionsByUserID(userID);
    }

    public List<Question> getQuestionsByKeywords(String sentence) {
        List<Question> questions = questionDao.getAllQuestions();
        List<String> articles = new ArrayList<>();
        articles.add(sentence);
        List<Set<String>> keywordList = monkeyLearnClient.extract(articles);
        // Remote API request and local execution are asyn, wait and make sure the result return
        while (keywordList == null) {
        }

        Set<Question> temp = new HashSet<>();
        for (Question question : questions) {
            for (String keyword : question.getKeywords()) {
                if (keywordList.get(0).contains(keyword)) {
                    temp.add(question);
                }
            }
        }
        List<Question> result = new ArrayList<>(temp);
        return result;
    }

    public void addQuestion(PostQuestionRequestBody request, int userId) {
        User user = userDao.getUserByID(userId);
        if (user == null) {
            return;
        }
        Question question = new Question();
        question.setUser(user);
        question.setDescription(request.getDescription());
        List<String> articles = new ArrayList<>();
        articles.add(request.getDescription());
        List<Set<String>> keywordList = monkeyLearnClient.extract(articles);
        // Remote API request and local execution are asyn, wait and make sure the result return
        while (keywordList == null) {
        }
        question.setKeywords(keywordList.get(0));
        questionDao.addQuestion(question);
    }
}

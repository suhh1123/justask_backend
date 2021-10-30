package com.example.JustAsk.dao;

import com.example.JustAsk.model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserDao userDao;

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT question FROM Question question");
            questions = (List<Question>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return questions;
    }

    public List<Question> getQuestionsByUserID(int userID) {
        List<Question> questions = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT question FROM Question question WHERE question.user.id = :userID ORDER BY question.date DESC");
            query.setParameter("userID", userID);
            questions = (List<Question>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return questions;
    }

    public Question getQuestionByID(int questionID) {
        Session session = sessionFactory.openSession();
        Question question = session.get(Question.class, questionID);
        if (session != null) {
            session.close();
        }
        return question;
    }

    public void addQuestion(Question question) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

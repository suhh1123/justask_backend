package com.example.JustAsk.dao;

import com.example.JustAsk.model.Answer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerDao {

    @Autowired
    SessionFactory sessionFactory;

    public void addAnswer(Answer answer) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(answer);
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

    public List<Answer> getAnswersByQuestionID(int questionID) {
        List<Answer> answers = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT answer FROM Answer answer WHERE answer.question.id = :questionID ORDER BY  answer.date");
            query.setParameter("questionID", questionID);
            answers = (List<Answer>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return answers;
    }
}

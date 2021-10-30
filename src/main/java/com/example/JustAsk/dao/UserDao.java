package com.example.JustAsk.dao;

import com.example.JustAsk.model.LoginResponseBody;
import com.example.JustAsk.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean addUser(User user) {
        boolean status = true;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return status;
    }

    public LoginResponseBody verifyLogin(String username, String password) {
        LoginResponseBody loginResponseBody = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT id, firstname, lastname FROM User WHERE username = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Object[]> result = query.list();
            loginResponseBody = new LoginResponseBody();
            loginResponseBody.setStatus("Fail");
            if (result.size() != 0) {
                loginResponseBody.setUserId((Integer) result.get(0)[0]);
                loginResponseBody.setFirstname((String) result.get(0)[1]);
                loginResponseBody.setLastname((String) result.get(0)[2]);
                loginResponseBody.setStatus("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return loginResponseBody;
    }

    public User getUserByUsername(String username) {
        List<User> users = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT user From User user WHERE user.username = :username");
            query.setParameter("username", username);
            users = (List<User>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users.size() == 0 ? null : users.get(0);
    }

    public User getUserByID(int userID) {
        User user = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            user = session.get(User.class, userID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }
}

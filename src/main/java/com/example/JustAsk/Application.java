package com.example.JustAsk;

import com.example.JustAsk.Log.PaymentAction;
import com.example.JustAsk.model.Question;
import com.example.JustAsk.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("shh1123");
        user.setPassword("suhaohua520");
        user.setFirstname("Haohua");
        user.setLastname("Su");
        session.save(user);

        Question question1 = new Question();
        question1.setDescription("This is question1");
        session.save(question1);
        question1.setUser(user);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Question question2 = new Question();
        question2.setDescription("This is question2");
        question2.setUser(user);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Question question3 = new Question();
        question3.setDescription("This is question3");
        question3.setUser(user);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.save(question1);
        session.save(question2);
        session.save(question3);

        session.getTransaction().commit();
        session.close();
    }
}

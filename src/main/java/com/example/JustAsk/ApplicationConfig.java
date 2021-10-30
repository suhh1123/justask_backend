package com.example.JustAsk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@EnableWebMvc
public class ApplicationConfig {

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.JustAsk.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/JustAskTest?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("suhaohua520");
//        dataSource.setUrl("jdbc:mysql://justask-instance.cpes1g4l3sdp.us-east-2.rds.amazonaws.com:3306/justask?createDatabaseIfNotExist=true&serverTimezone=UTC");
//        dataSource.setUsername("admin");
//        dataSource.setPassword("suhaohua520");
        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop"); // "create-drop" indicates each time we reboot the server, the database will be cleaned up
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        //hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialec");
        return hibernateProperties;
    }
}

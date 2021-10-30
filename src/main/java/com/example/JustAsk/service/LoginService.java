package com.example.JustAsk.service;

import com.example.JustAsk.dao.UserDao;
import com.example.JustAsk.model.LoginRequestBody;
import com.example.JustAsk.model.LoginResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserDao userDao;

    public LoginResponseBody login(LoginRequestBody requestBody) {
        return userDao.verifyLogin(requestBody.getUsername(), requestBody.getPassword());
    }
}

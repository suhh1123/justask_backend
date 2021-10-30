package com.example.JustAsk.service;

import com.example.JustAsk.dao.UserDao;
import com.example.JustAsk.model.RegistrationRequestBody;
import com.example.JustAsk.model.ResultResponse;
import com.example.JustAsk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationService {

    @Autowired
    UserDao userDao;

    public ResultResponse register(RegistrationRequestBody requestBody) {
        User user = userDao.getUserByUsername(requestBody.getUsername());
        ResultResponse resultResponse = new ResultResponse();
        if (user != null) {
            resultResponse.setStatus("Fail");
        } else {
            user = new User();
            user.setUsername(requestBody.getUsername());
            user.setPassword(requestBody.getPassword());
            user.setFirstname(requestBody.getFirstname());
            user.setLastname(requestBody.getLastname());
            boolean status = userDao.addUser(user);
            if (status) {
                resultResponse.setStatus("Success");
            } else {
                resultResponse.setStatus("Fail");
            }
        }
        return resultResponse;
    }
}

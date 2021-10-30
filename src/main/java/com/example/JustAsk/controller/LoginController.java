package com.example.JustAsk.controller;

import com.example.JustAsk.model.LoginRequestBody;
import com.example.JustAsk.model.LoginResponseBody;
import com.example.JustAsk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService service;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponseBody login(@RequestBody LoginRequestBody requestBody) {
        LoginResponseBody responseBody = service.login(requestBody);
        return responseBody;
    }
}

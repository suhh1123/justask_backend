package com.example.JustAsk.controller;

import com.example.JustAsk.model.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse logout() {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setStatus("Success");
        return resultResponse;
    }
}

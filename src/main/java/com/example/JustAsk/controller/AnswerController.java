package com.example.JustAsk.controller;

import com.example.JustAsk.model.Answer;
import com.example.JustAsk.model.PostAnswerRequestBody;
import com.example.JustAsk.model.ResultResponse;
import com.example.JustAsk.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin
public class AnswerController {

    @Autowired
    AnswerService service;

    @RequestMapping(value = "/post_answer", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse postAnswer(@RequestBody PostAnswerRequestBody requestBody, @RequestParam("userID") int userID, @RequestParam("questionID") int questionID) {
        ResultResponse response = new ResultResponse();
        service.addAnswer(requestBody, userID, questionID);
        response.setStatus("Success");
        return response;
    }

    @RequestMapping(value = "/fetch_answers", method = RequestMethod.GET)
    @ResponseBody
    public List<Answer> getAnswersByQuestionID(@RequestParam("questionID") int questionID) {
        return service.getAnswersByQuestionID(questionID);
    }
}

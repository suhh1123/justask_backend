package com.example.JustAsk.controller;

import com.example.JustAsk.model.PostQuestionRequestBody;
import com.example.JustAsk.model.Question;
import com.example.JustAsk.model.ResultResponse;
import com.example.JustAsk.model.SearchQuestionRequestBody;
import com.example.JustAsk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin
public class QuestionController {

    @Autowired
    QuestionService service;

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getLatestQuestions() {
        return service.getLatestQuestions();
    }

    @RequestMapping(value = "/post_question", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse postQuestion(@RequestBody PostQuestionRequestBody requestBody, @RequestParam("userID") int userID) {
        ResultResponse response = new ResultResponse();
        service.addQuestion(requestBody, userID);
        response.setStatus("Success");
        return response;
    }

    @RequestMapping(value = "/fetch_questions", method = RequestMethod.GET)
    @ResponseBody
    public List<Question> getQuestionsByUserID(@RequestParam("userID") int userID) {
        return service.getQuestionsByUserID(userID);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<Question> searchQuestion(@RequestBody SearchQuestionRequestBody requestBody) {
        return service.getQuestionsByKeywords(requestBody.getSentence());
    }
}

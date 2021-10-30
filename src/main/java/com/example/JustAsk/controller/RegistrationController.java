package com.example.JustAsk.controller;

import com.example.JustAsk.model.RegistrationRequestBody;
import com.example.JustAsk.model.ResultResponse;
import com.example.JustAsk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class RegistrationController {

    @Autowired
    RegistrationService service;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse register(@RequestBody RegistrationRequestBody requestBody) {
        // TODO: tackle this in the service layer
        return service.register(requestBody);
    }
}

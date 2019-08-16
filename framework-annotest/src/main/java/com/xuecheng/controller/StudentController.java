package com.xuecheng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {

    @ResponseBody
    @RequestMapping("/abc")
    public String abc(){
        return "abc";
    }
}

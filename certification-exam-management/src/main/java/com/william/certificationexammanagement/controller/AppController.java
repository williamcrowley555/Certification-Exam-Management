package com.william.certificationexammanagement.controller;

import com.william.certificationexammanagement.service.ExamCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private ExamCourseService examCourseService;

    @GetMapping("")
    private String viewHomePage(Model model) {


        return "index";
    }
}

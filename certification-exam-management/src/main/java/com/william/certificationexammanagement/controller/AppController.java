package com.william.certificationexammanagement.controller;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.service.ExamCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AppController {

    @Autowired
    private ExamCourseService examCourseService;

    @GetMapping("")
    private String viewHomePage(Model model) {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        List<ExamCourse> examCourseList = examCourseService.getExamCourseByMonthStartOn(currentMonth, currentYear);
//        Get first 6 exam courses
        List<ExamCourse> examCourses = examCourseList.stream().limit(6).collect(Collectors.toList());

        model.addAttribute("examCourses", examCourses);

        return "index";
    }
}

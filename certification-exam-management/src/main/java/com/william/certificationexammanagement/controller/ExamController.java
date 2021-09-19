package com.william.certificationexammanagement.controller;

import com.william.certificationexammanagement.model.Exam;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.service.ExamService;
import com.william.certificationexammanagement.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamineService examineService;

    @GetMapping("")
    public String showExamByExamineId(Model model, @RequestParam(value = "examineId", required = false, defaultValue = "") String examineId) {
        Examine currentExamine = null;
        List<Exam> exams = null;

        if (!examineId.isEmpty()) {
            currentExamine = examineService.getExamineByExamineId(examineId);
            exams = examService.getExamByExamine(currentExamine);
        }

        model.addAttribute("examineId", examineId);
        model.addAttribute("currentExamine", currentExamine);
        model.addAttribute("exams", exams);

        return "exams";
    }
}

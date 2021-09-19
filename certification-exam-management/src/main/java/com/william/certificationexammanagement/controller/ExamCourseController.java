package com.william.certificationexammanagement.controller;

import com.william.certificationexammanagement.model.EnglishLevel;
import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.service.EnglishLevelService;
import com.william.certificationexammanagement.service.ExamCourseService;
import com.william.certificationexammanagement.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "exam-courses")
public class ExamCourseController {

    @Autowired
    private ExamCourseService examCourseService;

    @Autowired
    private EnglishLevelService englishLevelService;

    @Autowired
    private ExamineService examineService;

    @GetMapping("")
    public String showExamCourses() {
        List<EnglishLevel> englishLevelList = englishLevelService.getEnglishLevels();
        EnglishLevel firstEnglishLevel = englishLevelList.get(0);

        return "redirect:/exam-courses/english-level/" + firstEnglishLevel.getId();
    }

    @GetMapping("/english-level/{englishLevelId}")
    public String showExamCoursesByEnglishLevel(Model model, @PathVariable("englishLevelId") Long englishLevelId) {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        List<EnglishLevel> englishLevels = englishLevelService.getEnglishLevels();

        EnglishLevel currentEnglishLevel = englishLevelService.getEnglishLevelById(englishLevelId);
        List<ExamCourse> examCourses = examCourseService.getExamCourseByEnglishLevelAndMonthStartOn(currentEnglishLevel, currentMonth, currentYear);

        model.addAttribute("englishLevels", englishLevels);
        model.addAttribute("currentEnglishLevel", currentEnglishLevel);
        model.addAttribute("examCourses", examCourses);

        return "exam_courses";
    }

    @GetMapping("/enroll/{examCourseId}")
    public String showExamineEnrollForm(Model model, @PathVariable("examCourseId") Long examCourseId) {
        Examine examine = new Examine();

        model.addAttribute("examCourseId", examCourseId);
        model.addAttribute("examine", examine);

        return "course_enroll";
    }

    @PostMapping("/enroll/{examCourseId}")
    public String addExamineToExamCourse(Model model, @PathVariable("examCourseId") Long examCourseId,
                                         @Valid @ModelAttribute("examine") Examine examine, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "course_enroll";
        }

        ExamCourse examCourse = examCourseService.getExamCourseById(examCourseId);
        Set<Examine> examineList = examCourse.getExamines();

        Examine newExamine = null;
        Examine existingExamine = examineService.getExamineByPhone(examine.getPhone());

        if (existingExamine != null) {
            examine.setId(existingExamine.getId());
            examine.setExamineId(existingExamine.getExamineId());
            examine.setStatus(existingExamine.getStatus());
            newExamine = examineService.updateExamine(examine);
        } else {
            newExamine = examineService.createExamine(examine);
        }

        examineList.add(newExamine);
        examCourse.setExamines(examineList);
        examCourseService.updateExamCourse(examCourse);
        System.out.println(newExamine);

        return "redirect:/exam-courses/enroll/" + examCourseId + "?success";
    }
}

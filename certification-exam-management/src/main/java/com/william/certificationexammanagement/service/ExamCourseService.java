package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.EnglishLevel;
import com.william.certificationexammanagement.model.ExamCourse;

import java.util.List;

public interface ExamCourseService {
    List<ExamCourse> getExamCourses();
    List<ExamCourse> getExamCourseByMonthStartOn(Integer month, Integer year);
    List<ExamCourse> getExamCourseByEnglishLevelAndMonthStartOn(EnglishLevel englishLevel, Integer month, Integer year);
    ExamCourse getExamCourseById(Long id);
    ExamCourse updateExamCourse(ExamCourse examCourse);
}

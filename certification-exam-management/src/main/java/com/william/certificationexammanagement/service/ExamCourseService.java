package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.ExamCourse;

import java.util.List;

public interface ExamCourseService {
    List<ExamCourse> getExamCourses();
    ExamCourse getExamCourseById(Long id);
    ExamCourse updateExamCourse(ExamCourse examCourse);
}

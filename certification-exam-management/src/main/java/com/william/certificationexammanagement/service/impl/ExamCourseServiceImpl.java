package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.repository.ExamCourseRepository;
import com.william.certificationexammanagement.service.ExamCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamCourseServiceImpl implements ExamCourseService {

    @Autowired
    private ExamCourseRepository examCourseRepository;

    @Override
    public List<ExamCourse> getExamCourses() {
        return examCourseRepository.findAll();
    }

    @Override
    public ExamCourse getExamCourseById(Long id) {
        ExamCourse examCourse = null;
        Optional<ExamCourse> optional = examCourseRepository.findById(id);
        if (optional.isPresent()) {
            examCourse = optional.get();
        } else {
            throw new RuntimeException("Exam Course ID: " + id + " does not exist");
        }
        return examCourse;
    }

    @Override
    public ExamCourse updateExamCourse(ExamCourse examCourse) {
        return examCourseRepository.save(examCourse);
    }
}

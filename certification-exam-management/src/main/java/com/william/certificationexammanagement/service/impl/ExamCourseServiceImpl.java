package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.EnglishLevel;
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
    public List<ExamCourse> getExamCourseByMonthStartOn(Integer month, Integer year) {
        List<ExamCourse> examCourses = (List<ExamCourse>) examCourseRepository.findByMonthGreaterThanEqualAndYearGreaterThanEqual(month, year);

        if (examCourses.isEmpty() || examCourses == null) {
            return null;
        }

        return examCourses;
    }

    @Override
    public List<ExamCourse> getExamCourseByEnglishLevelAndMonthStartOn(EnglishLevel englishLevel, Integer month, Integer year) {
        List<ExamCourse> examCourses = (List<ExamCourse>) examCourseRepository.findByEnglishLevelAndMonthGreaterThanEqualAndYearGreaterThanEqual(englishLevel, month, year);

        if (examCourses.isEmpty() || examCourses == null) {
            return null;
        }

        return examCourses;
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

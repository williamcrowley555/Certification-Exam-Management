/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IEnglishLevelBLL;
import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.dal.IExamCourseDAL;
import com.certification_exam.dal.impl.ExamCourseDAL;
import com.certification_exam.dto.EnglishLevel;
import com.certification_exam.dto.ExamCourse;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamCourseBLL implements IExamCourseBLL {
    
    private IExamCourseDAL examCourseDAL;
    private IEnglishLevelBLL englishLevelBLL;

    public ExamCourseBLL() {
        this.examCourseDAL = new ExamCourseDAL();
        this.englishLevelBLL = new EnglishLevelBLL();
    }

    @Override
    public List<ExamCourse> findAll() {
        return examCourseDAL.findAll();
    }

    @Override
    public ExamCourse findById(Long id) {
        return examCourseDAL.findById(id);
    }

    @Override
    public ExamCourse findByMonthAndYearAndEnglishLevelId(Integer month, Integer year, Long englishLevelId) {
        return examCourseDAL.findByMonthAndYearAndEnglishLevelId(month, year, englishLevelId);
    }

    @Override
    public Long save(ExamCourse examCourse) {
        Long savedExamCourseId = null;
        Integer month = examCourse.getMonth();
        Integer year = examCourse.getYear();
        Long englishLevelId = examCourse.getEnglishLevelId();
        ExamCourse existedExamCourse = findByMonthAndYearAndEnglishLevelId(month, year, englishLevelId);
        
        if (existedExamCourse == null) {
            EnglishLevel englishLevel = englishLevelBLL.findById(englishLevelId);
            if (englishLevel != null) {
                String englishLevelName = englishLevel.getName();
            
                examCourse.setName(generateName(englishLevelName, month, year));
                savedExamCourseId = examCourseDAL.save(examCourse);
            }
        }
        return savedExamCourseId;
    }

    @Override
    public void update(ExamCourse examCourse) {
        Integer month = examCourse.getMonth();
        Integer year = examCourse.getYear();
        Long englishLevelId = examCourse.getEnglishLevelId();
        ExamCourse existedExamCourse = findByMonthAndYearAndEnglishLevelId(month, year, englishLevelId);
        
        if (existedExamCourse == null) {
            EnglishLevel englishLevel = englishLevelBLL.findById(englishLevelId);
            if (englishLevel != null) {
                String englishLevelName = englishLevel.getName();
            
                examCourse.setName(generateName(englishLevelName, month, year));
                examCourseDAL.update(examCourse);
            }
        }
    }

    @Override
    public void delete(Long id) {
        examCourseDAL.delete(id);
    } 
    
    private String generateName(String englishLevelName, Integer month, Integer year) {
        String strMonth = month > 9 ? String.valueOf(month) : "0" + month;
        return englishLevelName + "K" + strMonth + year;
    }
}

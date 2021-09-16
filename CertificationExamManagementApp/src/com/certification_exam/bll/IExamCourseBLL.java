/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.ExamCourse;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamCourseBLL {
    List<ExamCourse> findAll();
    ExamCourse findById(Long id);
    ExamCourse findByMonthAndYearAndEnglishLevelId(Integer month, Integer year, Long englishLevelId);
    Long save(ExamCourse examCourse);
    void update(ExamCourse examCourse);
    void delete(Long id);
}

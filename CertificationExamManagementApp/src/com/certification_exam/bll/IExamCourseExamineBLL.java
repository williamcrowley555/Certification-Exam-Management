/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.ExamCourseExamine;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamCourseExamineBLL {
    List<ExamCourseExamine> findAll();
    List<ExamCourseExamine> findByExamCourseId(Long examCourseId);
    ExamCourseExamine findByExamCourseIdAndExamineId(Long examCourseId, Long examineId);
    void save(ExamCourseExamine examCourseExamine);
    void deleteByExamCourseIdAndExamineId(Long examCourseId, Long examineId);
}

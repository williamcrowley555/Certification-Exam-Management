/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.ExamCourseExamine;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamCourseExamineDAL extends GenericDAL<ExamCourseExamine> {
    
    List<ExamCourseExamine> findAll();
    ExamCourseExamine findByExamCourseIdAndExamineId(Long examCourseId, Long examineId);
    Long save(ExamCourseExamine examCourseExamine);
    void deleteByExamCourseIdAndExamineId(Long examCourseId, Long examineId);
}

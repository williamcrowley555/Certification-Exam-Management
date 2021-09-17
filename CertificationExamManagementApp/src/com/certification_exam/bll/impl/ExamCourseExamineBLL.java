/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamCourseExamineBLL;
import com.certification_exam.dal.IExamCourseExamineDAL;
import com.certification_exam.dal.impl.ExamCourseExamineDAL;
import com.certification_exam.dto.ExamCourseExamine;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamCourseExamineBLL implements IExamCourseExamineBLL {
    
    private IExamCourseExamineDAL examCourseExamineDAL;

    public ExamCourseExamineBLL() {
        this.examCourseExamineDAL = new ExamCourseExamineDAL();
    }

    @Override
    public List<ExamCourseExamine> findAll() {
        return examCourseExamineDAL.findAll();
    }

    @Override
    public List<ExamCourseExamine> findByExamCourseId(Long examCourseId) {
        return examCourseExamineDAL.findByExamCourseId(examCourseId);
    }

    @Override
    public ExamCourseExamine findByExamCourseIdAndExamineId(Long examCourseId, Long examineId) {
        return examCourseExamineDAL.findByExamCourseIdAndExamineId(examCourseId, examineId);
    }

    @Override
    public Long save(ExamCourseExamine examCourseExamine) {
        return examCourseExamineDAL.save(examCourseExamine);
    }

    @Override
    public void deleteByExamCourseIdAndExamineId(Long examCourseId, Long examineId) {
        examCourseExamineDAL.deleteByExamCourseIdAndExamineId(examCourseId,examineId);
    } 
}

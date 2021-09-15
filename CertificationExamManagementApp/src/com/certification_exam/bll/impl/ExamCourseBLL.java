/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.dal.IExamCourseDAL;
import com.certification_exam.dal.impl.ExamCourseDAL;
import com.certification_exam.dto.ExamCourse;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamCourseBLL implements IExamCourseBLL {
    
    private IExamCourseDAL examCourseDAL;

    public ExamCourseBLL() {
        this.examCourseDAL = new ExamCourseDAL();
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
    public Long save(ExamCourse examCourse) {
        return examCourseDAL.save(examCourse);
    }

    @Override
    public void update(ExamCourse examCourse) {
        examCourseDAL.update(examCourse);
    }

    @Override
    public void delete(Long id) {
        examCourseDAL.delete(id);
    } 
}

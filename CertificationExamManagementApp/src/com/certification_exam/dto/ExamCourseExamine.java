/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dto;

/**
 *
 * @author HP
 */
public class ExamCourseExamine {
    private Long examCourseId;
    private Long examineId;

    public ExamCourseExamine() {
    }

    public ExamCourseExamine(Long examCourseId, Long examineId) {
        this.examCourseId = examCourseId;
        this.examineId = examineId;
    }

    public Long getExamCourseId() {
        return examCourseId;
    }

    public void setExamCourseId(Long examCourseId) {
        this.examCourseId = examCourseId;
    }

    public Long getExamineId() {
        return examineId;
    }

    public void setExamineId(Long examineId) {
        this.examineId = examineId;
    }

    @Override
    public String toString() {
        return "ExamCourseExamine{" + "examCourseId=" + examCourseId + ", examineId=" + examineId + '}';
    }
    
}

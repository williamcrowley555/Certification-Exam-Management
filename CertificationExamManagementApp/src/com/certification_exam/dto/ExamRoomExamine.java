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
public class ExamRoomExamine {
    private Long examRoomId;
    private Long examineId;

    public ExamRoomExamine() {
    }

    public ExamRoomExamine(Long examRoomId, Long examineId) {
        this.examRoomId = examRoomId;
        this.examineId = examineId;
    }

    public Long getExamRoomId() {
        return examRoomId;
    }

    public void setExamRoomId(Long examRoomId) {
        this.examRoomId = examRoomId;
    }

    public Long getExamineId() {
        return examineId;
    }

    public void setExamineId(Long examineId) {
        this.examineId = examineId;
    }

    @Override
    public String toString() {
        return "ExamRoomExamine{" + "examRoomId=" + examRoomId + ", examineId=" + examineId + '}';
    }
}

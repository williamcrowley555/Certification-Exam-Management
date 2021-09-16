/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dto;

import java.util.Date;

/**
 *
 * @author HP
 */
public class ExamRoom {
    private Long id;
    private String name;
    private int quantity = 0;
    private Date examDate;
    private Long proctorId;
    private Long examinerId;
    private Long examCourseId;

    public ExamRoom() {
    }

    public ExamRoom(Long id, String name, Date examDate, Long proctorId, Long examinerId, Long examCourseId) {
        this.id = id;
        this.name = name;
        this.examDate = examDate;
        this.proctorId = proctorId;
        this.examinerId = examinerId;
        this.examCourseId = examCourseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Long getProctorId() {
        return proctorId;
    }

    public void setProctorId(Long proctorId) {
        this.proctorId = proctorId;
    }

    public Long getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(Long examinerId) {
        this.examinerId = examinerId;
    }

    public Long getExamCourseId() {
        return examCourseId;
    }

    public void setExamCourseId(Long examCourseId) {
        this.examCourseId = examCourseId;
    }

    @Override
    public String toString() {
        return "ExamRoom{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", examDate=" + examDate + ", proctorId=" + proctorId + ", examinerId=" + examinerId + ", examCourseId=" + examCourseId + '}';
    }
}

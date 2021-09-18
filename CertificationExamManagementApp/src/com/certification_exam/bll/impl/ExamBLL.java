/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamBLL;
import com.certification_exam.dal.IExamDAL;
import com.certification_exam.dal.impl.ExamDAL;
import com.certification_exam.dto.Exam;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamBLL implements IExamBLL {
    
    private IExamDAL examDAL;

    public ExamBLL() {
        this.examDAL = new ExamDAL();
    }

    @Override
    public List<Exam> findAll() {
        return examDAL.findAll();
    }

    @Override
    public Exam findById(Long id) {
        return examDAL.findById(id);
    }

    @Override
    public Exam findByExamineIdAndExamRoomId(Long examineId, Long examRoomId) {
        return examDAL.findByExamineIdAndExamRoomId(examineId, examRoomId);
    }

    @Override
    public Long save(Exam exam) {
        return examDAL.save(exam);
    }

    @Override
    public void update(Exam exam) {
        examDAL.update(exam);
    }

    @Override
    public void delete(Long id) {
        examDAL.delete(id);
    } 

    // Get exam if it already exists and create if it does not exist
    @Override
    public Exam getAndCreate(Long examineId, Long examRoomId) {
        Exam exam = findByExamineIdAndExamRoomId(examineId, examRoomId);
        
        if (exam == null) {
            Exam newExam = new Exam();
            newExam.setExamineId(examineId);
            newExam.setExamRoomId(examRoomId);
            Long savedId = save(newExam);
            exam = findById(savedId);
        }
        
        return exam;
    }

    @Override
    public void grade(Exam exam) {
        if (exam.getStatus() == 1) {
            exam.setStatus(2);
        }
        
        update(exam);
    }
}

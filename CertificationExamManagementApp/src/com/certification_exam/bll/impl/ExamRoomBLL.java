/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.dal.IExamRoomDAL;
import com.certification_exam.dal.impl.ExamRoomDAL;
import com.certification_exam.dto.ExamRoom;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamRoomBLL implements IExamRoomBLL {
    
    private IExamRoomDAL examRoomDAL;

    public ExamRoomBLL() {
        this.examRoomDAL = new ExamRoomDAL();
    }

    @Override
    public List<ExamRoom> findAll() {
        return examRoomDAL.findAll();
    }

    @Override
    public ExamRoom findById(Long id) {
        return examRoomDAL.findById(id);
    }

    @Override
    public Long save(ExamRoom examRoom) {
        return examRoomDAL.save(examRoom);
    }

    @Override
    public void update(ExamRoom examRoom) {
        examRoomDAL.update(examRoom);
    }

    @Override
    public void delete(Long id) {
        examRoomDAL.delete(id);
    }
    
}

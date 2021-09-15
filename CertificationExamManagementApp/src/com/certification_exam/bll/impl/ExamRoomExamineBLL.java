/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamRoomExamineBLL;
import com.certification_exam.dal.IExamRoomExamineDAL;
import com.certification_exam.dal.impl.ExamRoomExamineDAL;
import com.certification_exam.dto.ExamRoomExamine;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamRoomExamineBLL implements IExamRoomExamineBLL {
    
    private IExamRoomExamineDAL examRoomExamineDAL;

    public ExamRoomExamineBLL() {
        this.examRoomExamineDAL = new ExamRoomExamineDAL();
    }

    @Override
    public List<ExamRoomExamine> findAll() {
        return examRoomExamineDAL.findAll();
    }

    @Override
    public ExamRoomExamine findByExamRoomIdAndExamineId(Long examRoomId, Long examineId) {
        return examRoomExamineDAL.findByExamRoomIdAndExamineId(examRoomId, examineId);
    }

    @Override
    public Long save(ExamRoomExamine examRoomExamine) {
        return examRoomExamineDAL.save(examRoomExamine);
    }

    @Override
    public void deleteByExamRoomIdAndExamineId(Long examRoomId, Long examineId) {
        examRoomExamineDAL.deleteByExamRoomIdAndExamineId(examRoomId, examineId);
    }
}

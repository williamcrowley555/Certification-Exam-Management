/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.bll.IExamRoomExamineBLL;
import com.certification_exam.bll.IExamineBLL;
import com.certification_exam.dal.IExamRoomExamineDAL;
import com.certification_exam.dal.impl.ExamRoomExamineDAL;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.dto.ExamRoom;
import com.certification_exam.dto.ExamRoomExamine;
import com.certification_exam.dto.Examine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamRoomExamineBLL implements IExamRoomExamineBLL {
    
    private IExamRoomExamineDAL examRoomExamineDAL;
    private IExamRoomBLL examRoomBLL;
    private IExamCourseBLL examCourseBLL;
    private IExamineBLL examineBLL;

    public ExamRoomExamineBLL() {
        this.examRoomExamineDAL = new ExamRoomExamineDAL();
        this.examRoomBLL = new ExamRoomBLL();
        this.examCourseBLL = new ExamCourseBLL();
        this.examineBLL = new ExamineBLL();
    }

    @Override
    public List<ExamRoomExamine> findAll() {
        return examRoomExamineDAL.findAll();
    }

    @Override
    public List<ExamRoomExamine> findByExamRoomId(Long examRoomId) {
        return examRoomExamineDAL.findByExamRoomId(examRoomId);
    }

    @Override
    public ExamRoomExamine findByExamRoomIdAndExamineId(Long examRoomId, Long examineId) {
        return examRoomExamineDAL.findByExamRoomIdAndExamineId(examRoomId, examineId);
    }

    @Override
    public void save(ExamRoomExamine examRoomExamine) {
        ExamRoomExamine existedExamRoomExamine = findByExamRoomIdAndExamineId(examRoomExamine.getExamRoomId(), examRoomExamine.getExamineId());
        if (existedExamRoomExamine == null) {
            examRoomExamineDAL.save(examRoomExamine);
            
            Examine examine = examineBLL.findById(examRoomExamine.getExamineId());
            if (examine != null) {
                ExamRoom examRoom = examRoomBLL.findById(examRoomExamine.getExamRoomId());
                ExamCourse examCourse = examCourseBLL.findById(examRoom.getExamCourseId());
                
                examineBLL.updateExamineId(examine, examCourse.getEnglishLevelId());
                examRoom.setQuantity(examRoom.getQuantity() + 1);
                examRoomBLL.update(examRoom);
            }
        }
    }

    @Override
    public void save(List<ExamRoomExamine> examRoomExamines) {
        for (ExamRoomExamine examRoomExamine : examRoomExamines) {
            save(examRoomExamine);
        }
    }

    @Override
    public void deleteByExamRoomIdAndExamineId(Long examRoomId, Long examineId) {
        examRoomExamineDAL.deleteByExamRoomIdAndExamineId(examRoomId, examineId);
        
        ExamRoom examRoom = examRoomBLL.findById(examRoomId);
        examRoom.setQuantity(examRoom.getQuantity() - 1);
        examRoomBLL.update(examRoom);
    }
}

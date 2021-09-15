/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.ExamRoomExamine;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamRoomExamineBLL {
    List<ExamRoomExamine> findAll();
    ExamRoomExamine findByExamRoomIdAndExamineId(Long examRoomId, Long examineId);
    Long save(ExamRoomExamine examRoomExamine);
    void deleteByExamRoomIdAndExamineId(Long examRoomId, Long examineId);
}

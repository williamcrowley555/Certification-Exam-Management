/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.ExamRoomExamine;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamRoomExamineDAL extends GenericDAL<ExamRoomExamine>{
    List<ExamRoomExamine> findAll();
    List<ExamRoomExamine> findByExamRoomId(Long examRoomId);
    ExamRoomExamine findByExamRoomIdAndExamineId(Long examRoomId, Long examineId);
    void save(ExamRoomExamine examRoomExamine);
    void deleteByExamRoomIdAndExamineId(Long examRoomId, Long examineId);
}

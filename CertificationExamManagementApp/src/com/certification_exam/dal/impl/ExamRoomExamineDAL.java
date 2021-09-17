/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IExamRoomExamineDAL;
import com.certification_exam.dto.ExamRoomExamine;
import com.certification_exam.mapper.impl.ExamRoomExamineMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamRoomExamineDAL extends AbstractDAL<ExamRoomExamine> implements IExamRoomExamineDAL{

    @Override
    public List<ExamRoomExamine> findAll() {
        String sql = "SELECT * FROM exam_room_examine";
        return query(sql, new ExamRoomExamineMapper());
    }

    @Override
    public ExamRoomExamine findByExamRoomIdAndExamineId(Long examRoomId, Long examineId) {
        String sql = "SELECT * FROM exam_room_examine WHERE exam_room_id = ? AND examine_id = ?";
        List<ExamRoomExamine> examRoomExamine = query(sql, new ExamRoomExamineMapper(), examRoomId, examineId);
        return examRoomExamine.isEmpty() ? null : examRoomExamine.get(0);
    }

    @Override
    public void save(ExamRoomExamine examRoomExamine) {
        String sql = "INSERT INTO exam_room_examine(exam_room_id, examine_id) VALUES(?, ?)";
        insert(sql, examRoomExamine.getExamRoomId(), examRoomExamine.getExamineId());
    }

    @Override
    public void deleteByExamRoomIdAndExamineId(Long examRoomId, Long examineId) {
        String sql = "DELETE FROM exam_room_examine WHERE exam_room_id = ? AND examine_id = ?";
        update(sql, examRoomId, examineId);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IExamDAL;
import com.certification_exam.dto.Exam;
import com.certification_exam.mapper.impl.ExamMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamDAL extends AbstractDAL<Exam> implements IExamDAL{

    @Override
    public List<Exam> findAll() {
        String sql = "SELECT * FROM exam";
        return query(sql, new ExamMapper());
    }

    @Override
    public Exam findById(Long id) {
        String sql = "SELECT * FROM exam WHERE id = ?";
        List<Exam> exam = query(sql, new ExamMapper(), id);
        return exam.isEmpty() ? null : exam.get(0);
    }

    @Override
    public Long save(Exam exam) {
        String sql = "INSERT INTO exam(listening_grade, speaking_grade, writing_grade, reading_grade, examine_id, exam_room_id) VALUES(?, ?, ?, ?, ?, ?)";
        return insert(sql, exam.getListeningGrade(), exam.getSpeakingGrade(), exam.getWritingGrade(), exam.getReadingGrade(), exam.getExamineId(), exam.getExamRoomId());
    }

    @Override
    public void update(Exam exam) {
        String sql = "UPDATE exam SET listening_grade = ?, speaking_grade = ?, writing_grade = ?, reading_grade = ?, examine_id = ?, exam_room_id = ? WHERE id = ?";
        update(sql, exam.getListeningGrade(), exam.getSpeakingGrade(), exam.getWritingGrade(), exam.getReadingGrade(), exam.getExamineId(), exam.getExamRoomId(), exam.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM exam WHERE id = ?";
        update(sql, id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.Exam;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ExamMapper implements RowMapper<Exam>{

    @Override
    public Exam mapRow(ResultSet rs) {
        try {
            Exam exam = new Exam();
            exam.setId(rs.getLong("id"));
            exam.setExamRoomId(rs.getLong("exam_room_id"));
            exam.setExamineId(rs.getLong("examine_id"));
            exam.setListeningGrade(rs.getInt("listening_grade"));
            exam.setReadingGrade(rs.getInt("reading_grade"));
            exam.setWritingGrade(rs.getInt("writing_grade")); 
            exam.setSpeakingGrade(rs.getInt("speaking_grade"));
            return exam;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

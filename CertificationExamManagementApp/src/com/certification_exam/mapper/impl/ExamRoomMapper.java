/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.ExamRoom;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ExamRoomMapper implements RowMapper<ExamRoom>{

    @Override
    public ExamRoom mapRow(ResultSet rs) {
        try {
            ExamRoom examRoom = new ExamRoom();
            examRoom.setId(rs.getLong("id"));
            examRoom.setName(rs.getString("name"));
            examRoom.setQuantity(rs.getInt("quantity"));
            examRoom.setExamDate(rs.getTimestamp("exam_date"));
            examRoom.setProctorId(rs.getLong("proctor_id"));
            examRoom.setExaminerId(rs.getLong("examiner_id"));
            examRoom.setExamCourseId(rs.getLong("exam_course_id"));
            
            return examRoom;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

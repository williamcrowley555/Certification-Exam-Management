/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.ExamRoomExamine;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ExamRoomExamineMapper implements RowMapper<ExamRoomExamine>{

    @Override
    public ExamRoomExamine mapRow(ResultSet rs) {
        try {
            ExamRoomExamine examRoomExamine = new ExamRoomExamine();
            examRoomExamine.setExamRoomId(rs.getLong("exam_room_id"));
            examRoomExamine.setExamineId(rs.getLong("examine_id"));
            
            return examRoomExamine;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

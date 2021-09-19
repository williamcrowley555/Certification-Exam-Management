/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.ExamCourseExamine;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ExamCourseExamineMapper implements RowMapper<ExamCourseExamine>{

    @Override
    public ExamCourseExamine mapRow(ResultSet rs) {
        try {
            ExamCourseExamine examCourseExamine = new ExamCourseExamine();
            examCourseExamine.setExamineId(rs.getLong("examine_id"));
            examCourseExamine.setExamCourseId(rs.getLong("exam_course_id")); 
           
            return examCourseExamine;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.ExamCourse;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ExamCourseMapper implements RowMapper<ExamCourse>{

    @Override
    public ExamCourse mapRow(ResultSet rs) {
        try {
            ExamCourse examCourse = new ExamCourse();
            examCourse.setId(rs.getLong("id"));
            examCourse.setName(rs.getString("name"));
            examCourse.setMonth(rs.getInt("month"));
            examCourse.setYear(rs.getInt("year"));
            examCourse.setEnglishLevelId(rs.getLong("english_level_id"));
          
            return examCourse;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

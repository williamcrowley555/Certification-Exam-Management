/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.Examine;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ExamineMapper implements RowMapper<Examine>{

    @Override
    public Examine mapRow(ResultSet rs) {
        try {
            Examine examine = new Examine();
            examine.setId(rs.getLong("id"));
            examine.setExamineId(rs.getString("examine_id"));
            examine.setFirstName(rs.getString("first_name"));
            examine.setLastName(rs.getString("last_name"));
            examine.setDob(rs.getDate("dob").toLocalDate());
            examine.setGender(rs.getInt("gender"));
            examine.setAddress(rs.getString("address"));
            examine.setPhone(rs.getString("phone"));
            examine.setStatus(rs.getBoolean("status"));
            
            return examine;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

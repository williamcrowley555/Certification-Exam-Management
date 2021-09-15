/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.Role;
import com.certification_exam.dto.User;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class UserMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDob(rs.getDate("dob").toLocalDate());
            user.setGender(rs.getInt("gender"));
            user.setAddress(rs.getString("address"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            
            return user;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

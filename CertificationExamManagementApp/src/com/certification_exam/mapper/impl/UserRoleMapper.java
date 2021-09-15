/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.UserRole;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class UserRoleMapper implements RowMapper<UserRole>{

    @Override
    public UserRole mapRow(ResultSet rs) {
        try {
            UserRole userRole = new UserRole();
            userRole.setUserId(rs.getLong("user_id"));
            userRole.setRoleId(rs.getLong("role_id"));
            
            return userRole;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}

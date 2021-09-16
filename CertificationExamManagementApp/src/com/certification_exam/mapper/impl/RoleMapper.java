/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.Role;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class RoleMapper implements RowMapper<Role>{

    @Override
    public Role mapRow(ResultSet rs) {
        try {
            Role role = new Role();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("name"));
            role.setNormalizedName(rs.getString("normalized_name"));
            
            return role;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

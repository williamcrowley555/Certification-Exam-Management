/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class IdMapper implements RowMapper<Long> {
    @Override
        public Long mapRow(ResultSet rs) {
            try {
                Long id = null;
                id = (rs.getLong("id"));
                
                return id;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }    
}

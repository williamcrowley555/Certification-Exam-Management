/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.TinhDTO;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class TinhMapper implements RowMapper<TinhDTO> {
    @Override
        public TinhDTO mapRow(ResultSet rs) {
            try {
                TinhDTO tinh = new TinhDTO();
                tinh.setId(rs.getLong("id"));
                tinh.setTenTinh(rs.getString("ten_tinh"));            
                
                return tinh;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }            
}

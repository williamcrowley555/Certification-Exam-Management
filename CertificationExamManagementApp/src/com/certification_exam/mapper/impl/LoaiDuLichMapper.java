/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.LoaiDuLichDTO;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class LoaiDuLichMapper implements RowMapper<LoaiDuLichDTO>{

    @Override
    public LoaiDuLichDTO mapRow(ResultSet rs) {
        try {
            LoaiDuLichDTO loaiDuLich = new LoaiDuLichDTO();
            loaiDuLich.setId(rs.getLong("id"));
            loaiDuLich.setTenLoaiDuLich(rs.getString("ten_loai_du_lich"));
            
            return loaiDuLich;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
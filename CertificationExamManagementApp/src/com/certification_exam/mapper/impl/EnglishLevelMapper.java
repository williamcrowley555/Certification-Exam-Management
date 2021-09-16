/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.mapper.impl;

import com.certification_exam.dto.EnglishLevel;
import com.certification_exam.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class EnglishLevelMapper implements RowMapper<EnglishLevel>{

    @Override
    public EnglishLevel mapRow(ResultSet rs) {
        try {
            EnglishLevel englishLevel = new EnglishLevel();
            englishLevel.setId(rs.getLong("id"));
            englishLevel.setName(rs.getString("name"));
            return englishLevel;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

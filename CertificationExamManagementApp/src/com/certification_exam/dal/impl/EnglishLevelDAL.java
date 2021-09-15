/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IEnglishLevelDAL;
import com.certification_exam.dto.Role;
import com.certification_exam.mapper.impl.RoleMapper;
import java.util.List;
import com.certification_exam.dal.IRoleDAL;
import com.certification_exam.dto.EnglishLevel;
import com.certification_exam.mapper.impl.EnglishLevelMapper;

/**
 *
 * @author HP
 */
public class EnglishLevelDAL extends AbstractDAL<EnglishLevel> implements IEnglishLevelDAL{

    @Override
    public List<EnglishLevel> findAll() {
        String sql = "SELECT * FROM english_level";
        return query(sql, new EnglishLevelMapper());
    }

    @Override
    public EnglishLevel findById(Long id) {
        String sql = "SELECT * FROM english_level WHERE id = ?";
        List<EnglishLevel> englishLevel = query(sql, new EnglishLevelMapper(), id);
        return englishLevel.isEmpty() ? null : englishLevel.get(0);
    }

    @Override
    public Long save(EnglishLevel englishLevel) {
        String sql = "INSERT INTO english_level(name) VALUES(?)";
        return insert(sql, englishLevel.getName());
    }

    @Override
    public void update(EnglishLevel englishLevel) {
        String sql = "UPDATE english_level SET name = ? WHERE id = ?";
        update(sql, englishLevel.getName(), englishLevel.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM english_level WHERE id = ?";
        update(sql, id);
    }
}

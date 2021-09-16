/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IEnglishLevelBLL;
import com.certification_exam.dal.IEnglishLevelDAL;
import com.certification_exam.dal.impl.EnglishLevelDAL;
import com.certification_exam.dto.EnglishLevel;
import java.util.List;

/**
 *
 * @author HP
 */
public class EnglishLevelBLL implements IEnglishLevelBLL {
    
    private IEnglishLevelDAL englishLevelDAL;

    public EnglishLevelBLL() {
        this.englishLevelDAL = new EnglishLevelDAL();
    }

    @Override
    public List<EnglishLevel> findAll() {
        return englishLevelDAL.findAll();
    }

    @Override
    public EnglishLevel findById(Long id) {
        return englishLevelDAL.findById(id);
    }

    @Override
    public Long save(EnglishLevel englishLevel) {
        return englishLevelDAL.save(englishLevel);
    }

    @Override
    public void update(EnglishLevel englishLevel) {
        englishLevelDAL.update(englishLevel);
    }

    @Override
    public void delete(Long id) {
        englishLevelDAL.delete(id);
    } 
}

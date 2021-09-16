/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamineBLL;
import com.certification_exam.dal.IExamineDAL;
import com.certification_exam.dal.impl.ExamineDAL;
import com.certification_exam.dto.Examine;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamineBLL implements IExamineBLL {
    
    private IExamineDAL examineDAL;

    public ExamineBLL() {
        this.examineDAL = new ExamineDAL();
    }

    @Override
    public List<Examine> findAll() {
        return examineDAL.findAll();
    }

    @Override
    public Examine findById(Long id) {
        return examineDAL.findById(id);
    }

    @Override
    public Long save(Examine examine) {
        return examineDAL.save(examine);
    }

    @Override
    public void update(Examine examine) {
        examineDAL.update(examine);
    }

    @Override
    public void delete(Long id) {
        examineDAL.delete(id);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IDoanBLL;
import com.certification_exam.dal.impl.DoanDAL;
import com.certification_exam.dto.DoanDTO;
import java.util.List;
import com.certification_exam.dal.IDoanDAL;

/**
 *
 * @author kossp
 */
public class DoanBLL implements IDoanBLL {
    
    private IDoanDAL doanDAO;
    
    public DoanBLL() {
        this.doanDAO = new DoanDAL();
    }    
    
    @Override
    public List<DoanDTO> findAll() {
        return doanDAO.findAll();
    }

   @Override
    public DoanDTO findById(Long id) {
        return doanDAO.findById(id);
    }
    
    @Override
    public Long save(DoanDTO doan) {
        return doanDAO.save(doan);
    }

    @Override
    public void update(DoanDTO doan) {
        doanDAO.update(doan);
    }

    @Override
    public void updateAmount(Long id, Integer amount) {
        doanDAO.updateAmount(id, amount);
    }

    @Override
    public void delete(Long id) {
        doanDAO.delete(id);
    }

    @Override
    public List<DoanDTO> findByIdTour(Long id) {
       return doanDAO.findByIdTour(id);
    }
}

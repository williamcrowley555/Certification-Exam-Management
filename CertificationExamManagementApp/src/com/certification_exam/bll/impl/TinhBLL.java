/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.ITinhBLL;
import com.certification_exam.dal.impl.TinhDAL;
import com.certification_exam.dto.TinhDTO;
import java.util.List;
import com.certification_exam.dal.ITinhDAL;

/**
 *
 * @author kossp
 */
public class TinhBLL implements ITinhBLL {    
    private ITinhDAL tinhDAO;
    
    public TinhBLL() {
        this.tinhDAO = new TinhDAL();
    }    
    
    @Override
    public List<TinhDTO> findAll() {
        return tinhDAO.findAll();
    }

   @Override
    public TinhDTO findById(Long id) {
        return tinhDAO.findById(id);
    }          
    
    @Override
    public Long save(TinhDTO tinh) {
        return tinhDAO.save(tinh);
    }

    @Override
    public void update(TinhDTO tinh) {
        tinhDAO.update(tinh);
    }

    @Override
    public void delete(Long id) {
        tinhDAO.delete(id);
    }
}
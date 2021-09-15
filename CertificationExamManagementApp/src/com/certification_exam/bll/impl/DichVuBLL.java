/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IDichVuBLL;
import com.certification_exam.dal.impl.DichVuDAL;
import com.certification_exam.dto.DichVuDTO;
import java.util.List;
import com.certification_exam.dal.IDichVuDAL;

/**
 *
 * @author HP
 */
public class DichVuBLL implements IDichVuBLL {

    private IDichVuDAL dichVuDAO;

    public DichVuBLL() {
        this.dichVuDAO = new DichVuDAL();
    }
    
    @Override
    public List<DichVuDTO> findAll() {
        return dichVuDAO.findAll();
    }

    @Override
    public DichVuDTO findById(Long id) {
        return dichVuDAO.findById(id);
    }

    @Override
    public Long save(DichVuDTO dichVu) {
        return dichVuDAO.save(dichVu);
    }

    @Override
    public void update(DichVuDTO dichVu) {
        dichVuDAO.update(dichVu);
    }

    @Override
    public void delete(Long id) {
        dichVuDAO.delete(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IDiaDiemBLL;
import com.certification_exam.dal.impl.DiaDiemDAL;
import com.certification_exam.dto.DiaDiemDTO;
import java.util.List;
import com.certification_exam.dal.IDiaDiemDAL;

/**
 *
 * @author kossp
 */
public class DiaDiemBLL implements IDiaDiemBLL {
    
    private IDiaDiemDAL diaDiemDAO;
    
    public DiaDiemBLL() {
        this.diaDiemDAO = new DiaDiemDAL();
    }
    
    @Override
    public List<DiaDiemDTO> findAll() {
        return diaDiemDAO.findAll();
    }

   @Override
    public DiaDiemDTO findById(Long id) {
        return diaDiemDAO.findById(id);
    }
    
    @Override
    public Long save(DiaDiemDTO diaDiem) {
        return diaDiemDAO.save(diaDiem);
    }

    @Override
    public void update(DiaDiemDTO diaDiem) {
        diaDiemDAO.update(diaDiem);
    }

    @Override
    public void delete(Long id) {
        diaDiemDAO.delete(id);
    }

    @Override
    public List<DiaDiemDTO> findByIdTinh(Long idTinh) {
        return diaDiemDAO.findByIdTinh(idTinh);
    }
}

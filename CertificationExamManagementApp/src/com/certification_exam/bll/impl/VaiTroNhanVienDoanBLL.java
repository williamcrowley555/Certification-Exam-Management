/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IVaiTroNhanVienDoanBLL;
import com.certification_exam.dal.impl.VaiTroDAL;
import com.certification_exam.dal.impl.VaiTroNhanVienDoanDAL;
import com.certification_exam.dto.DiaDiemDTO;
import com.certification_exam.dto.VaiTroDTO;
import com.certification_exam.dto.VaiTroNhanVienDoanDTO;
import java.util.ArrayList;
import java.util.List;
import com.certification_exam.dal.IVaiTroDAL;
import com.certification_exam.dal.IVaiTroNhanVienDoanDAL;

/**
 *
 * @author kossp
 */
public class VaiTroNhanVienDoanBLL implements IVaiTroNhanVienDoanBLL {
    private IVaiTroNhanVienDoanDAL vaiTroNhanVienDoanDAO;
    private IVaiTroDAL vaiTroDAO;
    public VaiTroNhanVienDoanBLL() {
        this.vaiTroNhanVienDoanDAO = new VaiTroNhanVienDoanDAL();
        this.vaiTroDAO = new VaiTroDAL();
    }    
    
    @Override
    public List<VaiTroNhanVienDoanDTO> findAll() {
        return vaiTroNhanVienDoanDAO.findAll();
    }

   @Override
    public VaiTroNhanVienDoanDTO findById(Long id) {
        return vaiTroNhanVienDoanDAO.findById(id);
    }         
    
    @Override
    public Long save(VaiTroNhanVienDoanDTO vaiTroNhanVienDoan) {
        return vaiTroNhanVienDoanDAO.save(vaiTroNhanVienDoan);
    }

    @Override
    public List<Long> findByIdNhanVien(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VaiTroDTO> findByIdVaiTroNhanVienDoan(Long idVaiTroNhanVienDoan) {
        List<Long> vaiTroIds = vaiTroNhanVienDoanDAO.findByIdDsNhanVienDoan(idVaiTroNhanVienDoan);
        List<VaiTroDTO> vaiTroList = new ArrayList<>();
        for (Long vaiTroId : vaiTroIds)
        {   
            vaiTroList.add(vaiTroDAO.findById(vaiTroId));      
        }
        return vaiTroList;
    }

    @Override
    public void deleteByIdDsNhanVienDoan(Long idDsNhanVienDoan) {
         vaiTroNhanVienDoanDAO.deleteByIdDsNhanVienDoan(idDsNhanVienDoan);
    }
}

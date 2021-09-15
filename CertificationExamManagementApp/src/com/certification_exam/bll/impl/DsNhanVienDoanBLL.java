/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IDsNhanVienDoanBLL;
import com.certification_exam.dal.impl.DsNhanVienDoanDAL;
import com.certification_exam.dal.impl.NhanVienDAL;
import com.certification_exam.dal.impl.VaiTroNhanVienDoanDAL;
import com.certification_exam.dto.DsNhanVienDoanDTO;
import com.certification_exam.dto.KhachHangDTO;
import com.certification_exam.dto.NhanVienDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.certification_exam.dal.IDsNhanVienDoanDAL;
import com.certification_exam.dal.INhanVienDAL;
import com.certification_exam.dal.IVaiTroNhanVienDoanDAL;

/**
 *
 * @author HP
 */
public class DsNhanVienDoanBLL implements IDsNhanVienDoanBLL {
    private INhanVienDAL nhanVienDAO; 
    private IDsNhanVienDoanDAL dsNhanVienDoanDAO;
    private IVaiTroNhanVienDoanDAL vaiTroNhanVienDoanDAO;

    public DsNhanVienDoanBLL() {
        this.nhanVienDAO = new NhanVienDAL();
        this.dsNhanVienDoanDAO = new DsNhanVienDoanDAL();
        this.vaiTroNhanVienDoanDAO = new VaiTroNhanVienDoanDAL();
    }
    
    @Override
    public List<DsNhanVienDoanDTO> findAll() {
        return dsNhanVienDoanDAO.findAll();
    }

    @Override
    public DsNhanVienDoanDTO findById(Long id) {
        return dsNhanVienDoanDAO.findById(id);
    }

    @Override
    public Long save(DsNhanVienDoanDTO NhanVienDoan) {
        return dsNhanVienDoanDAO.save(NhanVienDoan);
    }

    @Override
    public void update(DsNhanVienDoanDTO dsNhanVienDoan) {
        dsNhanVienDoanDAO.update(dsNhanVienDoan);
    }

    @Override
    public void delete(Long id) {
        dsNhanVienDoanDAO.delete(id);
    }

    @Override
    public ArrayList<NhanVienDTO> getFreeNhanVien(LocalDate date) {
        List<Long> nhanVienIds = dsNhanVienDoanDAO.getFreeNhanVien(date);
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<NhanVienDTO>();
        for (Long nhanVienId : nhanVienIds)
        {
           nhanVienList.add(nhanVienDAO.findById(nhanVienId));     
        }
        return nhanVienList;
    }

    @Override
    public ArrayList<NhanVienDTO> findByIdDoan(Long idDoan) {
        
        List<Long> nhanVienIds = dsNhanVienDoanDAO.findByIdDoan(idDoan);
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<NhanVienDTO>();
        for (Long nhanVienId : nhanVienIds)
        {
            nhanVienList.add(nhanVienDAO.findById(nhanVienId));
        }
        return nhanVienList;
    }

    @Override
    public DsNhanVienDoanDTO findByIdNhanVienDoan(Long idDoan, Long idNhanVien) {
        return dsNhanVienDoanDAO.findIdNhanVienDoan(idDoan, idNhanVien);
    }

    @Override
    public void deleteByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien) {
        Long idDsNhanVienDoan = dsNhanVienDoanDAO.findByIdDoanAndIdNhanVien(idDoan, idNhanVien).getId();
        vaiTroNhanVienDoanDAO.deleteByIdDsNhanVienDoan(idDsNhanVienDoan);
        dsNhanVienDoanDAO.deleteByIdDoanAndIdNhanVien(idDoan, idNhanVien);
    }
}

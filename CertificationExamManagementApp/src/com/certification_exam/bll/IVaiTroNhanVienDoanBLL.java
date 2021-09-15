/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.VaiTroDTO;
import com.certification_exam.dto.VaiTroNhanVienDoanDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IVaiTroNhanVienDoanBLL {
    List<VaiTroNhanVienDoanDTO> findAll();
    VaiTroNhanVienDoanDTO findById(Long id);
    List<VaiTroDTO> findByIdVaiTroNhanVienDoan(Long idVaiTroNhanVienDoan);
    List<Long> findByIdNhanVien(Long id);
    Long save(VaiTroNhanVienDoanDTO vaiTroNhanVienDoan);
    void deleteByIdDsNhanVienDoan(Long idDsNhanVienDoan);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.DiaDiemDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IDiaDiemDAL {    
    List<DiaDiemDTO> findAll();
    DiaDiemDTO findById(Long id);
    List<DiaDiemDTO> findByIdTinh(Long idTinh);
    Long save(DiaDiemDTO diaDiem);
    void update(DiaDiemDTO diaDiem);
    void delete(Long id);   
}
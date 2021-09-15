/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.DoanDTO;
import com.certification_exam.dto.ThongKeDoanDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IThongKeDoanDAL {
    List<ThongKeDoanDTO> findByIdTour(Long id);
}

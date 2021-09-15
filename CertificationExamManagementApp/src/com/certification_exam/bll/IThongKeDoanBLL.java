/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.ThongKeDoanDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IThongKeDoanBLL {
    List<ThongKeDoanDTO> findByIdTour(Long id);
}

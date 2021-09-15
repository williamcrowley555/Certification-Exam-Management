/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IThongKeDoanBLL;
import com.certification_exam.dal.impl.ThongKeDoanDAL;
import com.certification_exam.dto.ThongKeDoanDTO;
import java.util.List;
import com.certification_exam.dal.IThongKeDoanDAL;

/**
 *
 * @author kossp
 */
public class ThongKeDoanBLL implements IThongKeDoanBLL {
    
    private IThongKeDoanDAL thongKeDoanDAO;
    
    public ThongKeDoanBLL() {
        this.thongKeDoanDAO = new ThongKeDoanDAL();
    }    

    @Override
    public List<ThongKeDoanDTO> findByIdTour(Long id) {
       return thongKeDoanDAO.findByIdTour(id);
    }
}

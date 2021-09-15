/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.ITourBLL;
import com.certification_exam.dal.impl.TourDAL;
import com.certification_exam.dto.TourDTO;
import java.util.List;
import com.certification_exam.dal.ITourDAL;

/**
 *
 * @author HP
 */
public class TourBLL implements ITourBLL {

    private ITourDAL tourDAO;

    public TourBLL() {
        this.tourDAO = new TourDAL();
    }
    
    @Override
    public List<TourDTO> findAll() {
        return tourDAO.findAll();
    }

    @Override
    public TourDTO findById(Long id) {
        return tourDAO.findById(id);
    }

    @Override
    public Long save(TourDTO tour) {
        return tourDAO.save(tour);
    }

    @Override
    public void update(TourDTO tour) {
        tourDAO.update(tour);
    }

    @Override
    public void delete(Long id) {
        tourDAO.delete(id);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.Exam;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamBLL {
    List<Exam> findAll();
    Exam findById(Long id);
    Long save(Exam exam);
    void update(Exam exam);
    void delete(Long id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.ExamRoom;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamRoomBLL {
    List<ExamRoom> findAll();
    ExamRoom findById(Long id);
    Long save(ExamRoom examRoom);
    void update(ExamRoom examRoom);
    void delete(Long id);
}

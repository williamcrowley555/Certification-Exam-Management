/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.ExamRoom;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamRoomDAL extends GenericDAL<ExamRoom>{
    List<ExamRoom> findAll();
    ExamRoom findById(Long id);
    String getGreatestOrdinalNumber(String englishLevelName);
    Long save(ExamRoom examRoom);
    void update(ExamRoom examRoom);
    void delete(Long id); 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.Examine;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IExamineBLL {
    List<Examine> findAll();
    List<Examine> findByIds(List<Long> ids);
    List<Examine> findByExamCourseIdNotInExamRoomId(Long examCourseId, Long examRoomId);
    Examine findById(Long id);
    String getGreatestOrdinalNumber(String englishLevelName);
    Long save(Examine examine);
    void update(Examine examine);
    void delete(Long id);
}

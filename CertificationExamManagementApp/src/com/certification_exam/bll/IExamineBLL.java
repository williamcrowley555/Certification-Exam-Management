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
    List<Examine> findByExamCourseId(Long examCourseId);
    List<Examine> findByExamRoomId(Long examRoomId);
    List<Examine> findByFullName(String fullName);
    List<Examine> findByFullNameAndPhone(String fullName, String phone);
    Examine findById(Long id);
    Examine findByPhone(String phone);
    String getGreatestOrdinalNumber(String englishLevelName);
    Long save(Examine examine);
    void update(Examine examine);
    void updateExamineId(Examine examine, Long englishLevelId);
    void delete(Long id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IEnglishLevelBLL;
import com.certification_exam.bll.IExamineBLL;
import com.certification_exam.dal.IExamineDAL;
import com.certification_exam.dal.impl.ExamineDAL;
import com.certification_exam.dto.EnglishLevel;
import com.certification_exam.dto.Examine;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamineBLL implements IExamineBLL {
    
    private IExamineDAL examineDAL;
    private IEnglishLevelBLL englishLevelBLL;

    public ExamineBLL() {
        this.examineDAL = new ExamineDAL();
        this.englishLevelBLL = new EnglishLevelBLL();
    }

    @Override
    public List<Examine> findAll() {
        return examineDAL.findAll();
    }

    @Override
    public List<Examine> findByIds(List<Long> ids) {
        List<Examine> examineList = new ArrayList<>();
        for (Long id : ids) {
            Examine examine = findById(id);
            examineList.add(examine);
        }
        
        return examineList;
    }

    @Override
    public List<Examine> findByExamCourseIdNotInExamRoomId(Long examCourseId, Long examRoomId) {
        return examineDAL.findByExamCourseIdNotInExamRoomId(examCourseId, examRoomId);
    }

    @Override
    public Examine findById(Long id) {
        return examineDAL.findById(id);
    }

    @Override
    public Examine findByPhone(String phone) {
        return examineDAL.findByPhone(phone);
    }

    @Override
    public String getGreatestOrdinalNumber(String englishLevelName) {
        return examineDAL.getGreatestOrdinalNumber(englishLevelName);
    }

    @Override
    public Long save(Examine examine) {
        Examine existedExamine = findByPhone(examine.getPhone());
        
        if (existedExamine != null) {
            return null;
        }
        
        return examineDAL.save(examine);
    }

    @Override
    public void update(Examine examine) {
        Examine existedExamine = findByPhone(examine.getPhone());
        
        if (existedExamine != null && existedExamine.getId() != examine.getId()) {
            System.out.println("Phone number already exists");
            return;
        }
        
        examineDAL.update(examine);
    }

    @Override
    public void updateExamineId(Examine examine, Long englishLevelId) {
        EnglishLevel englishLevel = englishLevelBLL.findById(englishLevelId);
        String newExamineId = generateExamineId(englishLevel.getName());
        examine.setExamineId(newExamineId);
        update(examine);
    }

    @Override
    public void delete(Long id) {
        examineDAL.delete(id);
    }
    
    private String generateExamineId(String englishLevelName) {
        String name = englishLevelName + "001";
        String number = getGreatestOrdinalNumber(englishLevelName);
        if (number != null) {
            Integer nextNumber = Integer.valueOf(number) + 1;
            String strNumber = nextNumber > 99 ? String.valueOf(nextNumber) : "00" + nextNumber;
            name = englishLevelName + strNumber;
        }
        return name;
    }
}

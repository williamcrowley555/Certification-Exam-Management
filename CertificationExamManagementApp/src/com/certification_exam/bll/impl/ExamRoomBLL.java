/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IEnglishLevelBLL;
import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.dal.IExamRoomDAL;
import com.certification_exam.dal.impl.ExamRoomDAL;
import com.certification_exam.dto.EnglishLevel;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.dto.ExamRoom;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamRoomBLL implements IExamRoomBLL {
    
    private IExamRoomDAL examRoomDAL;
    private IExamCourseBLL examCourseBLL;
    private IEnglishLevelBLL englishLevelBLL;

    public ExamRoomBLL() {
        this.examRoomDAL = new ExamRoomDAL();
        this.examCourseBLL = new ExamCourseBLL();
        this.englishLevelBLL = new EnglishLevelBLL();
    }

    @Override
    public List<ExamRoom> findAll() {
        return examRoomDAL.findAll();
    }

    @Override
    public ExamRoom findById(Long id) {
        return examRoomDAL.findById(id);
    }

    @Override
    public String getGreatestOrdinalNumber(String englishLevelName) {
        return examRoomDAL.getGreatestOrdinalNumber(englishLevelName);
    }

    @Override
    public Long save(ExamRoom examRoom) {
        Long savedExamRoomId = null;
        ExamCourse course = examCourseBLL.findById(examRoom.getExamCourseId());
        
        if (course != null) {
            EnglishLevel englishLevel = englishLevelBLL.findById(course.getEnglishLevelId());
            String englishLevelName = englishLevel.getName();
            
            examRoom.setName(generateName(englishLevelName));
            savedExamRoomId = examRoomDAL.save(examRoom);
        }
        return savedExamRoomId;
    }

    @Override
    public void update(ExamRoom examRoom) {
        examRoomDAL.update(examRoom);
    }

    @Override
    public void delete(Long id) {
        examRoomDAL.delete(id);
    }
    
    private String generateName(String englishLevelName) {
        String name = englishLevelName + "P" + "01";
        String number = getGreatestOrdinalNumber(englishLevelName);
        if (number != null) {
            Integer nextNumber = Integer.valueOf(number) + 1;
            String strNumber = nextNumber > 9 ? String.valueOf(nextNumber) : "0" + nextNumber;
            name = englishLevelName + "P" + strNumber;
        }
        return name;
    }
}

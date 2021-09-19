package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.ExamRoom;

import java.util.List;

public interface ExamRoomService {
    List<ExamRoom> getExamRooms();
    List<ExamRoom> getExamRoomByExamineFullNameAndExaminePhone(String examineFullName, String examinePhone);
    List<ExamRoom> getExamRoomByExamCourse(ExamCourse examCourse);
    ExamRoom getExamRoomById(Long id);
}

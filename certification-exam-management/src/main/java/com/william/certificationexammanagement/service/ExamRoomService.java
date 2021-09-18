package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.ExamRoom;

import java.util.List;

public interface ExamRoomService {
    List<ExamRoom> getExamRooms();
    ExamRoom getExamRoomByExamineFullNameAndExaminePhone(String examineFullName, String examinePhone);
    ExamRoom getExamRoomById(Long id);
}

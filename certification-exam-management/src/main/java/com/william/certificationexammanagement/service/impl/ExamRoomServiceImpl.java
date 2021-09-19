package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.repository.ExamRoomRepository;
import com.william.certificationexammanagement.service.ExamRoomService;
import com.william.certificationexammanagement.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    @Autowired
    private ExamRoomRepository examRoomRepository;

    @Autowired
    private ExamineService examineService;

    @Override
    public List<ExamRoom> getExamRooms() {
        return examRoomRepository.findAll();
    }

    @Override
    public List<ExamRoom> getExamRoomByExamineFullNameAndExaminePhone(String examineFullName, String examinePhone) {
        List<ExamRoom> examRooms = null;
        Examine examine = null;

        if (examineFullName != null && examinePhone != null) {
            examine = examineService.getExamineByFullNameAndPhone(examineFullName, examinePhone);
        }

        if (examine != null) {
            examRooms = (List<ExamRoom>) examRoomRepository.findByExamines(examine);
        }

        return examRooms;
    }

    @Override
    public List<ExamRoom> getExamRoomByExamCourse(ExamCourse examCourse) {
        return (List<ExamRoom>) examRoomRepository.findExamRoomByExamCourse(examCourse);
    }

    @Override
    public ExamRoom getExamRoomById(Long id) {
        ExamRoom examRoom = null;
        Optional<ExamRoom> optional = examRoomRepository.findById(id);
        if (optional.isPresent()) {
            examRoom = optional.get();
        } else {
            throw new RuntimeException("Exam Room ID: " + id + " does not exist");
        }
        return examRoom;
    }
}

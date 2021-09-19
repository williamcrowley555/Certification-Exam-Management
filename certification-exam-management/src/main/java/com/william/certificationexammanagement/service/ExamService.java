package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.Exam;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;

import java.util.List;

public interface ExamService {
    List<Exam> getExams();
    List<Exam> getExamByExamine(Examine examine);
    Exam getExamById(Long id);
    Exam getExamByExamineAndExamRoom(Examine examine, ExamRoom examRoom);
}

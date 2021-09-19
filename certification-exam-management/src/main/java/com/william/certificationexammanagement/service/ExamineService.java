package com.william.certificationexammanagement.service;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.Examine;

import java.util.List;

public interface ExamineService {
    List<Examine> getExamines();
    Examine getExamineById(Long id);
    Examine getExamineByPhone(String phone);
    List<Examine> getExamineByFullName(String fullName);
    Examine getExamineByFullNameAndPhone(String fullName, String phone);
    Examine getExamineByExamineId(String examineId);
    Examine createExamine(Examine examine);
    Examine updateExamine(Examine examine);
    Examine enroll(Examine examine, ExamCourse examCourse);
}

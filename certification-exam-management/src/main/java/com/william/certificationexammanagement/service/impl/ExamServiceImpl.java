package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.Exam;
import com.william.certificationexammanagement.model.ExamRoom;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.repository.ExamRepository;
import com.william.certificationexammanagement.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> getExams() {
        return examRepository.findAll();
    }

    @Override
    public List<Exam> getExamByExamine(Examine examine) {
        return (List<Exam>) examRepository.findByExamine(examine);
    }

    @Override
    public Exam getExamById(Long id) {
        Exam exam = null;
        Optional<Exam> optional = examRepository.findById(id);
        if (optional.isPresent()) {
            exam = optional.get();
        } else {
            throw new RuntimeException("Exam ID: " + id + " does not exist");
        }
        return exam;
    }

    @Override
    public Exam getExamByExamineAndExamRoom(Examine examine, ExamRoom examRoom) {
        Exam exam = null;
        Optional<Exam> optional = examRepository.findByExamineAndExamRoom(examine, examRoom);

        if (optional.isPresent()) {
            exam = optional.get();
        }

        return exam;
    }
}

package com.william.certificationexammanagement.service.impl;

import com.william.certificationexammanagement.model.ExamCourse;
import com.william.certificationexammanagement.model.Examine;
import com.william.certificationexammanagement.repository.ExamineRepository;
import com.william.certificationexammanagement.service.ExamCourseService;
import com.william.certificationexammanagement.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    private ExamineRepository examineRepository;

    @Autowired
    private ExamCourseService examCourseService;

    @Override
    public List<Examine> getExamines() {
        return examineRepository.findAll();
    }

    @Override
    public Examine getExamineById(Long id) {
        Examine examine = null;
        Optional<Examine> optional = examineRepository.findById(id);
        if (optional.isPresent()) {
            examine = optional.get();
        } else {
            throw new RuntimeException("Examine ID: " + id + " does not exist");
        }
        return examine;
    }

    @Override
    public Examine getExamineByPhone(String phone) {
        Examine examine = null;
        Optional<Examine> optional = examineRepository.findByPhone(phone);

        if (optional.isPresent()) {
            examine = optional.get();
        }

        return examine;
    }

    @Override
    public List<Examine> getExamineByFullName(String fullName) {
        List<Examine> examineList = (List<Examine>) examineRepository.findByFullName(fullName);

        if (examineList.isEmpty() || examineList == null) {
            return null;
        }

        return examineList;
    }

    @Override
    public Examine getExamineByFullNameAndPhone(String fullName, String phone) {
        Examine examine = null;
        Optional<Examine> optional = examineRepository.findByFullNameAndPhone(fullName, phone);

        if (optional.isPresent()) {
            examine = optional.get();
        }

        return examine;
    }

    @Override
    public Examine createExamine(Examine examine) {
        Examine existedExamine = getExamineByPhone(examine.getPhone());
        if (existedExamine != null) {
            return null;
        }

        return examineRepository.save(examine);
    }

    @Override
    public Examine updateExamine(Examine examine) {
        Examine existedExamine = getExamineByPhone(examine.getPhone());
        if (existedExamine != null && existedExamine.getId() != examine.getId()) {
            System.out.println("Phone number already exists");
            return null;
        }

        return examineRepository.save(examine);
    }

    @Override
    public Examine enroll(Examine examine, ExamCourse examCourse) {
        Examine returnValue = null;
        Examine existedExamine = getExamineByPhone(examine.getPhone());

        if (existedExamine != null) {
            if (existedExamine.equals(examine)) {
                returnValue = existedExamine;
            }
        } else {
            returnValue = createExamine(examine);
        }

        Set<Examine> examineSet = examCourse.getExamines();
        examineSet.add(returnValue);
        examCourse.setExamines(examineSet);
        examCourseService.updateExamCourse(examCourse);

        return returnValue;
    }
}

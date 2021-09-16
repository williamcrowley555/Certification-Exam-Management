/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IExamCourseExamineDAL;
import com.certification_exam.dto.ExamCourseExamine;
import com.certification_exam.mapper.impl.ExamCourseExamineMapper;
import java.util.List;


/**
 *
 * @author HP
 */
public class ExamCourseExamineDAL extends AbstractDAL<ExamCourseExamine> implements IExamCourseExamineDAL{

    @Override
    public List<ExamCourseExamine> findAll() {
        String sql = "SELECT * FROM exam_course_examine";
        return query(sql, new ExamCourseExamineMapper());
    }

    @Override
    public ExamCourseExamine findByExamCourseIdAndExamineId(Long examCourseId, Long examineId) {
        String sql = "SELECT * FROM exam_course_examine WHERE exam_course_id = ? AND examine_id = ?";
        List<ExamCourseExamine> examCourseExamine = query(sql, new ExamCourseExamineMapper(), examCourseId, examineId);
        return examCourseExamine.isEmpty() ? null : examCourseExamine.get(0);
    }

    @Override
    public Long save(ExamCourseExamine examCourseExamine) {
        String sql = "INSERT INTO exam_course_examine(exam_course_id, examine_id) VALUES(?, ?)";
        return insert(sql, examCourseExamine.getExamCourseId(), examCourseExamine.getExamineId());
    }

    @Override
    public void deleteByExamCourseIdAndExamineId(Long examCourseId, Long examineId) {
        String sql = "DELETE FROM exam_course_examine WHERE exam_course_id = ? AND examine_id = ?";
        update(sql, examCourseId, examineId);
    }

   
    
}

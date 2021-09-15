/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IExamCourseDAL;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.mapper.impl.ExamCourseMapper;
import java.util.List;


/**
 *
 * @author HP
 */
public class ExamCourseDAL extends AbstractDAL<ExamCourse> implements IExamCourseDAL{

    @Override
    public List<ExamCourse> findAll() {
        String sql = "SELECT * FROM exam_course";
        return query(sql, new ExamCourseMapper());
    }

    @Override
    public ExamCourse findById(Long id) {
        String sql = "SELECT * FROM exam_course WHERE id = ?";
        List<ExamCourse> examCourse = query(sql, new ExamCourseMapper(), id);
        return examCourse.isEmpty() ? null : examCourse.get(0);
    }

    @Override
    public Long save(ExamCourse examCourse) {
        String sql = "INSERT INTO exam_course(name, english_level_id, date_created) VALUES(?, ?, ?)";
        return insert(sql, examCourse.getName(), examCourse.getEnglishLevelId(), examCourse.getDateCreated());
    }

    @Override
    public void update(ExamCourse examCourse) {
        String sql = "UPDATE exam_course SET name = ?, english_level_id = ?, date_created = ? WHERE id = ?";
        update(sql, examCourse.getName(), examCourse.getEnglishLevelId(), examCourse.getDateCreated(), examCourse.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM exam_course WHERE id = ?";
        update(sql, id);
    }
}

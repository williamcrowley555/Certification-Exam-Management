/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IExamCourseDAL;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.mapper.impl.ExamCourseMapper;
import java.time.LocalDate;
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
    public List<ExamCourse> findByEnglishLevelId(Long englishLevelId) {
        String sql = "SELECT * FROM exam_course WHERE english_level_id = ?";
        return query(sql, new ExamCourseMapper(), englishLevelId);
    }

    @Override
    public List<ExamCourse> findByMonthAndYear(Integer month, Integer year) {
        String sql = "SELECT * FROM exam_course WHERE month = ? AND year = ?";
        return query(sql, new ExamCourseMapper(), month, year);
    }

    @Override
    public List<ExamCourse> findByEnglishLevelIdAndMonthAndYearStartFrom(Long englishLevelId, Integer month, Integer year) {
        String sql = "SELECT * FROM exam_course WHERE english_level_id = ? AND month >= ? AND year >= ?";
        return query(sql, new ExamCourseMapper(), englishLevelId, month, year);
    }

    @Override
    public ExamCourse findById(Long id) {
        String sql = "SELECT * FROM exam_course WHERE id = ?";
        List<ExamCourse> examCourse = query(sql, new ExamCourseMapper(), id);
        return examCourse.isEmpty() ? null : examCourse.get(0);
    }

    @Override
    public ExamCourse findByMonthAndYearAndEnglishLevelId(Integer month, Integer year, Long englishLevelId) {
        String sql = "SELECT * FROM exam_course WHERE month = ? AND year = ? AND english_level_id = ?";
        List<ExamCourse> examCourse = query(sql, new ExamCourseMapper(), month, year, englishLevelId);
        return examCourse.isEmpty() ? null : examCourse.get(0);
    }

    @Override
    public Long save(ExamCourse examCourse) {
        String sql = "INSERT INTO exam_course(name, month, year, english_level_id) VALUES(?, ?, ?, ?)";
        return insert(sql, examCourse.getName(), examCourse.getMonth(), examCourse.getYear(), examCourse.getEnglishLevelId());
    }

    @Override
    public void update(ExamCourse examCourse) {
        String sql = "UPDATE exam_course SET name = ?, month = ?, year = ?, english_level_id = ? WHERE id = ?";
        update(sql, examCourse.getName(), examCourse.getMonth(), examCourse.getYear(), examCourse.getEnglishLevelId(), examCourse.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM exam_course WHERE id = ?";
        update(sql, id);
    }
}

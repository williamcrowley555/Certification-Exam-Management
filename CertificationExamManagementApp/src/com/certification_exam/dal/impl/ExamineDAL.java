/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IExamineDAL;
import com.certification_exam.dto.Examine;
import com.certification_exam.mapper.impl.ExamineMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class ExamineDAL extends AbstractDAL<Examine> implements IExamineDAL {

    @Override
    public List<Examine> findAll() {
        String sql = "SELECT * FROM examine";
        return query(sql, new ExamineMapper());
    }

    @Override
    public Examine findById(Long id) {
        String sql = "SELECT * FROM examine WHERE id = ?";
        List<Examine> examine = query(sql, new ExamineMapper(), id);
        return examine.isEmpty() ? null : examine.get(0);
    }

    @Override
    public Long save(Examine examine) {
        String sql = "INSERT INTO examine(examine_id, first_name, last_name, dob, gender, address, phone, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, examine.getExamineId(), examine.getFirstName(), examine.getLastName(), examine.getDob(), examine.getGender(), examine.getAddress(), examine.getPhone(), examine.getStatus());
    }

    @Override
    public void update(Examine examine) {
        String sql = "UPDATE examine SET examine_id = ?, first_name = ?, last_name = ?, dob = ?, gender = ?, address = ?, phone = ?, status = ? WHERE id = ?";
        update(sql, examine.getExamineId(), examine.getFirstName(), examine.getLastName(), examine.getDob(), examine.getGender(), examine.getAddress(), examine.getPhone(), examine.getStatus(), examine.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM examine WHERE id = ?";
        update(sql, id);
    }
}
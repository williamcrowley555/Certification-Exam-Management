/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IUserDAL;
import com.certification_exam.dto.User;
import com.certification_exam.mapper.impl.UserMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserDAL extends AbstractDAL<User> implements IUserDAL {

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return query(sql, new UserMapper());
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<User> user = query(sql, new UserMapper(), id);
        return user.isEmpty() ? null : user.get(0);
    }

    @Override
    public Long save(User user) {
        String sql = "INSERT INTO user(first_name, last_name, dob, gender, address, phone, email, password, enabled) VALUES(?)";
        return insert(sql, user.getFirstName(), user.getLastName(), user.getDob(), user.getGender(), user.getAddress(), user.getPhone(), user.getEmail(), user.getPassword(), user.isEnabled());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET first_name = ?, last_name = ?, dob = ?, gender = ?, address = ?, phone = ?, email = ?, password = ?, enabled = ? WHERE id = ?";
        update(sql, user.getFirstName(), user.getLastName(), user.getDob(), user.getGender(), user.getAddress(), user.getPhone(), user.getEmail(), user.getPassword(), user.isEnabled(), user.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        update(sql, id);
    }
    
}

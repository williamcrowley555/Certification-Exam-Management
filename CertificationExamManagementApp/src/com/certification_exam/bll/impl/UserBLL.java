/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IUserBLL;
import com.certification_exam.dal.IUserDAL;
import com.certification_exam.dal.impl.UserDAL;
import com.certification_exam.dto.User;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserBLL implements IUserBLL {
    
    private IUserDAL userDAL;

    public UserBLL() {
        this.userDAL = new UserDAL();
    }

    @Override
    public List<User> findAll() {
        return userDAL.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDAL.findById(id);
    }

    @Override
    public Long save(User user) {
        return userDAL.save(user);
    }

    @Override
    public void update(User user) {
        userDAL.update(user);
    }

    @Override
    public void delete(Long id) {
        userDAL.delete(id);
    }
}

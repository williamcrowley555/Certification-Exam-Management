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
import org.mindrot.jbcrypt.BCrypt;

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
    public User findByPhone(String phone) {
        return userDAL.findByPhone(phone);
    }

    @Override
    public User findByEmail(String email) {
        return userDAL.findByEmail(email);
    }

    @Override
    public Long save(User user) {
        User existedUser = findByPhone(user.getPhone());
        
        if (existedUser != null) {
            return null;
        }
        
        existedUser = findByEmail(user.getEmail());
        
        if (existedUser != null) {
            return null;
        }
        
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(encryptedPassword);
        return userDAL.save(user);
    }

    @Override
    public void update(User user) {
        User existedUser = findByPhone(user.getPhone());
        
        if (existedUser != null && existedUser.getId() == user.getId()) {
            return;
        }
        
        existedUser = findByEmail(user.getEmail());
        
        if (existedUser != null && existedUser.getId() == user.getId()) {
            return;
        }
        
        User oldUser = findById(user.getId());
        boolean valuate = BCrypt.checkpw(user.getPassword(), oldUser.getPassword());
        
        if (!valuate) {
            String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
            user.setPassword(encryptedPassword);
        }
        
        userDAL.update(user);
    }

    @Override
    public void delete(Long id) {
        userDAL.delete(id);
    }
}

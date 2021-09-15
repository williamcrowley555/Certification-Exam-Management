/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IUserRoleBLL;
import com.certification_exam.dal.IUserRoleDAL;
import com.certification_exam.dal.impl.UserRoleDAL;
import com.certification_exam.dto.UserRole;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserRoleBLL implements IUserRoleBLL {
    
    private IUserRoleDAL userRoleDAL;

    public UserRoleBLL() {
        this.userRoleDAL = new UserRoleDAL();
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleDAL.findAll();
    }

    @Override
    public UserRole findByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleDAL.findByUserIdAndRoleId(userId, roleId);
    }

    @Override
    public Long save(UserRole userRole) {
        return userRoleDAL.save(userRole);
    }

    @Override
    public void deleteByUserIdAndRoleId(Long userId, Long roleId) {
        userRoleDAL.deleteByUserIdAndRoleId(userId, roleId);
    }
}

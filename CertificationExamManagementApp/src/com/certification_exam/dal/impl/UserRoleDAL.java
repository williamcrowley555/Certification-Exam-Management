/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dal.IUserRoleDAL;
import com.certification_exam.dto.UserRole;
import com.certification_exam.mapper.impl.UserRoleMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserRoleDAL extends AbstractDAL<UserRole> implements IUserRoleDAL{

    @Override
    public List<UserRole> findAll() {
        String sql = "SELECT * FROM user_role";
        return query(sql, new UserRoleMapper());
    }

    @Override
    public UserRole findByUserIdAndRoleId(Long userId, Long roleId) {
        String sql = "SELECT * FROM user_role WHERE user_id = ? AND role_id = ?";
        List<UserRole> userRole = query(sql, new UserRoleMapper(), userId, roleId);
        return userRole.isEmpty() ? null : userRole.get(0);
    }

    @Override
    public Long save(UserRole userRole) {
        String sql = "INSERT INTO user_role(user_id, role_id) VALUES(?, ?)";
        return insert(sql, userRole.getUserId(), userRole.getRoleId());
    }

    @Override
    public void deleteByUserIdAndRoleId(Long userId, Long roleId) {
        String sql = "DELETE FROM user_role WHERE user_id = ? AND role_id = ?";
        update(sql, userId, roleId);
    }
}

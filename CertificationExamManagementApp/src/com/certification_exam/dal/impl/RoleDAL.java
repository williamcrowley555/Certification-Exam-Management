/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.dto.Role;
import com.certification_exam.mapper.impl.RoleMapper;
import java.util.List;
import com.certification_exam.dal.IRoleDAL;

/**
 *
 * @author HP
 */
public class RoleDAL extends AbstractDAL<Role> implements IRoleDAL{

    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM role";
        return query(sql, new RoleMapper());
    }

    @Override
    public Role findById(Long id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        List<Role> role = query(sql, new RoleMapper(), id);
        return role.isEmpty() ? null : role.get(0);
    }

    @Override
    public Long save(Role role) {
        String sql = "INSERT INTO role(name, normalized_name) VALUES(?, ?)";
        return insert(sql, role.getName(), role.getNormalizedName());
    }

    @Override
    public void update(Role role) {
        String sql = "UPDATE role SET name = ?, normalized_name = ? WHERE id = ?";
        update(sql, role.getName(), role.getNormalizedName(), role.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM role WHERE id = ?";
        update(sql, id);
    }
}

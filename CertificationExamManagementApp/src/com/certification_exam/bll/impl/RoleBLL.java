/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IRoleBLL;
import com.certification_exam.dal.IRoleDAL;
import com.certification_exam.dal.impl.RoleDAL;
import com.certification_exam.dto.Role;
import java.util.List;

/**
 *
 * @author HP
 */
public class RoleBLL implements IRoleBLL {
    
    private IRoleDAL roleDAL;

    public RoleBLL() {
        this.roleDAL = new RoleDAL();
    }

    @Override
    public List<Role> findAll() {
        return roleDAL.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleDAL.findById(id);
    }

    @Override
    public Long save(Role role) {
        return roleDAL.save(role);
    }

    @Override
    public void update(Role role) {
        roleDAL.update(role);
    }

    @Override
    public void delete(Long id) {
        roleDAL.delete(id);
    } 
}

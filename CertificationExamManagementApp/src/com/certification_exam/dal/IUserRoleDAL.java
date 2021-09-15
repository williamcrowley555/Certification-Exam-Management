/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.UserRole;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IUserRoleDAL extends GenericDAL<UserRole>{
    List<UserRole> findAll();
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);
    Long save(UserRole userRole);
    void deleteByUserIdAndRoleId(Long userId, Long roleId); 
}

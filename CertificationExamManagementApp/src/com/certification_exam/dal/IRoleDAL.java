/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.Role;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IRoleDAL extends GenericDAL<Role> {
    List<Role> findAll();
    Role findById(Long id);
    Long save(Role role);
    void update(Role role);
    void delete(Long id);
}

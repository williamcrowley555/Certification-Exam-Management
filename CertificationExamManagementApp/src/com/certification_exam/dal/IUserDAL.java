/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal;

import com.certification_exam.dto.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IUserDAL extends GenericDAL<User> {
    List<User> findAll();
    User findById(Long id);
    User findByPhone(String phone);
    User findByEmail(String email);
    Long save(User user);
    void update(User user);
    void delete(Long id);  
}

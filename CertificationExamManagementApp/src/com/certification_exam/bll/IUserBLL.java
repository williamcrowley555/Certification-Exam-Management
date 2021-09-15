/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll;

import com.certification_exam.dto.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IUserBLL {
    List<User> findAll();
    User findById(Long id);
    Long save(User user);
    void update(User user);
    void delete(Long id);
}

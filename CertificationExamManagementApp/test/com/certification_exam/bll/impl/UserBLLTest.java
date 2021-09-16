/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.dto.User;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class UserBLLTest {
    
    public UserBLLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        UserBLL instance = new UserBLL();
        List<User> expResult = null;
        List<User> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        UserBLL instance = new UserBLL();
        User expResult = null;
        User result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByPhone() {
        System.out.println("findByPhone");
        String phone = "";
        UserBLL instance = new UserBLL();
        User expResult = null;
        User result = instance.findByPhone(phone);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        String email = "";
        UserBLL instance = new UserBLL();
        User expResult = null;
        User result = instance.findByEmail(email);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSave() {
        System.out.println("save");
        LocalDate dob = LocalDate.of(1976, Month.FEBRUARY, 15);
        User user = new User();
        user.setFirstName("Ren");
        user.setLastName("Lucifer");
        user.setDob(dob);
        user.setGender(1);
        user.setAddress("TP.HCM");
        user.setPhone("0905632123");
        user.setEmail("admin@gmail.com");
        user.setPassword("123");
        UserBLL instance = new UserBLL();
        Long expResult = null;
        Long result = instance.save(user);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        User user = null;
        UserBLL instance = new UserBLL();
        instance.update(user);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        UserBLL instance = new UserBLL();
        instance.delete(id);
        fail("The test case is a prototype.");
    }
    
}

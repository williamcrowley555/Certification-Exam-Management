/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.dto.ExamRoom;
import com.certification_exam.util.Utils;
import java.util.Calendar;
import java.util.Date;
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
public class ExamRoomBLLTest {
    
    public ExamRoomBLLTest() {
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
        ExamRoomBLL instance = new ExamRoomBLL();
        List<ExamRoom> expResult = null;
        List<ExamRoom> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        ExamRoomBLL instance = new ExamRoomBLL();
        ExamRoom expResult = null;
        ExamRoom result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSave() {
        System.out.println("save");
        ExamRoom examRoom = new ExamRoom();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 9, 15, 9, 30, 0);
        Date date = calendar.getTime();
        examRoom.setExamDate(date);
        examRoom.setProctorId(3L);
        examRoom.setExaminerId(2L);
        examRoom.setExamCourseId(2L);
        ExamRoomBLL instance = new ExamRoomBLL();
        Long result = instance.save(examRoom);
        System.out.println("ID: " + result);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        ExamRoom examRoom = null;
        ExamRoomBLL instance = new ExamRoomBLL();
        instance.update(examRoom);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        ExamRoomBLL instance = new ExamRoomBLL();
        instance.delete(id);
        fail("The test case is a prototype.");
    }
    
}

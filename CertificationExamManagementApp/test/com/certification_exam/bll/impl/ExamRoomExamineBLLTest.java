/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.dto.ExamRoomExamine;
import java.util.ArrayList;
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
public class ExamRoomExamineBLLTest {
    
    public ExamRoomExamineBLLTest() {
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
        ExamRoomExamineBLL instance = new ExamRoomExamineBLL();
        List<ExamRoomExamine> expResult = null;
        List<ExamRoomExamine> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByExamRoomIdAndExamineId() {
        System.out.println("findByExamRoomIdAndExamineId");
        Long examRoomId = null;
        Long examineId = null;
        ExamRoomExamineBLL instance = new ExamRoomExamineBLL();
        ExamRoomExamine expResult = null;
        ExamRoomExamine result = instance.findByExamRoomIdAndExamineId(examRoomId, examineId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSave_ExamRoomExamine() {
        System.out.println("save");
        ExamRoomExamine examRoomExamine = null;
        ExamRoomExamineBLL instance = new ExamRoomExamineBLL();
        instance.save(examRoomExamine);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSave_List() {
        System.out.println("save");
        List<ExamRoomExamine> examRoomExamines = new ArrayList<>();
        examRoomExamines.add(new ExamRoomExamine(10L, 2L));
        examRoomExamines.add(new ExamRoomExamine(10L, 3L));
        ExamRoomExamineBLL instance = new ExamRoomExamineBLL();
        instance.save(examRoomExamines);
    }

    @Test
    public void testDeleteByExamRoomIdAndExamineId() {
        System.out.println("deleteByExamRoomIdAndExamineId");
        Long examRoomId = null;
        Long examineId = null;
        ExamRoomExamineBLL instance = new ExamRoomExamineBLL();
        instance.deleteByExamRoomIdAndExamineId(examRoomId, examineId);
        fail("The test case is a prototype.");
    }
    
}

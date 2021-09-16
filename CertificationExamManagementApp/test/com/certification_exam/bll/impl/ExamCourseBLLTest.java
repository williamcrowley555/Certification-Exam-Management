/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.dto.ExamCourse;
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
public class ExamCourseBLLTest {
    
    public ExamCourseBLLTest() {
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
        ExamCourseBLL instance = new ExamCourseBLL();
        List<ExamCourse> expResult = null;
        List<ExamCourse> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        ExamCourseBLL instance = new ExamCourseBLL();
        ExamCourse expResult = null;
        ExamCourse result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByMonthAndYearAndEnglishLevelId() {
        System.out.println("findByMonthAndYearAndEnglishLevelId");
        Integer month = null;
        Integer year = null;
        Long englishLevelId = null;
        ExamCourseBLL instance = new ExamCourseBLL();
        ExamCourse expResult = null;
        ExamCourse result = instance.findByMonthAndYearAndEnglishLevelId(month, year, englishLevelId);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSave() {
        System.out.println("save");
        ExamCourse examCourse = new ExamCourse();
        examCourse.setMonth(10);
        examCourse.setEnglishLevelId(1L);
        ExamCourseBLL instance = new ExamCourseBLL();
        Long result = instance.save(examCourse);
        System.out.println("ID: " + result);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        ExamCourse examCourse = null;
        ExamCourseBLL instance = new ExamCourseBLL();
        instance.update(examCourse);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        ExamCourseBLL instance = new ExamCourseBLL();
        instance.delete(id);
        fail("The test case is a prototype.");
    }
    
}

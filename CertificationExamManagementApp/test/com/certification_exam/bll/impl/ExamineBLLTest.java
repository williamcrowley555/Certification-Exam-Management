/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.IExamCourseExamineBLL;
import com.certification_exam.dto.ExamCourseExamine;
import com.certification_exam.dto.Examine;
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
public class ExamineBLLTest {
    
    public ExamineBLLTest() {
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
        ExamineBLL instance = new ExamineBLL();
        List<Examine> expResult = null;
        List<Examine> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        ExamineBLL instance = new ExamineBLL();
        Examine expResult = null;
        Examine result = instance.findById(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGreatestOrdinalNumber() {
        System.out.println("getGreatestOrdinalNumber");
        String englishLevelName = "";
        ExamineBLL instance = new ExamineBLL();
        String expResult = "";
        String result = instance.getGreatestOrdinalNumber(englishLevelName);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSave() {
        System.out.println("save");
        LocalDate dob = LocalDate.of(2000, Month.SEPTEMBER, 6);
        Examine examine = new Examine();
        examine.setFirstName("Hoàng Minh");
        examine.setLastName("Nguyễn");
        examine.setDob(dob);
        examine.setGender(1);
        examine.setAddress("TP.HCM");
        examine.setPhone("0908822128");
        
        ExamineBLL instance = new ExamineBLL();
        Long result = instance.save(examine);
        
//        ExamCourseExamine examCourseExamine = new ExamCourseExamine(2L, result);
//        IExamCourseExamineBLL instance1 = new ExamCourseExamineBLL();
//        instance1.save(examCourseExamine);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Examine examine = null;
        ExamineBLL instance = new ExamineBLL();
        instance.update(examine);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        ExamineBLL instance = new ExamineBLL();
        instance.delete(id);
        fail("The test case is a prototype.");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.dal.impl;

import com.certification_exam.bll.IThongKeTourBLL;
import com.certification_exam.bll.impl.ThongKeTourBLL;
import com.certification_exam.dto.ThongKeTourDTO;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class ThongKeTourDALTest {
    public ThongKeTourDALTest(){}
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        IThongKeTourBLL instance = new ThongKeTourBLL();
        List<ThongKeTourDTO> result = instance.findAll();
        assertNotNull(result);
    }
}

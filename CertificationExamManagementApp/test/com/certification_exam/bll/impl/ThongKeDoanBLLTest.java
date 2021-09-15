/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.bll.impl;

import com.certification_exam.bll.impl.ThongKeDoanBLL;
import com.certification_exam.bll.IThongKeDoanBLL;
import com.certification_exam.dto.ThongKeDoanDTO;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author USER
 */
public class ThongKeDoanBLLTest {
    public ThongKeDoanBLLTest(){}
    @Test
    public void testFindByIdTour() {
        System.out.println("findAll");
        Long idTour = 411L;
        IThongKeDoanBLL instance = new ThongKeDoanBLL();
        List<ThongKeDoanDTO> result = instance.findByIdTour(idTour);
        assertNotNull(result);
    }
}

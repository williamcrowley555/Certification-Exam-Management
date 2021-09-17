/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.util;

import com.certification_exam.bll.IVaiTroBLL;
import com.certification_exam.bll.impl.VaiTroBLL;
import com.certification_exam.dto.Examine;
import com.certification_exam.dto.KhachHangDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class ThiSinhTableLoaderUtil implements ITableLoaderUtil<Examine>{
    @Override
    public DefaultTableModel setTable(List<Examine> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(Examine examine : listItems) {
            row = new Vector();
            row.add(examine.getId());
            row.add(examine.getLastName());
            row.add(examine.getFirstName());
            
            if (examine.getGender() == 1) row.add("Nam");
            else if (examine.getGender() == 2) row.add("Nữ");
            else row.add("Không xác định");
            
            row.add(examine.getExamineId());
            row.add(examine.getDob());
            row.add(examine.getAddress());
            row.add(examine.getPhone());
            row.add(examine.getStatus() ? "Kích hoạt" : "Vô hiệu");
            model.addRow(row);
        }
        
        return model;
    }
    
}

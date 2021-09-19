/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.util;

import com.certification_exam.bll.IEnglishLevelBLL;
import com.certification_exam.bll.impl.EnglishLevelBLL;
import com.certification_exam.dto.ExamCourse;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class KhoaThiTableLoaderUtil implements ITableLoaderUtil<ExamCourse>{
    @Override
    public DefaultTableModel setTable(List<ExamCourse> listItems, String[] listColumns) {
        Vector header = new Vector();
        IEnglishLevelBLL englishLevelBLL = new EnglishLevelBLL();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        if (listItems != null)
        {
            for(ExamCourse course : listItems) {
                row = new Vector();
                row.add(course.getId());
                row.add(course.getName());
                row.add(course.getMonth());
                row.add(course.getYear());
                row.add(englishLevelBLL.findById(course.getEnglishLevelId()).getName());

                model.addRow(row);
            }
        }
        
        return model;
    }
    
}

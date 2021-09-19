/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.util;

import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IUserBLL;
import com.certification_exam.bll.impl.ExamCourseBLL;
import com.certification_exam.bll.impl.UserBLL;
import com.certification_exam.dto.ExamRoom;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kossp
 */
public class PhongThiTableLoaderUtil implements ITableLoaderUtil<ExamRoom> {
    private IExamCourseBLL examCourseBLL = new ExamCourseBLL();
    private IUserBLL userBLL = new UserBLL();
    @Override
    public DefaultTableModel setTable(List<ExamRoom> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        if (listItems != null){
            Vector row = null;
            for(ExamRoom examRoom : listItems) {
                row = new Vector();
                row.add(examRoom.getId());
                row.add(examRoom.getName());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                row.add(formatter.format(examRoom.getExamDate()));
                row.add(examCourseBLL.findById(examRoom.getExamCourseId()).getName());
                row.add(examRoom.getQuantity());
                if ( userBLL.findById(examRoom.getExaminerId()) == null)
                row.add("Trống");
                else{
                    row.add(
                            userBLL.findById(examRoom.getExaminerId()).getLastName() + " " +
                            userBLL.findById(examRoom.getExaminerId()).getFirstName()
                    );
                }

                if (userBLL.findById(examRoom.getProctorId()) == null){
                row.add("Trống");
                } else {
                    row.add(
                            userBLL.findById(examRoom.getProctorId()).getLastName() + " " +
                            userBLL.findById(examRoom.getProctorId()).getFirstName()
                    );
                }

                model.addRow(row);
            }
        }
        return model;
    }    
}

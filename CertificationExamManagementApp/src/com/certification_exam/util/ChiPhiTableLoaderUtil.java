/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.util;

import com.certification_exam.bll.IChiPhiDoanBLL;
import com.certification_exam.bll.IDiaDiemBLL;
import com.certification_exam.bll.IDichVuBLL;
import com.certification_exam.bll.ITinhBLL;
import com.certification_exam.bll.impl.ChiPhiDoanBLL;
import com.certification_exam.bll.impl.DiaDiemBLL;
import com.certification_exam.bll.impl.DichVuBLL;
import com.certification_exam.bll.impl.TinhBLL;
import com.certification_exam.dto.ChiPhiDoanDTO;
import com.certification_exam.dto.DiaDiemDTO;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */

public class ChiPhiTableLoaderUtil implements ITableLoaderUtil<ChiPhiDoanDTO>{
    private IChiPhiDoanBLL chiPhiBLL = new ChiPhiDoanBLL();
    private IDichVuBLL dichVuBLL = new DichVuBLL();
    
    @Override
    public DefaultTableModel setTable(List<ChiPhiDoanDTO> listItems, String[] listColumns) {
        Vector header = new Vector();
        for(Object colName : listColumns){
            header.add(colName);
        }
        
        DefaultTableModel model = new DefaultTableModel(header, 0);
        
        Vector row = null;
        for(ChiPhiDoanDTO chiPhi : listItems) {
            row = new Vector();
            row.add(chiPhi.getId());
            row.add(chiPhi.getIdDoan());
            row.add(dichVuBLL.findById(chiPhi.getIdDichVu()).getTenDichVu());
            row.add(chiPhi.getHoaDon());
            row.add(chiPhi.getNgayHoaDon());
            row.add(chiPhi.getChiPhi());
            model.addRow(row);
        }
        
        return model;
    }    
}

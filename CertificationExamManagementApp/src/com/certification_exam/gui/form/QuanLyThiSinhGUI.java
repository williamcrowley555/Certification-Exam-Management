/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.gui.form;

import com.certification_exam.bll.IExamBLL;
import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.bll.IExamineBLL;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.certification_exam.gui.menu.MyScrollBarUI;
import com.certification_exam.gui.popup.PopUpThiSinhGUI;
import com.certification_exam.bll.impl.ExamBLL;
import com.certification_exam.bll.impl.ExamCourseBLL;
import com.certification_exam.bll.impl.ExamRoomBLL;
import com.certification_exam.bll.impl.ExamineBLL;
import com.certification_exam.dto.ExamRoom;
import com.certification_exam.dto.Examine;
import com.certification_exam.gui.popup.PopUpChamDiemGUI;
import com.certification_exam.gui.popup.PopUpTableChonKhoaThiGUI;
import com.certification_exam.util.KhoaThiTableLoaderUtil;
import com.certification_exam.util.PhongThiTableLoaderUtil;
import com.certification_exam.util.ThiSinhTableLoaderUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class QuanLyThiSinhGUI extends javax.swing.JPanel {

    /**
     * Creates new form Panel1
     */
    String[] columnNames = {
                            "Id",
                            "Họ",
                            "Tên",
                            "Giới Tính",
                            "Số Báo Danh",
                            "Ngày Sinh",
                            "Địa Chỉ",
                            "Sđt",
                            "Trạng Thái"
    };
    
    String[] columnNamesPhongThi = {
                            "Id",
                            "Tên Phòng Thi",
                            "Số Lượng",
                            "Ngày Thi"
    };
    
    String[] columnNamesKhoaThi = {
                            "Id",
                            "Tên Khóa",
                            "Tháng",
                            "Năm",
                            "Trình Độ"
    };
    private IExamineBLL examineBLL;
    private IExamCourseBLL examCourseBLL;
    private IExamRoomBLL examRoomBLL;
    private IExamBLL examBLL;
    private PopUpChamDiemGUI popupChamDiem;
    private PopUpThiSinhGUI popUp = null;
    private PopUpTableChonKhoaThiGUI popUpKhoaThi = null;
    TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyThiSinhGUI() {
        initComponents();
        examineBLL = new ExamineBLL();
        examCourseBLL = new ExamCourseBLL();
        examRoomBLL = new ExamRoomBLL();
        examBLL = new ExamBLL();
        initNullTable();
        loadTableData();
        headerColor(14,142,233,tblThiSinh);
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
       
    }
    
    public void initNullTable()
    {
        tblKhoaThi.setModel(new KhoaThiTableLoaderUtil().setTable(null, columnNamesKhoaThi));
        tblPhongThi.setModel(new PhongThiTableLoaderUtil().setTable(null, columnNamesPhongThi));
        headerColor(14,142,233,tblKhoaThi);
        headerColor(14,142,233,tblPhongThi);
    }
    
    public void loadTableData() {
        tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(examineBLL.findAll(), this.columnNames)) ;
        tblKhoaThi.setModel(new KhoaThiTableLoaderUtil().setTable(examCourseBLL.findAll(), this.columnNamesKhoaThi)) ;
        headerColor(14,142,233,tblThiSinh);
        headerColor(14,142,233,tblKhoaThi);
         
    }
    
    public Vector createHeader(Object[] columnNames){
        Vector header = new Vector();
        for(Object colName : columnNames){
            header.add(colName);
        }
        return header;
    }
    
    public void headerColor(int r, int b, int g, JTable table)
    {
        Color color = new Color(r,b,g);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(color);
        headerRenderer.setForeground(color.WHITE);
        

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
        table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }       
         
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightClickMenu = new javax.swing.JPopupMenu();
        itemSua = new javax.swing.JMenuItem();
        itemDangKyKhoaThi = new javax.swing.JMenuItem();
        itemChamDiem = new javax.swing.JMenuItem();
        pnlHeader = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        pnlBody = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblThiSinh = new javax.swing.JTable();
        scroll1 = new javax.swing.JScrollPane();
        tblKhoaThi = new javax.swing.JTable();
        scroll2 = new javax.swing.JScrollPane();
        tblPhongThi = new javax.swing.JTable();
        lblTableTitle = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        txtSdt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        itemSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/img/edit_icon.png"))); // NOI18N
        itemSua.setText("Sửa");
        itemSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSuaActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemSua);

        itemDangKyKhoaThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemDangKyKhoaThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/img/edit_icon.png"))); // NOI18N
        itemDangKyKhoaThi.setText("Đăng Ký Khóa Thi");
        itemDangKyKhoaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDangKyKhoaThiActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemDangKyKhoaThi);

        itemChamDiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemChamDiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/img/edit_icon.png"))); // NOI18N
        itemChamDiem.setText("Chấm Điểm");
        itemChamDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemChamDiemActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemChamDiem);

        setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setPreferredSize(new java.awt.Dimension(808, 150));

        btnThem.setBackground(new java.awt.Color(14, 142, 233));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setOpaque(true);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Quản Lý Thí Sinh");

        btnRefresh.setBackground(new java.awt.Color(14, 142, 233));
        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.setOpaque(true);
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRefreshMousePressed(evt);
            }
        });
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 572, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addGap(30, 30, 30))
            .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeaderLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );

        add(pnlHeader, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        tblThiSinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblThiSinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblThiSinhMouseReleased(evt);
            }
        });
        scroll.setViewportView(tblThiSinh);

        tblKhoaThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhoaThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblKhoaThiMouseReleased(evt);
            }
        });
        scroll1.setViewportView(tblKhoaThi);

        tblPhongThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPhongThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPhongThiMouseReleased(evt);
            }
        });
        scroll2.setViewportView(tblPhongThi);

        lblTableTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTableTitle.setText("Danh sách tất cả thí sinh:");

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTen.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(14, 142, 233)));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        btnTimKiem.setBackground(new java.awt.Color(14, 142, 233));
        btnTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.setContentAreaFilled(false);
        btnTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTimKiem.setOpaque(true);
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTimKiemMousePressed(evt);
            }
        });
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSdt.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(14, 142, 233)));
        txtSdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSdtActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Tên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Sđt:");

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)))
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_btnThemActionPerformed

    private void itemSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSuaActionPerformed
        int rowindex = tblThiSinh.getSelectedRow();
        Long id = Long.parseLong(tblThiSinh.getValueAt(rowindex,0).toString());
        if (this.popUp == null) {
        popUp = new PopUpThiSinhGUI("PUT", examineBLL.findById(id));
        } else {
            this.popUp.toFront();
            this.popUp.center();
        }
        popUp.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUp = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_itemSuaActionPerformed

    private void tblThiSinhMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThiSinhMouseReleased
        int r = tblThiSinh.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tblThiSinh.getRowCount()) {
            tblThiSinh.setRowSelectionInterval(r, r);
        } else {
           tblThiSinh.clearSelection();
        }

        int rowindex = tblThiSinh.getSelectedRow();
       
        if (rowindex < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
             int rowindexPhongThi = tblPhongThi.getSelectedRow();
             int rowindexKhoaThi = tblKhoaThi.getSelectedRow();
        if (rowindexPhongThi >=0 && rowindexKhoaThi >= 0)
        {
           itemChamDiem.setVisible(true);
           rightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        } else {
            itemChamDiem.setVisible(false);
            rightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tblThiSinhMouseReleased

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        if (this.popUp == null) {
            this.popUp = new PopUpThiSinhGUI("POST");
            
        } else {
            this.popUp.toFront();
            this.popUp.center();
        }
        popUp.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUp = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_btnThemMousePressed

    private void itemDangKyKhoaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDangKyKhoaThiActionPerformed
        LocalDate today = LocalDate.now();
        int rowindex = tblThiSinh.getSelectedRow();
        Long id = Long.parseLong(tblThiSinh.getValueAt(rowindex,0).toString());
        if (this.popUpKhoaThi == null) {
        popUpKhoaThi = new PopUpTableChonKhoaThiGUI(id, today.getMonthValue(), today.getYear());
        popUpKhoaThi.show();
        } else {
            this.popUpKhoaThi.toFront();
            this.popUpKhoaThi.center();
        }
        popUpKhoaThi.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUpKhoaThi = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_itemDangKyKhoaThiActionPerformed

    private void tblKhoaThiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoaThiMouseReleased
      int rowindex = tblKhoaThi.getSelectedRow();
       if (rowindex >=0)
       {
           Long CourseId = Long.parseLong(tblKhoaThi.getValueAt(rowindex,0).toString());
           List<ExamRoom> examRooms = examRoomBLL.findByExamCourseId(CourseId);
           DefaultTableModel model = new DefaultTableModel(columnNamesPhongThi,0);
           
           if (examRooms.isEmpty()) {
               model = new DefaultTableModel(columnNamesPhongThi,0);
               tblPhongThi.setModel(model);
           } 
           else{     
                 model = new DefaultTableModel(columnNamesPhongThi,0);
                 for (int i = 0; i < examRooms.size(); i++) {
                 model.addRow(new Object[]    {      
                                                  String.valueOf(examRooms.get(i).getId()),
                                                  String.valueOf(examRooms.get(i).getName()),
                                                  String.valueOf(examRooms.get(i).getQuantity()),
                                                  String.valueOf(examRooms.get(i).getExamDate())
                                                  
                                              });
                 tblPhongThi.setModel(model);
                 }  
           }
           lblTableTitle.setText("Danh sách thí sinh khóa " + tblKhoaThi.getValueAt(rowindex,1).toString());
           headerColor(14,142,233,tblPhongThi);
           
           List<Examine> examineListByCourse = examineBLL.findByExamCourseId(CourseId);
           DefaultTableModel modelExamine = new DefaultTableModel(columnNames,0);
           if(examineListByCourse.isEmpty()){
                    tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(null, this.columnNames));
           } else {
           tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(examineBLL.findByExamCourseId(CourseId), this.columnNames));   
           }
           headerColor(14,142,233,tblThiSinh);
       } else JOptionPane.showMessageDialog(this, "Hãy chọn 1 khóa để hiển thị", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_tblKhoaThiMouseReleased

    private void tblPhongThiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongThiMouseReleased
       int rowindex = tblPhongThi.getSelectedRow();
       int rowindexKhoaThi = tblKhoaThi.getSelectedRow();
       if (rowindex >=0 && rowindexKhoaThi >= 0)
       {
           Long RoomId = Long.parseLong(tblPhongThi.getValueAt(rowindex,0).toString());
           List<Examine> examinesByCourseAndRoom = examineBLL.findByExamRoomId(RoomId);
           DefaultTableModel model = new DefaultTableModel(columnNames,0);
           
           if (examinesByCourseAndRoom.isEmpty()) {
               tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(null, this.columnNames));
           } 
           else{     
                  tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(examinesByCourseAndRoom, this.columnNames)); 
           }
           headerColor(14,142,233,tblThiSinh);
           
           lblTableTitle.setText("Danh sách thí sinh khóa "+ tblKhoaThi.getValueAt(rowindexKhoaThi,1).toString()+" phòng "+ tblPhongThi.getValueAt(rowindex,1).toString());
       } else 
           JOptionPane.showMessageDialog(this, "Hãy chọn 1 khóa để hiển thị", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_tblPhongThiMouseReleased

    private void btnRefreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshMousePressed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        loadTableData();
        lblTableTitle.setText("Danh sách tất cả thí sinh:");
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void btnTimKiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemMousePressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        if(txtTen.getText().isEmpty() && txtSdt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Hãy nhập gì đó để tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (txtSdt.getText().isEmpty())
        {
            List<Examine> examinesByFullName = examineBLL.findByFullName(txtTen.getText().trim());
            if (examinesByFullName == null) {
               JOptionPane.showMessageDialog(this, "Không tìm thấy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
           } 
           else{     
                  tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(examinesByFullName, this.columnNames)); 
           }
           headerColor(14,142,233,tblThiSinh);
        } else if (txtTen.getText().isEmpty())
        {
            Examine examineByPhone = examineBLL.findByPhone(txtSdt.getText().trim());
            List<Examine> examinesByPhone = new ArrayList<>();
            if (examineByPhone != null)
            {
            examinesByPhone.add(examineByPhone);
            tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(examinesByPhone, this.columnNames)); 
            headerColor(14,142,233,tblThiSinh);
            }
            else JOptionPane.showMessageDialog(this, "Không tìm thấy sđt này vui lòng nhập chính xác", "Thông báo", JOptionPane.INFORMATION_MESSAGE);  
        } else {
            String name = txtTen.getText().trim();
            String phoneNumber = txtSdt.getText().trim();
            List<Examine> examinesByFullNameAndPhone = examineBLL.findByFullNameAndPhone(name, phoneNumber);
            if (examinesByFullNameAndPhone == null) {
               JOptionPane.showMessageDialog(this, "Không tìm thấy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
           } 
           else{     
                  tblThiSinh.setModel(new ThiSinhTableLoaderUtil().setTable(examinesByFullNameAndPhone, this.columnNames)); 
           }
           headerColor(14,142,233,tblThiSinh);
        }    
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtSdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSdtActionPerformed

    private void itemChamDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemChamDiemActionPerformed
        int rowindexThiSinh = tblThiSinh.getSelectedRow();
        int rowindexPhongThi = tblPhongThi.getSelectedRow();
        
        Long RoomId = Long.parseLong(tblPhongThi.getValueAt(rowindexPhongThi,0).toString());
        Long ExamineId = Long.parseLong(tblThiSinh.getValueAt(rowindexThiSinh,0).toString());

        Date examDate = examRoomBLL.findById(RoomId).getExamDate();
        Date today = new Date();
        System.out.println(today);

        if (!today.after(examDate)) {
            JOptionPane.showMessageDialog(this, "Thí sinh chưa hoàn thành kỳ thi", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (this.popupChamDiem == null) {
        popupChamDiem = new PopUpChamDiemGUI(examBLL.getAndCreate(ExamineId, RoomId));
        popupChamDiem.show();
        } else {
            this.popupChamDiem.toFront();
            this.popupChamDiem.center();
        }
        
        popupChamDiem.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                popupChamDiem = null;
            }
        });
        
    }//GEN-LAST:event_itemChamDiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JMenuItem itemChamDiem;
    private javax.swing.JMenuItem itemDangKyKhoaThi;
    private javax.swing.JMenuItem itemSua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblTableTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JTable tblKhoaThi;
    private javax.swing.JTable tblPhongThi;
    private javax.swing.JTable tblThiSinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}

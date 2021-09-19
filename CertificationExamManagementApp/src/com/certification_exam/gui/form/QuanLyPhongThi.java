/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.gui.form;

import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.bll.IExamRoomExamineBLL;
import com.certification_exam.bll.IExamineBLL;
import com.certification_exam.bll.IUserBLL;
import com.certification_exam.bll.impl.ExamCourseBLL;
import com.certification_exam.bll.impl.ExamRoomBLL;
import com.certification_exam.bll.impl.ExamRoomExamineBLL;
import com.certification_exam.bll.impl.ExamineBLL;
import com.certification_exam.bll.impl.UserBLL;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.dto.ExamRoom;
import com.certification_exam.dto.ExamRoomExamine;
import com.certification_exam.dto.Examine;
import com.certification_exam.dto.User;
import com.certification_exam.gui.menu.MyComboBoxEditor;
import com.certification_exam.gui.menu.MyComboBoxRenderer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.certification_exam.gui.menu.MyScrollBarUI;
import com.certification_exam.gui.popup.PopUpPhongThiGUI;
import com.certification_exam.gui.popup.PopUpKhoaThiGUI;
import com.certification_exam.util.PhongThiTableLoaderUtil;
import com.certification_exam.util.TableSetupUtil;
import com.certification_exam.util.ThiSinhXepPhongTableLoaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RavenPC
 */
public class QuanLyPhongThi extends javax.swing.JPanel {

    /**
     * Creates new form Panel1
     */
    private PopUpPhongThiGUI popUp = null;
    private PopUpKhoaThiGUI popUpKhoaThi = null;
    DefaultTableModel model;   
    DefaultTableModel modelThiSinh;
    String[] columnNames = {
                            "Id",
                            "Tên Phòng Thi",
                            "Ngày Thi",
                            "Khóa Thi",
                            "Số Lượng",
                            "Giám Khảo",
                            "Giám Thị"
    };
    
    String[] thiSinhColumnNames = {
                            "Id",
                            "Họ",
                            "Tên",
                            "Giới Tính",
                            "Ngày Sinh"
                           
    };
    
    
    private IExamRoomBLL examRoomBLL;
    private IExamCourseBLL examCourseBLL;
    private IExamineBLL examineBLL;
    private IExamRoomExamineBLL examRoomExamineBLL;
    private List<Examine> DsThiSinhChuaXepPhong = null;
    private List<Examine> DsThiSinhDaXepPhong = null;
    private IUserBLL userBLL;
    TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyPhongThi() {
        initComponents();
        examRoomBLL = new ExamRoomBLL();
        examCourseBLL = new ExamCourseBLL();
        examineBLL = new ExamineBLL();
        examRoomExamineBLL = new ExamRoomExamineBLL();
        userBLL = new UserBLL();
        initComboBox();
        loadTableData();
        headerColor(14,142,233,tblPhongThi);
        initNullTable();
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
    }
    
    public void initComboBox(){
        
        setComboBox(comboBoxKhoaThi, getExamCourseItems());
        setComboBox(comboBoxGiamKhao, getUserItems());
        setComboBox(comboBoxGiamThi, getUserItems());
        
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(14,142,233));
        comboBoxGiamKhao = myComboBox(comboBoxGiamKhao, new Color(14,142,233));
        comboBoxGiamThi = myComboBox(comboBoxGiamThi, new Color(14,142,233));
    
    }
    
    public void loadTableData() {
        String selectedCourse = comboBoxKhoaThi.getSelectedItem().toString();
        Long idCourse = Long.parseLong(selectedCourse.substring(0, selectedCourse.indexOf(" - ")));
        tblPhongThi.setModel(new PhongThiTableLoaderUtil().setTable(examRoomBLL.findByExamCourseId(idCourse), this.columnNames)) ;
        this.rowSorter = TableSetupUtil.setTableFilter(tblPhongThi, txtTimKiem);
        headerColor(14,142,233,tblPhongThi);
    }
    
    public Vector createHeader(Object[] columnNames){
        Vector header = new Vector();
        for(Object colName : columnNames){
            header.add(colName);
        }
        return header;
    }
    
    public void initNullTable()
    {
        tblThiSinhChuaXepPhong.setModel(new ThiSinhXepPhongTableLoaderUtil().setTable(null, thiSinhColumnNames));
        tblThiSinhDaXepPhong.setModel(new ThiSinhXepPhongTableLoaderUtil().setTable(null, thiSinhColumnNames));
        headerColor(14,142,233,tblThiSinhChuaXepPhong);
        headerColor(14,142,233,tblThiSinhDaXepPhong);
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
    
    public String[] getExamCourseItems() {
        List<ExamCourse> courseLists = examCourseBLL.findAll();
        String[] courseItems = new String[courseLists.size()];
        int index = 0;
        for(ExamCourse vt : courseLists) {
            courseItems[index] = vt.getId() + " - " + vt.getName();
            ++ index;
        }
        return courseItems;
    }
    
    public String[] getUserItems() {
        List<User> userLists = userBLL.findAll();
        userLists.add(null);
        String[] userItems = new String[userLists.size()];
        int index = 0;
        for(User vt : userLists) {
            if(vt != null)
            {
            userItems[index] = vt.getId() + " - " + vt.getLastName()+ " " + vt.getFirstName();
            ++ index;
            } else userItems[index] = "Trống";
        }
        return userItems;
    }
    
    public String getUserItemName(User user) {
        if(user != null)
        return user.getId() + " - " + user.getLastName()+ " " + user.getFirstName();
        else return null;
    }
    
     public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
     
    public JComboBox myComboBox(JComboBox box, Color color)
    {   
        box.setRenderer(new MyComboBoxRenderer());
        box.setEditor(new MyComboBoxEditor());
        
        box.setUI(new BasicComboBoxUI() 
        {
            @Override
            protected ComboPopup createPopup() 
            {
                BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
                basicComboPopup.setBorder(new MatteBorder(2,2,2,2,color));
                return basicComboPopup;
            }
            
            @Override 
            protected JButton createArrowButton() 
            {
                Color matteGrey = new Color(223,230,233);
                Color flatBlue = new Color(14,142,233);
        
                BasicArrowButton custom = new BasicArrowButton(
                BasicArrowButton.SOUTH, null, null, Color.WHITE, null);
                custom.setBorder(new MatteBorder(0,0,0,0,flatBlue));
                return custom;
            }
        }); 

       return box;
    }
    
    
    
     public void modifyExamineList(List<Examine> originalList, List<Examine> modifiedList, Long examRoomId) {
        List<Examine> newExamineList = new ArrayList<>();
        List<Examine> deletedExamineList = originalList;
        
        
//        Find new examine and deleted examine to list
        for(Examine examine : modifiedList) {
            if (originalList.contains(examine)) {
                deletedExamineList.remove(examine);
            } else {
                newExamineList.add(examine);
            }
        }
        
       
//        Save and delete examine into ExamCourseExamine
        if (!newExamineList.isEmpty()) {
            for (Examine examine : newExamineList) {
                try {
                    
                     examRoomExamineBLL.save(new ExamRoomExamine(examRoomId, examine.getId()));
                } catch(Exception e) {
                    e.printStackTrace();
                }  
               
            }
        }
        
        if (!deletedExamineList.isEmpty()) {
            for (Examine examine : deletedExamineList) {
                try {
                    System.out.println("Delete id: " + examine.getId());
                    examRoomExamineBLL.deleteByExamRoomIdAndExamineId(examRoomId, examine.getId());
                } catch(Exception e) {
                    e.printStackTrace();
                }  
            }
        }
        
        
    }
    
     public void addDeleted(Examine deleted)   //Add deleted examine back to table not in room
    {
        boolean duplicate = false;
        for (int i = 0; i < modelThiSinh.getRowCount(); i++)
        {
            if (modelThiSinh.getValueAt(i, 0) == deleted.getId())
            duplicate = true;
        }
        
        if (!duplicate)
        {
        Vector examine = new Vector();
        examine.add(deleted.getId());
        examine.add(deleted.getLastName());
        examine.add(deleted.getFirstName());
        examine.add(deleted.getGender());
        examine.add(deleted.getDob());
        modelThiSinh.addRow(examine);      
        }
        
        tblThiSinhChuaXepPhong.setModel(modelThiSinh);
         headerColor(14,142,233,tblThiSinhChuaXepPhong);

    }
     
    public boolean save(){
       
       
       int rowindex = tblPhongThi.getSelectedRow();
       Long RoomId = Long.parseLong(tblPhongThi.getValueAt(rowindex,0).toString());
       if (rowindex >=0)
       {
         modifyExamineList(examineBLL.findByExamRoomId(RoomId), DsThiSinhDaXepPhong, RoomId);
         return true;
       } else JOptionPane.showMessageDialog(this, "Hãy chọn 1 phòng để tiếp tục", "Thông báo", JOptionPane.INFORMATION_MESSAGE); 
       return false; 
       
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
        pnlHead = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lblTitle = new javax.swing.JLabel();
        lblTimKiem = new javax.swing.JLabel();
        btnTaoKhoaThiNhanh = new javax.swing.JButton();
        comboBoxKhoaThi = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        tblPhongThi = new javax.swing.JTable();
        scroll1 = new javax.swing.JScrollPane();
        tblThiSinhChuaXepPhong = new javax.swing.JTable();
        scroll2 = new javax.swing.JScrollPane();
        tblThiSinhDaXepPhong = new javax.swing.JTable();
        btnThemVaoPhongThi = new javax.swing.JButton();
        btnXoaKhoiPhongThi = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLưu = new javax.swing.JButton();
        lblTitleSoLuong = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        lblTitleSoLuong1 = new javax.swing.JLabel();
        lblTitleSoLuong2 = new javax.swing.JLabel();
        comboBoxGiamThi = new javax.swing.JComboBox<>();
        comboBoxGiamKhao = new javax.swing.JComboBox<>();
        btnLuuGtGk = new javax.swing.JButton();

        itemSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        itemSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/img/edit_icon.png"))); // NOI18N
        itemSua.setText("Sửa");
        itemSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSuaActionPerformed(evt);
            }
        });
        rightClickMenu.add(itemSua);

        setLayout(new java.awt.BorderLayout());

        pnlHead.setBackground(new java.awt.Color(255, 255, 255));
        pnlHead.setPreferredSize(new java.awt.Dimension(808, 150));

        btnThem.setBackground(new java.awt.Color(14, 142, 233));
        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm Phòng Thi");
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.setOpaque(true);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnThemMouseReleased(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(14, 142, 233)));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Quản Lý Phòng Thi");

        lblTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/img/search_icon.png"))); // NOI18N

        btnTaoKhoaThiNhanh.setBackground(new java.awt.Color(14, 142, 233));
        btnTaoKhoaThiNhanh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTaoKhoaThiNhanh.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoKhoaThiNhanh.setText("Tạo Khóa Thi Nhanh");
        btnTaoKhoaThiNhanh.setContentAreaFilled(false);
        btnTaoKhoaThiNhanh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTaoKhoaThiNhanh.setOpaque(true);
        btnTaoKhoaThiNhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTaoKhoaThiNhanhMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTaoKhoaThiNhanhMouseReleased(evt);
            }
        });
        btnTaoKhoaThiNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoKhoaThiNhanhActionPerformed(evt);
            }
        });

        comboBoxKhoaThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxKhoaThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxKhoaThi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxKhoaThiItemStateChanged(evt);
            }
        });
        comboBoxKhoaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxKhoaThiActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Khóa Thi");

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTaoKhoaThiNhanh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTaoKhoaThiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlHeadLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(comboBoxKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 14, Short.MAX_VALUE))))
        );

        add(pnlHead, java.awt.BorderLayout.PAGE_START);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

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
        scroll.setViewportView(tblPhongThi);

        tblThiSinhChuaXepPhong.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThiSinhChuaXepPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblThiSinhChuaXepPhongMouseReleased(evt);
            }
        });
        scroll1.setViewportView(tblThiSinhChuaXepPhong);

        tblThiSinhDaXepPhong.setModel(new javax.swing.table.DefaultTableModel(
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
        tblThiSinhDaXepPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblThiSinhDaXepPhongMouseReleased(evt);
            }
        });
        scroll2.setViewportView(tblThiSinhDaXepPhong);

        btnThemVaoPhongThi.setBackground(new java.awt.Color(14, 142, 233));
        btnThemVaoPhongThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemVaoPhongThi.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVaoPhongThi.setText("Thêm");
        btnThemVaoPhongThi.setContentAreaFilled(false);
        btnThemVaoPhongThi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemVaoPhongThi.setOpaque(true);
        btnThemVaoPhongThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemVaoPhongThiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnThemVaoPhongThiMouseReleased(evt);
            }
        });
        btnThemVaoPhongThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoPhongThiActionPerformed(evt);
            }
        });

        btnXoaKhoiPhongThi.setBackground(new java.awt.Color(14, 142, 233));
        btnXoaKhoiPhongThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoaKhoiPhongThi.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKhoiPhongThi.setText("Xóa");
        btnXoaKhoiPhongThi.setContentAreaFilled(false);
        btnXoaKhoiPhongThi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaKhoiPhongThi.setOpaque(true);
        btnXoaKhoiPhongThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaKhoiPhongThiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnXoaKhoiPhongThiMouseReleased(evt);
            }
        });
        btnXoaKhoiPhongThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiPhongThiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thí sinh đã xếp vào phòng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thí sinh chưa xếp vào phòng");

        btnLưu.setBackground(new java.awt.Color(14, 142, 233));
        btnLưu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLưu.setForeground(new java.awt.Color(255, 255, 255));
        btnLưu.setText("Lưu Xếp Phòng Thi");
        btnLưu.setContentAreaFilled(false);
        btnLưu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLưu.setOpaque(true);
        btnLưu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLưuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLưuMouseReleased(evt);
            }
        });
        btnLưu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLưuActionPerformed(evt);
            }
        });

        lblTitleSoLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitleSoLuong.setText("SL:");

        lblSoLuong.setText("0");

        lblTitleSoLuong1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitleSoLuong1.setText("Giám Thị:");

        lblTitleSoLuong2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitleSoLuong2.setText("Giám Khảo:");

        comboBoxGiamThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxGiamThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxGiamThi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxGiamThiItemStateChanged(evt);
            }
        });
        comboBoxGiamThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxGiamThiActionPerformed(evt);
            }
        });

        comboBoxGiamKhao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxGiamKhao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxGiamKhao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxGiamKhaoItemStateChanged(evt);
            }
        });
        comboBoxGiamKhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxGiamKhaoActionPerformed(evt);
            }
        });

        btnLuuGtGk.setBackground(new java.awt.Color(14, 142, 233));
        btnLuuGtGk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuuGtGk.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuGtGk.setText("Lưu GT&GK");
        btnLuuGtGk.setContentAreaFilled(false);
        btnLuuGtGk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuuGtGk.setOpaque(true);
        btnLuuGtGk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLuuGtGkMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLuuGtGkMouseReleased(evt);
            }
        });
        btnLuuGtGk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuGtGkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemVaoPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(btnXoaKhoiPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTitleSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblTitleSoLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                                .addComponent(btnLưu))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxGiamKhao, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTitleSoLuong2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLuuGtGk)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboBoxGiamThi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scroll1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThemVaoPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnXoaKhoiPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTitleSoLuong)
                                .addComponent(lblSoLuong)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleSoLuong1)
                            .addComponent(btnLuuGtGk, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addComponent(btnLưu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(comboBoxGiamThi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitleSoLuong2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxGiamKhao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnThemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemMouseReleased

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        // TODO add your handling code here:
        if (this.popUp == null) {
            this.popUp = new PopUpPhongThiGUI("POST");
            
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

    private void itemSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSuaActionPerformed
        int rowindex = tblPhongThi.getSelectedRow();
        Long id = Long.parseLong(tblPhongThi.getValueAt(rowindex,0).toString());
        if (this.popUp == null) {
            popUp = new PopUpPhongThiGUI("PUT", examRoomBLL.findById(id));
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

    private void tblPhongThiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongThiMouseReleased
        // TODO add your handling code here:
        int r = tblPhongThi.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tblPhongThi.getRowCount()) {
            tblPhongThi.setRowSelectionInterval(r, r);
        } else {
           tblPhongThi.clearSelection();
        }
        int rowindex = tblPhongThi.getSelectedRow();
        if (rowindex < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
            
            rightClickMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
       
        Long RoomId = Long.parseLong(tblPhongThi.getValueAt(rowindex,0).toString());
        String selectedCourse = comboBoxKhoaThi.getSelectedItem().toString();
        Long CourseId = Long.parseLong(selectedCourse.substring(0, selectedCourse.indexOf(" - ")));
        DsThiSinhChuaXepPhong = examineBLL.findByExamCourseIdNotInExamRoomId(CourseId, RoomId);
        DsThiSinhDaXepPhong = examineBLL.findByExamRoomId(RoomId);
        lblSoLuong.setText(String.valueOf(DsThiSinhDaXepPhong.size()));
        this.modelThiSinh = new ThiSinhXepPhongTableLoaderUtil().setTable(DsThiSinhChuaXepPhong, this.thiSinhColumnNames);
        tblThiSinhChuaXepPhong.setModel(new ThiSinhXepPhongTableLoaderUtil().setTable(DsThiSinhChuaXepPhong, this.thiSinhColumnNames));
        headerColor(14,142,233,tblThiSinhChuaXepPhong);
        
        tblThiSinhDaXepPhong.setModel(new ThiSinhXepPhongTableLoaderUtil().setTable(DsThiSinhDaXepPhong, this.thiSinhColumnNames));
        headerColor(14,142,233,tblThiSinhDaXepPhong);
        
        comboBoxGiamKhao.setSelectedItem(
                getUserItemName(userBLL.findById(
                    examRoomBLL.findById(RoomId).getExaminerId()
                ))
        );
        
        comboBoxGiamThi.setSelectedItem(
                getUserItemName(userBLL.findById(
                    examRoomBLL.findById(RoomId).getProctorId()
                ))
        );
        
    }//GEN-LAST:event_tblPhongThiMouseReleased

    private void btnTaoKhoaThiNhanhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoKhoaThiNhanhMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaoKhoaThiNhanhMousePressed

    private void btnTaoKhoaThiNhanhMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoKhoaThiNhanhMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaoKhoaThiNhanhMouseReleased

    private void btnTaoKhoaThiNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoKhoaThiNhanhActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (this.popUpKhoaThi == null) {
            this.popUpKhoaThi = new PopUpKhoaThiGUI("POST_FAST");
            
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
    }//GEN-LAST:event_btnTaoKhoaThiNhanhActionPerformed

    private void comboBoxKhoaThiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxKhoaThiItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxKhoaThiItemStateChanged

    private void comboBoxKhoaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKhoaThiActionPerformed
        // TODO add your handling code here:
        loadTableData();
    }//GEN-LAST:event_comboBoxKhoaThiActionPerformed

    private void tblThiSinhChuaXepPhongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThiSinhChuaXepPhongMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThiSinhChuaXepPhongMouseReleased

    private void tblThiSinhDaXepPhongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThiSinhDaXepPhongMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThiSinhDaXepPhongMouseReleased

    private void btnThemVaoPhongThiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemVaoPhongThiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemVaoPhongThiMousePressed

    private void btnThemVaoPhongThiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemVaoPhongThiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemVaoPhongThiMouseReleased

    private void btnThemVaoPhongThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoPhongThiActionPerformed
       
       int rowindex = tblThiSinhChuaXepPhong.getSelectedRow();
       if (rowindex >=0)
       {
           
        if(Integer.parseInt(lblSoLuong.getText()) < 35)
        {
            Long id = Long.parseLong(tblThiSinhChuaXepPhong.getValueAt(rowindex,0).toString());
            Examine selected = examineBLL.findById(id);
            boolean duplicate = false;
            for (Examine examine : DsThiSinhDaXepPhong)
            {
                if (examine.getId().equals(selected.getId()))
                duplicate = true;
            }

             if (!duplicate)
             {
                 DsThiSinhDaXepPhong.add(selected);
                 lblSoLuong.setText(String.valueOf(DsThiSinhDaXepPhong.size()));
                 model = new DefaultTableModel(thiSinhColumnNames,0);
                 for (int i = 0; i < DsThiSinhDaXepPhong.size(); i++) {
                 model.addRow(new Object[]    {      
                                                  String.valueOf(DsThiSinhDaXepPhong.get(i).getId()),
                                                  String.valueOf(DsThiSinhDaXepPhong.get(i).getLastName()),
                                                  String.valueOf(DsThiSinhDaXepPhong.get(i).getFirstName()),
                                                  String.valueOf(DsThiSinhDaXepPhong.get(i).getGender() == 1 ? "Nam" : "Nữ"),
                                                  String.valueOf(DsThiSinhDaXepPhong.get(i).getDob()),
                                              });
                 tblThiSinhDaXepPhong.setModel(model);
                 }  
             }
             headerColor(14,142,233,tblThiSinhDaXepPhong);
        } else JOptionPane.showMessageDialog(this, "Phòng đã đầy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
       } else JOptionPane.showMessageDialog(this, "Hãy chọn 1 thí sinh để thêm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnThemVaoPhongThiActionPerformed

    private void btnXoaKhoiPhongThiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKhoiPhongThiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaKhoiPhongThiMousePressed

    private void btnXoaKhoiPhongThiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKhoiPhongThiMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaKhoiPhongThiMouseReleased

    private void btnXoaKhoiPhongThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiPhongThiActionPerformed
      int rowindex = tblThiSinhDaXepPhong.getSelectedRow();
       if (rowindex >=0)
       {
       Long id = Long.parseLong(tblThiSinhDaXepPhong.getValueAt(rowindex,0).toString());
       for (int i = 0; i < DsThiSinhDaXepPhong.size(); i++)
       {
           if (id.equals(DsThiSinhDaXepPhong.get(i).getId()))
           {
           addDeleted(DsThiSinhDaXepPhong.get(i));
           DsThiSinhDaXepPhong.remove(DsThiSinhDaXepPhong.get(i));
           lblSoLuong.setText(String.valueOf(DsThiSinhDaXepPhong.size()));
           }
       }
         
        model = new DefaultTableModel(columnNames,0);
        for (int i = 0; i < DsThiSinhDaXepPhong.size(); i++) {
        model.addRow(new Object[]   {
                                        String.valueOf(DsThiSinhDaXepPhong.get(i).getId()),
                                              String.valueOf(DsThiSinhDaXepPhong.get(i).getLastName()),
                                              String.valueOf(DsThiSinhDaXepPhong.get(i).getFirstName()),
                                              String.valueOf(DsThiSinhDaXepPhong.get(i).getGender() == 1 ? "Nam" : "Nữ"),
                                              String.valueOf(DsThiSinhDaXepPhong.get(i).getDob())
                                    });
        }
        
         if (DsThiSinhDaXepPhong.size() > 0) 
                tblThiSinhDaXepPhong.setModel(model);
            else 
            {
                 model = new DefaultTableModel(columnNames,0);
                 tblThiSinhDaXepPhong.setModel(model);
            }
         headerColor(14,142,233,tblThiSinhDaXepPhong);
       } else 
           JOptionPane.showMessageDialog(this, "Hãy chọn 1 thí sinh để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXoaKhoiPhongThiActionPerformed

    private void btnLưuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLưuMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLưuMousePressed

    private void btnLưuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLưuMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLưuMouseReleased

    private void btnLưuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLưuActionPerformed

        Boolean result = save();
                if(result) {
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    int selectedRow = tblPhongThi.getSelectedRow();
                    loadTableData();
                    tblPhongThi.changeSelection(selectedRow, 0, true, true);
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
    }//GEN-LAST:event_btnLưuActionPerformed

    private void comboBoxGiamThiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxGiamThiItemStateChanged
       
    }//GEN-LAST:event_comboBoxGiamThiItemStateChanged

    private void comboBoxGiamThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxGiamThiActionPerformed
       
    }//GEN-LAST:event_comboBoxGiamThiActionPerformed

    private void comboBoxGiamKhaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxGiamKhaoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxGiamKhaoItemStateChanged

    private void comboBoxGiamKhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxGiamKhaoActionPerformed
        
    }//GEN-LAST:event_comboBoxGiamKhaoActionPerformed

    private void btnLuuGtGkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuGtGkMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuGtGkMousePressed

    private void btnLuuGtGkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuGtGkMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuGtGkMouseReleased

    private void btnLuuGtGkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuGtGkActionPerformed
        // TODO add your handling code here:
        System.out.println();
        String selectedGiamKhao;
        String selectedGiamThi;
        Long GiamKhaoId;
        Long GiamThiId;
        
        int rowindex = tblPhongThi.getSelectedRow();
        if (rowindex >=0)
       {
          Long RoomId = Long.parseLong(tblPhongThi.getValueAt(rowindex,0).toString());

          if(comboBoxGiamKhao.getSelectedItem() != null && comboBoxGiamThi.getSelectedItem() != null)
          {   
              selectedGiamKhao = comboBoxGiamKhao.getSelectedItem().toString();
              selectedGiamThi = comboBoxGiamThi.getSelectedItem().toString();
              System.out.println(comboBoxGiamKhao.getSelectedItem().toString());
              System.out.println(comboBoxGiamThi.getSelectedItem().toString());
              if (!selectedGiamKhao.equals("Trống"))
              GiamKhaoId = Long.parseLong(selectedGiamKhao.substring(0, selectedGiamKhao.indexOf(" - ")));
              else GiamKhaoId = null;

              if (!selectedGiamThi.equals("Trống"))
              GiamThiId = Long.parseLong(selectedGiamThi.substring(0, selectedGiamThi.indexOf(" - ")));
              else GiamThiId = null;

              if (GiamKhaoId != GiamThiId){
                  ExamRoom room = examRoomBLL.findById(RoomId);
                  room.setExaminerId(GiamKhaoId);
                  room.setProctorId(GiamThiId);
                  examRoomBLL.update(room);
                  JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                  loadTableData();
              } else {
                  JOptionPane.showMessageDialog(this, "Giám thị và giám khảo trùng nhau", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
              }

          } else {
              System.out.println("run");
              GiamKhaoId = null;
              GiamThiId = null;
              ExamRoom room = examRoomBLL.findById(RoomId);
              room.setExaminerId(GiamKhaoId);
              room.setProctorId(GiamThiId);
              examRoomBLL.update(room);
              JOptionPane.showMessageDialog(this, "Lưu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
              loadTableData();
          }
         
       } else JOptionPane.showMessageDialog(this, "Hãy chọn 1 phòng để tiếp tục", "Thông báo", JOptionPane.INFORMATION_MESSAGE); 
        
        
        
    }//GEN-LAST:event_btnLuuGtGkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuGtGk;
    private javax.swing.JButton btnLưu;
    private javax.swing.JButton btnTaoKhoaThiNhanh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemVaoPhongThi;
    private javax.swing.JButton btnXoaKhoiPhongThi;
    private javax.swing.JComboBox<String> comboBoxGiamKhao;
    private javax.swing.JComboBox<String> comboBoxGiamThi;
    private javax.swing.JComboBox<String> comboBoxKhoaThi;
    private javax.swing.JMenuItem itemSua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleSoLuong;
    private javax.swing.JLabel lblTitleSoLuong1;
    private javax.swing.JLabel lblTitleSoLuong2;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPopupMenu rightClickMenu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JTable tblPhongThi;
    private javax.swing.JTable tblThiSinhChuaXepPhong;
    private javax.swing.JTable tblThiSinhDaXepPhong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

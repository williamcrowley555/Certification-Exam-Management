/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.gui.form;

import com.certification_exam.bll.IDoanBLL;
import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.bll.IExamineBLL;
import com.certification_exam.bll.ITourBLL;
import com.certification_exam.bll.impl.DoanBLL;
import com.certification_exam.bll.impl.ExamCourseBLL;
import com.certification_exam.bll.impl.ExamRoomBLL;
import com.certification_exam.bll.impl.ExamineBLL;
import com.certification_exam.bll.impl.TourBLL;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.dto.Examine;
import com.certification_exam.dto.TourDTO;
import com.certification_exam.gui.menu.MyComboBoxEditor;
import com.certification_exam.gui.menu.MyComboBoxRenderer;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.certification_exam.gui.menu.MyScrollBarUI;
import com.certification_exam.gui.popup.PopUpCapVaiTroGUI;
import com.certification_exam.gui.popup.PopUpPhongThiGUI;
import com.certification_exam.gui.popup.PopUpDsNguoiDiGUI;
import com.certification_exam.gui.popup.PopUpKhoaThiGUI;
import com.certification_exam.gui.popup.PopUpTableChonThiSinhChoPhongThiGUI;
import com.certification_exam.util.PhongThiTableLoaderUtil;
import com.certification_exam.util.TableSetupUtil;
import com.certification_exam.util.ThiSinhXepPhongTableLoaderUtil;
import java.util.ArrayList;
import java.util.List;
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
    private PopUpTableChonThiSinhChoPhongThiGUI popUpTableChonThiSinh;
    private PopUpCapVaiTroGUI popUpCapVaiTro;
    private PopUpPhongThiGUI popUp = null;
    private PopUpKhoaThiGUI popUpKhoaThi = null;
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
    private ArrayList<Examine> DsThiSinhChuaXepPhong = null;
    private ArrayList<Examine> DsThiSinhDaXepPhong = null;
    TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyPhongThi() {
        initComponents();
        examRoomBLL = new ExamRoomBLL();
        examCourseBLL = new ExamCourseBLL();
        examineBLL = new ExamineBLL();
        setComboBox(comboBoxKhoaThi, getExamCourseItems());
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(14,142,233));
        loadTableData();
        headerColor(14,142,233,tblPhongThi);
        initNullTable();
        scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
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
        btnListThiSinh = new javax.swing.JButton();
        btnCapVaiTro = new javax.swing.JButton();
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
        btnThem1 = new javax.swing.JButton();
        btnThem2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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
        btnThem.setText("Thêm");
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

        btnListThiSinh.setBackground(new java.awt.Color(14, 142, 233));
        btnListThiSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnListThiSinh.setForeground(new java.awt.Color(255, 255, 255));
        btnListThiSinh.setText("Xếp Phòng Thi");
        btnListThiSinh.setContentAreaFilled(false);
        btnListThiSinh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListThiSinh.setOpaque(true);
        btnListThiSinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnListThiSinhMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnListThiSinhMouseReleased(evt);
            }
        });
        btnListThiSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListThiSinhActionPerformed(evt);
            }
        });

        btnCapVaiTro.setBackground(new java.awt.Color(14, 142, 233));
        btnCapVaiTro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCapVaiTro.setForeground(new java.awt.Color(255, 255, 255));
        btnCapVaiTro.setText("Cấp Vai Trò Giáo Viên");
        btnCapVaiTro.setContentAreaFilled(false);
        btnCapVaiTro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapVaiTro.setOpaque(true);
        btnCapVaiTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCapVaiTroMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCapVaiTroMouseReleased(evt);
            }
        });
        btnCapVaiTro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapVaiTroActionPerformed(evt);
            }
        });

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
                .addGap(30, 30, 30)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnListThiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapVaiTro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(lblTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlHeadLayout.createSequentialGroup()
                        .addComponent(btnTaoKhoaThiNhanh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboBoxKhoaThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnListThiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCapVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoKhoaThiNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
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

        btnThem1.setBackground(new java.awt.Color(14, 142, 233));
        btnThem1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem1.setForeground(new java.awt.Color(255, 255, 255));
        btnThem1.setText("Thêm");
        btnThem1.setContentAreaFilled(false);
        btnThem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem1.setOpaque(true);
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThem1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnThem1MouseReleased(evt);
            }
        });
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnThem2.setBackground(new java.awt.Color(14, 142, 233));
        btnThem2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem2.setForeground(new java.awt.Color(255, 255, 255));
        btnThem2.setText("Xóa");
        btnThem2.setContentAreaFilled(false);
        btnThem2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem2.setOpaque(true);
        btnThem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThem2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnThem2MouseReleased(evt);
            }
        });
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thí sinh đã xếp vào phòng");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thí sinh chưa xếp vào phòng");

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 748, Short.MAX_VALUE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
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
        tblThiSinhChuaXepPhong.setModel(new ThiSinhXepPhongTableLoaderUtil().setTable(examineBLL.findByExamCourseIdNotInExamRoomId(CourseId, RoomId), this.thiSinhColumnNames));
        headerColor(14,142,233,tblThiSinhChuaXepPhong);
       // DsThiSinhChuaXepPhong = new ArrayList<Examine>();
        //System.out.println(DsThiSinhChuaXepPhong);
        
    }//GEN-LAST:event_tblPhongThiMouseReleased

    private void btnListThiSinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListThiSinhMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListThiSinhMousePressed

    private void btnListThiSinhMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListThiSinhMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListThiSinhMouseReleased

    private void btnListThiSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListThiSinhActionPerformed
        // TODO add your handling code here:    
        if (this.popUpTableChonThiSinh == null) {
            // popUpTableChonThiSinh = new PopUpTableChonThiSinhChoPhongThiGUI("POST");
             popUpTableChonThiSinh.setVisible(true);
            
        } else {
            this.popUpTableChonThiSinh.toFront();
            this.popUpTableChonThiSinh.center();
        }
        popUpTableChonThiSinh.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUpTableChonThiSinh = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_btnListThiSinhActionPerformed

    private void btnCapVaiTroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapVaiTroMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapVaiTroMousePressed

    private void btnCapVaiTroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapVaiTroMouseReleased
     
    }//GEN-LAST:event_btnCapVaiTroMouseReleased

    private void btnCapVaiTroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapVaiTroActionPerformed
         if (this.popUpCapVaiTro == null) {
             popUpCapVaiTro = new PopUpCapVaiTroGUI("POST");
             popUpCapVaiTro.setVisible(true);
            
        } else {
            this.popUpCapVaiTro.toFront();
            this.popUpCapVaiTro.center();
        }
        popUpCapVaiTro.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            popUpCapVaiTro = null;
            loadTableData();
        }
    });
    }//GEN-LAST:event_btnCapVaiTroActionPerformed

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

    private void btnThem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MousePressed

    private void btnThem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1MouseReleased

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem2MousePressed

    private void btnThem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem2MouseReleased

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapVaiTro;
    private javax.swing.JButton btnListThiSinh;
    private javax.swing.JButton btnTaoKhoaThiNhanh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JComboBox<String> comboBoxKhoaThi;
    private javax.swing.JMenuItem itemSua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblTimKiem;
    private javax.swing.JLabel lblTitle;
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

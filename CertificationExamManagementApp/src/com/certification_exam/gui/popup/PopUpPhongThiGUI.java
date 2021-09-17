/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.gui.popup;

import com.github.lgooddatepicker.components.DatePicker;
import com.toedter.calendar.JTextFieldDateEditor;
import com.certification_exam.bll.IDiaDiemBLL;
import com.certification_exam.bll.IDiaDiemBLL;
import com.certification_exam.bll.IDoanBLL;
import com.certification_exam.bll.IEnglishLevelBLL;
import com.certification_exam.bll.IExamCourseBLL;
import com.certification_exam.bll.IExamRoomBLL;
import com.certification_exam.bll.IGiaTourBLL;
import com.certification_exam.bll.IKhachHangBLL;
import com.certification_exam.bll.ITinhBLL;
import com.certification_exam.bll.ITinhBLL;
import com.certification_exam.bll.ITourBLL;
import com.certification_exam.bll.IVaiTroBLL;
import com.certification_exam.bll.impl.DiaDiemBLL;
import com.certification_exam.bll.impl.DiaDiemBLL;
import com.certification_exam.bll.impl.DoanBLL;
import com.certification_exam.bll.impl.EnglishLevelBLL;
import com.certification_exam.bll.impl.ExamCourseBLL;
import com.certification_exam.bll.impl.ExamRoomBLL;
import com.certification_exam.bll.impl.GiaTourBLL;
import com.certification_exam.bll.impl.KhachHangBLL;
import com.certification_exam.bll.impl.TinhBLL;
import com.certification_exam.bll.impl.TinhBLL;
import com.certification_exam.bll.impl.TourBLL;
import com.certification_exam.bll.impl.VaiTroBLL;
import com.certification_exam.dto.DiaDiemDTO;
import com.certification_exam.dto.DiaDiemDTO;
import com.certification_exam.dto.DoanDTO;
import com.certification_exam.dto.ExamCourse;
import com.certification_exam.dto.ExamRoom;
import com.certification_exam.dto.GiaTourDTO;
import com.certification_exam.dto.KhachHangDTO;
import com.certification_exam.dto.NhanVienDTO;
import com.certification_exam.dto.TinhDTO;
import com.certification_exam.dto.TinhDTO;
import com.certification_exam.dto.TourDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import com.certification_exam.gui.menu.MyComboBoxEditor;
import com.certification_exam.gui.menu.MyComboBoxRenderer;
import com.certification_exam.gui.others.TableRowTransferHandler;
import com.certification_exam.util.ImageUtil;
import com.certification_exam.util.InputValidatorUtil;
import com.certification_exam.util.KhachHangTableLoaderUtil;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hi
 */
public class PopUpPhongThiGUI extends javax.swing.JFrame {
    private File selectedImg = null;
    private String action;
    private DoanDTO doan = null;
    private ExamRoom room = null;
    private IDoanBLL doanBLL;
    private ITourBLL tourBLL;
    private IExamCourseBLL examCourseBLL;
    private IExamRoomBLL examRoomBLL;
    private IGiaTourBLL giaTourBLL;
    private ArrayList<KhachHangDTO> listKhach = null;
    private ArrayList<NhanVienDTO> listNhanVien = null;
    private IVaiTroBLL vaiTroBLL;
    private PopUpTableChonGiaGUI popUp;
    private GiaTourDTO giaTour;
    
    
    public PopUpPhongThiGUI(String action) {
        initComponents();
        
        this.action = action;    
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        examCourseBLL = new ExamCourseBLL();
        examRoomBLL = new ExamRoomBLL();
        giaTourBLL = new GiaTourBLL();        
        CustomWindow();
        setComboBox(comboBoxKhoaThi, getKhoaThiItems());
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(14,142,233));
        showGeneratedName();
        disableEditorDateChooser();
        this.setVisible(true);    
    }
    
    public PopUpPhongThiGUI(String action, ExamRoom room) {
        initComponents();
        this.action = action;  
        this.room = room;
       
        examCourseBLL = new ExamCourseBLL();
        examRoomBLL = new ExamRoomBLL();
        
        CustomWindow();
        setComboBox(comboBoxKhoaThi, getKhoaThiItems());
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(14,142,233));
        setLabelText(room);
        comboBoxKhoaThi.disable();
        String selectedTour = comboBoxKhoaThi.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));     
        disableEditorDateChooser();
        this.setVisible(true);    
    }
     
    public void setLabelText(ExamRoom examRoom)
    {
        txtTenPhongThi.setText(examRoom.getName());
        dateTimePicker.setDateTimePermissive( new java.sql.Timestamp(
        examRoom.getExamDate().getTime()).toLocalDateTime());
        comboBoxKhoaThi.setSelectedItem(getKhoaThiItemName(examCourseBLL.findById(room.getExamCourseId())));
    }
    
    
    
    public void disableEditorDateChooser()
    {
        dateTimePicker.getDatePicker().getComponentDateTextField().setEditable(false);
        dateTimePicker.getTimePicker().getComponentTimeTextField().setEditable(false);
    }
    
    public void showGeneratedName()
    {
        IExamRoomBLL examRoomBLL = new ExamRoomBLL();
        IExamCourseBLL examCourseBLL = new ExamCourseBLL();
        IEnglishLevelBLL englishLevelBLL = new EnglishLevelBLL();
        Long selectedCourseId = Long.parseLong(comboBoxKhoaThi.getSelectedItem().toString().substring(0,1));
        Long selectedCourseEnglishLevelId = examCourseBLL.findById(selectedCourseId).getEnglishLevelId();
        String selectedCourseEnglishLevelName = englishLevelBLL.findById(selectedCourseEnglishLevelId).getName();
        String stt = examRoomBLL.getGreatestOrdinalNumber(selectedCourseEnglishLevelName);
        
        String name = selectedCourseEnglishLevelName + "P" + "01";
       
        if (stt != null) {
            Integer nextNumber = Integer.valueOf(stt) + 1;
            String strNumber = nextNumber > 9 ? String.valueOf(nextNumber) : "0" + nextNumber;
            name = selectedCourseEnglishLevelName + "P" + strNumber;
        }
        
        txtTenPhongThi.setText(name);
    }
    
    
     public boolean validateForm() 
    {   
        
        boolean NgayThi, GioThi; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/certification_exam/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/certification_exam/img/error.png"));
        
        
        
        if (InputValidatorUtil.isValidStartDate(dateTimePicker.getDatePicker().getDate(), "Ngày thi không hợp lệ").isEmpty())  
        {
            String selectedCourse = comboBoxKhoaThi.getSelectedItem().toString();
            Long idCourse = Long.parseLong(selectedCourse.substring(0, selectedCourse.indexOf(" - ")));
            examCourseBLL.findById(idCourse);
            NgayThi = true;
            lblValidateNgayThi.setIcon(iconCheck);
            lblValidateNgayThi.setToolTipText(null);
        } else {
            NgayThi = false;
            lblValidateNgayThi.setIcon(iconError);
            lblValidateNgayThi.setToolTipText(InputValidatorUtil.isValidStartDate(dateTimePicker.getDatePicker().getDate(), "Ngày thi không hợp lệ"));
        }
        
      
//        if (TenDoan && StartDate)
//        return true;
//        else return false;
        
        return true;
       
    }
    
    private ExamRoom getFormInfo() throws IOException {
        ExamRoom room = new ExamRoom();
        
        room.setExamDate(java.sql.Timestamp.valueOf( dateTimePicker.getDateTimePermissive() ));
        String selectedCourse = comboBoxKhoaThi.getSelectedItem().toString();
        Long idCourse = Long.parseLong(selectedCourse.substring(0, selectedCourse.indexOf(" - ")));
        room.setExamCourseId(idCourse);
        
        if(this.room != null) {
            room.setId(this.room.getId());
            room.setName(this.room.getName());
            room.setProctorId(this.room.getProctorId() == 0 ? null : this.room.getProctorId());
            room.setExaminerId(this.room.getExaminerId() == 0 ? null : this.room.getExaminerId());
            room.setExamCourseId(this.room.getExamCourseId());
        }
        
        return room;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getKhoaThiItems() {
        List<ExamCourse> examCourseLists = examCourseBLL.findAll();
        String[] examCourseItems = new String[examCourseLists.size()];
        int index = 0;
        for(ExamCourse vt : examCourseLists) {
            examCourseItems[index] = vt.getId() + " - " + vt.getName();
            ++ index;
        }
        return examCourseItems;
    }
    
    public String getKhoaThiItemName(ExamCourse examCourse) {
        return examCourse.getId() + " - " + examCourse.getName();
    }
    
    
    
    public void  setGiaTour(GiaTourDTO giaTour)
    {
        this.giaTour = giaTour;
       
    }
    
    public void resetGiaTour()
    {
        this.giaTour = null;
       
    }
    public PopUpPhongThiGUI() {
        initComponents();
        CustomWindow();
    }
   
    public void CustomWindow()
    {   
        Color flatBlue = new Color(14,142,233);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlue));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(240,240,240));
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
    
    public void center()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
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

        btnGroupGioiTinh = new javax.swing.ButtonGroup();
        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        txtTenPhongThi = new javax.swing.JTextField();
        lblChonKhoaThi = new javax.swing.JLabel();
        lblTenPhongThi = new javax.swing.JLabel();
        comboBoxKhoaThi = new javax.swing.JComboBox<>();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateNgayThi = new javax.swing.JLabel();
        lblValidateTenPhongThi = new javax.swing.JLabel();
        lblNgayThi = new javax.swing.JLabel();
        dateTimePicker = new com.github.lgooddatepicker.components.DateTimePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(45, 113, 248));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 40));
        panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHeaderMouseDragged(evt);
            }
        });

        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setBackground(new java.awt.Color(255, 255, 255));
        lblMinimize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });

        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addComponent(lblMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblExit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        txtTenPhongThi.setEditable(false);
        txtTenPhongThi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtTenPhongThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenPhongThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPhongThiActionPerformed(evt);
            }
        });

        lblChonKhoaThi.setText("Chọn Khóa Thi:");
        lblChonKhoaThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblTenPhongThi.setText("Tên Phòng Thi:");
        lblTenPhongThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboBoxKhoaThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        comboBoxKhoaThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBoxKhoaThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxKhoaThiActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(14, 142, 233));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/gui/popup/save_icon.png"))); // NOI18N
        btnLuu.setText(" Lưu");
        btnLuu.setBorder(null);
        btnLuu.setContentAreaFilled(false);
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.setOpaque(true);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(14, 142, 233));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/certification_exam/gui/popup/cancel_icon.png"))); // NOI18N
        btnHuy.setText(" Hủy");
        btnHuy.setBorder(null);
        btnHuy.setContentAreaFilled(false);
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setOpaque(true);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNgayThi.setText("Ngày Thi:");
        lblNgayThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateTimePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenPhongThi)
                    .addComponent(comboBoxKhoaThi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblNgayThi)
                                .addGap(188, 188, 188)
                                .addComponent(lblValidateNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblTenPhongThi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValidateTenPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblChonKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(comboBoxKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblChonKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenPhongThi)
                    .addComponent(lblValidateTenPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblValidateNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
       this.dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void panelHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHeaderMouseDragged
        setLocation (evt.getXOnScreen()-(getWidth()/2),evt.getYOnScreen()-10);
    }//GEN-LAST:event_panelHeaderMouseDragged

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            ExamRoom newRoom = null;
            try {
                newRoom = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpPhongThiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {
                Long newRoomId = examRoomBLL.save(newRoom);
                if(newRoomId != null) {

                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else if(this.action.equals("PUT")) {
                try {
                    examRoomBLL.update(newRoom);
                    System.out.println(newRoom);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch(Exception e) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void comboBoxKhoaThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKhoaThiActionPerformed
         showGeneratedName();
    }//GEN-LAST:event_comboBoxKhoaThiActionPerformed

    private void txtTenPhongThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhongThiActionPerformed

    }//GEN-LAST:event_txtTenPhongThiActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PopUpPhongThiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpPhongThiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpPhongThiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpPhongThiGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpPhongThiGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxKhoaThi;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker;
    private javax.swing.JLabel lblChonKhoaThi;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblNgayThi;
    private javax.swing.JLabel lblTenPhongThi;
    private javax.swing.JLabel lblValidateNgayThi;
    private javax.swing.JLabel lblValidateTenPhongThi;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextField txtTenPhongThi;
    // End of variables declaration//GEN-END:variables
}

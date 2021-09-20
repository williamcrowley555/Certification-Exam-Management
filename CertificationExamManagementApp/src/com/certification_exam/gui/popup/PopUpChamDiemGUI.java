/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.gui.popup;

import com.certification_exam.bll.IExamBLL;
import com.certification_exam.bll.IExamineBLL;
import com.certification_exam.bll.impl.ExamBLL;
import com.certification_exam.bll.impl.ExamineBLL;
import com.certification_exam.dto.Exam;
import com.certification_exam.dto.Examine;
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
import com.certification_exam.util.InputValidatorUtil;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Hi
 */
public class PopUpChamDiemGUI extends javax.swing.JFrame {
    private String action;
    private Exam exam;
    private IExamBLL examBLL;
    private IExamineBLL examineBLL;
  

    public PopUpChamDiemGUI(Exam exam) {
        initComponents();
        this.action = action;
        this.exam = exam;
        examBLL = new ExamBLL();
        examineBLL = new ExamineBLL();
        CustomWindow();
        setLabelText(exam);
        this.setVisible(true);
    }
    
    public void setLabelText(Exam exam)
    {   
        Examine examine = new Examine();
        examine = examineBLL.findById(exam.getExamineId());
        txtTenThiSinh.setText(examine.getLastName() + " " + examine.getFirstName());
        txtDiemNghe.setText(String.valueOf(exam.getListeningGrade()));
        txtDiemNoi.setText(String.valueOf(exam.getSpeakingGrade()));
        txtDiemDoc.setText(String.valueOf(exam.getReadingGrade()));
        txtDiemViet.setText(String.valueOf(exam.getWritingGrade()));
        if(exam.getStatus() == 2)
        lblTrangThai.setText("Đã chấm điểm");
        else lblTrangThai.setText("Chưa chấm điểm");
    }
    
    private Exam getFormInfo() {
        Exam exam = new Exam();
        if(this.exam != null) {
            exam.setId(this.exam.getId());
            exam.setExamineId(this.exam.getExamineId());
            exam.setExamRoomId(this.exam.getExamRoomId());
        }
      exam.setSpeakingGrade(Integer.parseInt(txtDiemNoi.getText()));
      exam.setReadingGrade(Integer.parseInt(txtDiemDoc.getText()));
      exam.setWritingGrade(Integer.parseInt(txtDiemViet.getText()));
      exam.setListeningGrade(Integer.parseInt(txtDiemNghe.getText()));
       
      return exam;
    }
 
    public PopUpChamDiemGUI() {
        initComponents();
        CustomWindow();
    }
    
    public boolean validateForm() 
    {
        boolean doc,nghe, noi, viet; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/certification_exam/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/certification_exam/img/error.png"));
         
        if (InputValidatorUtil.isVailidNumber(txtDiemDoc.getText(), 0, 100).isEmpty())  
        {
            doc = true;
            lblValidateDiemDoc.setIcon(iconCheck);
            lblValidateDiemDoc.setToolTipText(null);
        } else {
            doc = false;
            lblValidateDiemDoc.setIcon(iconError);
            lblValidateDiemDoc.setToolTipText(InputValidatorUtil.isVailidNumber(txtDiemDoc.getText(), 0, 100));
        } 
        
        if (InputValidatorUtil.isVailidNumber(txtDiemNghe.getText(), 0, 100).isEmpty())  
        {
            nghe = true;
            lblValidateDiemNghe.setIcon(iconCheck);
            lblValidateDiemNghe.setToolTipText(null);
        } else {
            nghe = false;
            lblValidateDiemNghe.setIcon(iconError);
            lblValidateDiemNghe.setToolTipText(InputValidatorUtil.isVailidNumber(txtDiemNghe.getText(), 0, 100));
        }  
        
        if (InputValidatorUtil.isVailidNumber(txtDiemNoi.getText(), 0, 100).isEmpty())  
        {
            noi = true;
            lblValidateDiemNoi.setIcon(iconCheck);
            lblValidateDiemNoi.setToolTipText(null);
        } else {
            noi = false;
            lblValidateDiemNoi.setIcon(iconError);
            lblValidateDiemNoi.setToolTipText(InputValidatorUtil.isVailidNumber(txtDiemNoi.getText(), 0, 100));
        } 
        
        if (InputValidatorUtil.isVailidNumber(txtDiemViet.getText(), 0, 100).isEmpty())  
        {
            viet = true;
            lblValidateDiemViet.setIcon(iconCheck);
            lblValidateDiemViet.setToolTipText(null);
        } else {
            viet = false;
            lblValidateDiemViet.setIcon(iconError);
            lblValidateDiemViet.setToolTipText(InputValidatorUtil.isVailidNumber(txtDiemViet.getText(), 0, 100));
        } 
        
        if (nghe && noi && doc & viet)
        return true;
        else return false; 
    }
    public void center()
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void CustomWindow()
    {   
        Color flatBlue = new Color(14,142,233);  
        
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0,1,1,1, flatBlue));   
        center();
        lblMinimize.setText("\u2014");
        lblExit.setText("X");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblTenDichVu = new javax.swing.JLabel();
        txtTenThiSinh = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblValidateDiemNghe = new javax.swing.JLabel();
        lblMoTa = new javax.swing.JLabel();
        AreaScrollPane = new javax.swing.JScrollPane();
        txtDiemNghe = new javax.swing.JTextArea();
        lblMoTa1 = new javax.swing.JLabel();
        AreaScrollPane1 = new javax.swing.JScrollPane();
        txtDiemNoi = new javax.swing.JTextArea();
        lblMoTa2 = new javax.swing.JLabel();
        AreaScrollPane2 = new javax.swing.JScrollPane();
        txtDiemDoc = new javax.swing.JTextArea();
        lblMoTa3 = new javax.swing.JLabel();
        AreaScrollPane3 = new javax.swing.JScrollPane();
        txtDiemViet = new javax.swing.JTextArea();
        lblTrangThai = new javax.swing.JLabel();
        lblValidateDiemDoc = new javax.swing.JLabel();
        lblValidateDiemViet = new javax.swing.JLabel();
        lblValidateDiemNoi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        panelHeader.setBackground(new java.awt.Color(45, 113, 248));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 40));
        panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelHeaderMouseDragged(evt);
            }
        });

        lblMinimize.setBackground(new java.awt.Color(255, 255, 255));
        lblMinimize.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMinimize.setForeground(new java.awt.Color(255, 255, 255));
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseClicked(evt);
            }
        });

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lblTenDichVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTenDichVu.setText("Tên thí sinh:");

        txtTenThiSinh.setEditable(false);
        txtTenThiSinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenThiSinh.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));

        btnLuu.setBackground(new java.awt.Color(45, 113, 248));
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

        btnHuy.setBackground(new java.awt.Color(45, 113, 248));
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

        lblMoTa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMoTa.setText("Điểm Nghe");

        AreaScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiemNghe.setColumns(20);
        txtDiemNghe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiemNghe.setRows(5);
        txtDiemNghe.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane.setViewportView(txtDiemNghe);

        lblMoTa1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMoTa1.setText("Điểm Nói");

        AreaScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiemNoi.setColumns(20);
        txtDiemNoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiemNoi.setRows(5);
        txtDiemNoi.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane1.setViewportView(txtDiemNoi);

        lblMoTa2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMoTa2.setText("Điểm Đọc");

        AreaScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiemDoc.setColumns(20);
        txtDiemDoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiemDoc.setRows(5);
        txtDiemDoc.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane2.setViewportView(txtDiemDoc);

        lblMoTa3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMoTa3.setText("Điểm Viết");

        AreaScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        AreaScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtDiemViet.setColumns(20);
        txtDiemViet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDiemViet.setRows(5);
        txtDiemViet.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 2, 2, new java.awt.Color(204, 204, 204)));
        AreaScrollPane3.setViewportView(txtDiemViet);

        lblTrangThai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTrangThai.setText("Trạng thái:");

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AreaScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblMoTa3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblValidateDiemViet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AreaScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblMoTa2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblValidateDiemDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AreaScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblMoTa1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblValidateDiemNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenThiSinh)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblValidateDiemNghe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTenDichVu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenThiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMoTa)
                    .addComponent(lblValidateDiemNghe, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(AreaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMoTa1)
                    .addComponent(lblValidateDiemNoi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(AreaScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMoTa2))
                            .addComponent(lblValidateDiemDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(AreaScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMoTa3))
                    .addComponent(lblValidateDiemViet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(AreaScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {    
            Exam exam = getFormInfo();

           // if(this.action.equals("POST")) {             
                    examBLL.grade(exam);
                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

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
            java.util.logging.Logger.getLogger(PopUpChamDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpChamDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpChamDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpChamDiemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PopUpChamDiemGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AreaScrollPane;
    private javax.swing.JScrollPane AreaScrollPane1;
    private javax.swing.JScrollPane AreaScrollPane2;
    private javax.swing.JScrollPane AreaScrollPane3;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblMoTa1;
    private javax.swing.JLabel lblMoTa2;
    private javax.swing.JLabel lblMoTa3;
    private javax.swing.JLabel lblTenDichVu;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblValidateDiemDoc;
    private javax.swing.JLabel lblValidateDiemNghe;
    private javax.swing.JLabel lblValidateDiemNoi;
    private javax.swing.JLabel lblValidateDiemViet;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextArea txtDiemDoc;
    private javax.swing.JTextArea txtDiemNghe;
    private javax.swing.JTextArea txtDiemNoi;
    private javax.swing.JTextArea txtDiemViet;
    private javax.swing.JTextField txtTenThiSinh;
    // End of variables declaration//GEN-END:variables

}

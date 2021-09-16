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
import com.certification_exam.bll.IGiaTourBLL;
import com.certification_exam.bll.IKhachHangBLL;
import com.certification_exam.bll.ITinhBLL;
import com.certification_exam.bll.ITinhBLL;
import com.certification_exam.bll.ITourBLL;
import com.certification_exam.bll.IVaiTroBLL;
import com.certification_exam.bll.impl.DiaDiemBLL;
import com.certification_exam.bll.impl.DiaDiemBLL;
import com.certification_exam.bll.impl.DoanBLL;
import com.certification_exam.bll.impl.GiaTourBLL;
import com.certification_exam.bll.impl.KhachHangBLL;
import com.certification_exam.bll.impl.TinhBLL;
import com.certification_exam.bll.impl.TinhBLL;
import com.certification_exam.bll.impl.TourBLL;
import com.certification_exam.bll.impl.VaiTroBLL;
import com.certification_exam.dto.DiaDiemDTO;
import com.certification_exam.dto.DiaDiemDTO;
import com.certification_exam.dto.DoanDTO;
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
import java.util.ArrayList;
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
    private IDoanBLL doanBLL;
    private ITourBLL tourBLL;
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
        giaTourBLL = new GiaTourBLL();        
        CustomWindow();
       // setComboBox(comboBoxKhoaThi, getTourItems());
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(14,142,233));
        disableEditorDateChooser();
        this.setVisible(true);    
    }
    
    public PopUpPhongThiGUI(String action, DoanDTO doan) {
        initComponents();
        this.action = action;  
        this.doan = doan;
        doanBLL = new DoanBLL();
        tourBLL = new TourBLL();
        giaTourBLL = new GiaTourBLL();
        CustomWindow();
        setComboBox(comboBoxKhoaThi, getTourItems());
        comboBoxKhoaThi = myComboBox(comboBoxKhoaThi, new Color(14,142,233));
        setLabelText(doan);
        String selectedTour = comboBoxKhoaThi.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));     
        disableEditorDateChooser();
        this.setVisible(true);    
    }
     
    public void setLabelText(DoanDTO doan)
    {
        txtTenPhongThi.setText(doan.getTenDoan());
        DCNgayThi.setDate(doan.getNgayKhoiHanh());
        
    }
    
    
    
    public void disableEditorDateChooser()
    {
        DCNgayThi.getComponentDateTextField().setEditable(false);
       
    }
    
    
     public boolean validateForm() 
    {   
        
        boolean TenDoan, StartDate, EndDate, GiaTour = false; 
        ImageIcon iconCheck = new ImageIcon(getClass().getResource("/com/tourdulich/img/check.png"));
        ImageIcon iconError = new ImageIcon(getClass().getResource("/com/tourdulich/img/error.png"));
        
        if (InputValidatorUtil.isValidPattern(txtTenPhongThi.getText(), "[^A-Za-z0-9\\-\\s]", "Tên đoàn không hợp lệ").isEmpty())  
        {
            TenDoan = true;
            lblValidateTenPhongThi.setIcon(iconCheck);
            lblValidateTenPhongThi.setToolTipText(null);
        } else {
            TenDoan = false;
            lblValidateTenPhongThi.setIcon(iconError);
            lblValidateTenPhongThi.setToolTipText(InputValidatorUtil.isValidPattern(txtTenPhongThi.getText(), "[^A-Za-z0-9\\-\\s]", "Tên đoàn không hợp lệ"));
        }
        
        if (InputValidatorUtil.isValidStartDate(DCNgayThi.getDate()).isEmpty())  
        {
            StartDate = true;
            lblValidateNgayThi.setIcon(iconCheck);
            lblValidateNgayThi.setToolTipText(null);
        } else {
            StartDate = false;
            lblValidateNgayThi.setIcon(iconError);
            lblValidateNgayThi.setToolTipText(InputValidatorUtil.isValidStartDate(DCNgayThi.getDate()));
        }
        
      
        if (TenDoan && StartDate)
        return true;
        else return false;
       
    }
    
    private DoanDTO getFormInfo() throws IOException {
        DoanDTO doan = new DoanDTO();
        if(this.doan != null) {
            doan.setId(this.doan.getId());
        }
        doan.setTenDoan(txtTenPhongThi.getText().trim());
        doan.setNgayKhoiHanh(DCNgayThi.getDate());
 
        String selectedTour = comboBoxKhoaThi.getSelectedItem().toString();
        Long idTour = Long.parseLong(selectedTour.substring(0, selectedTour.indexOf(" - ")));
        doan.setIdTour(idTour);
        return doan;
    }
    
    public void setComboBox(JComboBox<String> comboBox, String[] listItems) {
        comboBox.setModel(new DefaultComboBoxModel<>(listItems));
    } 
    
    public String[] getTourItems() {
        List<TourDTO> tourLists = tourBLL.findAll();
        String[] tourItems = new String[tourLists.size()];
        int index = 0;
        for(TourDTO vt : tourLists) {
            tourItems[index] = vt.getId() + " - " + vt.getTenTour();
            ++ index;
        }
        return tourItems;
    }
    
    public String getTourItemName(TourDTO tour) {
        return tour.getId() + " - " + tour.getTenTour();
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
        DCNgayThi = new com.github.lgooddatepicker.components.DatePicker();
        lblNgayThi = new javax.swing.JLabel();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();

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

        DCNgayThi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DCNgayThiFocusLost(evt);
            }
        });
        DCNgayThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DCNgayThiMouseExited(evt);
            }
        });
        DCNgayThi.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                DCNgayThiInputMethodTextChanged(evt);
            }
        });
        DCNgayThi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DCNgayThiPropertyChange(evt);
            }
        });

        lblNgayThi.setText("Ngày Thi:");
        lblNgayThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSoLuong.setText("Số lượng:");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSoLuong.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(204, 204, 204)));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoLuong)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblNgayThi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblValidateNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(DCNgayThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(29, 29, 29))))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblTenPhongThi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValidateTenPhongThi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTenPhongThi, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboBoxKhoaThi, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblChonKhoaThi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
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
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuong)
                            .addComponent(lblNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DCNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblValidateNgayThi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
            .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void DCNgayThiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DCNgayThiPropertyChange

    }//GEN-LAST:event_DCNgayThiPropertyChange

    private void DCNgayThiInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_DCNgayThiInputMethodTextChanged

    }//GEN-LAST:event_DCNgayThiInputMethodTextChanged

    private void DCNgayThiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DCNgayThiMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_DCNgayThiMouseExited

    private void DCNgayThiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DCNgayThiFocusLost

    }//GEN-LAST:event_DCNgayThiFocusLost

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm())
        {
            DoanDTO newDoan = null;
            try {
                newDoan = getFormInfo();
            } catch (IOException ex) {
                Logger.getLogger(PopUpPhongThiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(this.action.equals("POST")) {
                Long newDoanId = doanBLL.save(newDoan);
                if(newDoanId != null) {

                    JOptionPane.showMessageDialog(this, "Lưu thành công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!!!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } else if(this.action.equals("PUT")) {
                try {
                    doanBLL.update(newDoan);
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
        resetGiaTour();
        DCNgayThi.setDate(null);
       

    }//GEN-LAST:event_comboBoxKhoaThiActionPerformed

    private void txtTenPhongThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhongThiActionPerformed

    }//GEN-LAST:event_txtTenPhongThiActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed
    
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
    private com.github.lgooddatepicker.components.DatePicker DCNgayThi;
    private javax.swing.ButtonGroup btnGroupGioiTinh;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JComboBox<String> comboBoxKhoaThi;
    private javax.swing.JLabel lblChonKhoaThi;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblNgayThi;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenPhongThi;
    private javax.swing.JLabel lblValidateNgayThi;
    private javax.swing.JLabel lblValidateTenPhongThi;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenPhongThi;
    // End of variables declaration//GEN-END:variables
}

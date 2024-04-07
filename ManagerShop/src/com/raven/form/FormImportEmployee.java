/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.fpt.DAO.AccountDao;
import com.fpt.DAO.EmpolyeeDao;
import com.fpt.DAO.UserDAO;
import com.fpt.Validate.Validate;
import com.fpt.entity.Account;
import com.fpt.Validate.labelValidate;
import com.fpt.entity.User;
import com.fpt.utils.MsgBox;
import com.fpt.utils.XDate;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ducit
 */
public class FormImportEmployee extends javax.swing.JPanel {

    /**
     * Creates new form FormProducts
     */
    public FormImportEmployee() {
        initComponents();
        setOpaque(false);

    }

    UserDAO daoE = new UserDAO();
    AccountDao daoA = new AccountDao();

    public boolean checkDate() {
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse(txtBirth.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int years = Period.between(date, today).getYears();
        if (years < 18) {
            MsgBox.labelAlert(lblBirth, txtBirth, "Trên 18 tuổi");
            System.out.println(years);
            return false;
        }
        System.out.println(years);
        return true;
    }

    EmpolyeeDao emDao = new EmpolyeeDao();

    public boolean checkUser(String acc) {
        for (int i = 0; i < emDao.selectAll().size(); i++) {
            if (emDao.selectAll().get(i).getUsername().equals(acc.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(String acc) {
        for (int i = 0; i < emDao.selectAll().size(); i++) {
            if (emDao.selectAll().get(i).getEmail().trim().equals(acc.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPhoneNumber(String acc) {
        for (int i = 0; i < emDao.selectAll().size(); i++) {
            if (emDao.selectAll().get(i).getPhoneNumber().trim().equals(acc.trim())) {
                return true;
            }
        }
        return false;
    }

    public void insert() {
        try {
            if (labelValidate.checkEmpty(lblName, txtName, "Tên chưa nhập!!!") == false) {
                return;
            } else if (labelValidate.checkPhoneNumber(lblPhone, txtPhone, "") == false) {
                return;
            } else if (checkPhoneNumber(txtPhone.getText()) == true) {
                MsgBox.labelAlert(lblPhone, txtPhone, "Trùng số điện thoại");
                return;
            } else if (labelValidate.checkEmpty(lblBirth, txtBirth, "Ngày sinh chưa nhập!!!") == false) {
                return;
            } else if (checkDate() == false) {
                return;
            } else if (labelValidate.checkEmpty(lblSalary, txtSalary, "Lương chưa nhập!!!") == false) {
                return;
            } else if (labelValidate.checkNumber(lblSalary, txtSalary, "Lương không hợp lệ!!!") == false) {
                return;
            } else if (labelValidate.checkEmail(lblEmail, txtEmail, "") == false) {
                return;
            } else if (checkEmail(txtEmail.getText()) == true) {
                MsgBox.labelAlert(lblEmail, txtEmail, "Trùng email");
                return;
            } else if (labelValidate.checkEmpty(lblUser, txtUsername, "Tài khoản chưa nhập!!!") == false) {
                return;
            } else if (checkUser(txtUsername.getText()) == true) {
                MsgBox.labelAlert(lblUser, txtUsername, "Trùng user");
                return;
            } else if (labelValidate.checkEmpty(lblPass, txtPassWord, "Mật khẩu chưa nhập!!!") == false) {
                return;
            } else {
                User e = getFormE();
                daoE.insert(e);
                Account a = getFormA();
                daoA.insert(a);
                MsgBox.alert(this, "Thêm mới thành công..^^..");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.warring(this, "Thêm mới thất bại!!");
        }
    }

    public User getFormE() {
        User e = new User();
        e.setFullname(txtName.getText());
        e.setRole(rdoManage.isSelected());
        e.setGender(rdoMale.isSelected());
        e.setDateOfBirth(XDate.toDate(txtBirth.getText(), "dd-MM-yyyy"));
        e.setAdress(txtAdress.getText());
        e.setPhoneNumber(txtPhone.getText());
        e.setEmail(txtEmail.getText());
        e.setStatus(rdoWorking.isSelected());
        e.setSalary(Double.parseDouble(txtSalary.getText()));
        return e;
    }

    public Account getFormA() {
        Account a = new Account();
        a.setUserName(txtUsername.getText());
        a.setPassWord(String.valueOf(txtPassWord.getText()));
        return a;
    }

    public void clear() {
        txtName.setText("");
        txtBirth.setText("");
        txtAdress.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtSalary.setText("");
        rdoManage.setSelected(true);
        rdoMale.setSelected(true);
        rdoWorking.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        GroupGender = new javax.swing.ButtonGroup();
        GroupStatus = new javax.swing.ButtonGroup();
        GroupRole = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPhone = new com.raven.suportSwing.TextField();
        txtName = new com.raven.suportSwing.TextField();
        txtEmail = new com.raven.suportSwing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAdress = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtSalary = new com.raven.suportSwing.TextField();
        jLabel3 = new javax.swing.JLabel();
        rdoMale = new com.raven.suportSwing.RadioButtonCustom();
        rdoFeMale = new com.raven.suportSwing.RadioButtonCustom();
        rdoManage = new com.raven.suportSwing.RadioButtonCustom();
        rdoEmpolyee = new com.raven.suportSwing.RadioButtonCustom();
        jLabel4 = new javax.swing.JLabel();
        txtBirth = new com.raven.suportSwing.TextField();
        jLabel5 = new javax.swing.JLabel();
        rdoWorking = new com.raven.suportSwing.RadioButtonCustom();
        rdoLeave = new com.raven.suportSwing.RadioButtonCustom();
        txtUsername = new com.raven.suportSwing.TextField();
        txtPassWord = new com.raven.suportSwing.PasswordField();
        myButton1 = new com.raven.suportSwing.MyButton();
        myButton2 = new com.raven.suportSwing.MyButton();
        lblBirth = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblSalary = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txtBirth);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Thêm Nhân Viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtPhone.setLabelText("Điện thoại");
        txtPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPhoneFocusGained(evt);
            }
        });

        txtName.setLabelText("Tên");
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFocusGained(evt);
            }
        });

        txtEmail.setLabelText("Email");
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });

        txtAdress.setColumns(20);
        txtAdress.setRows(5);
        jScrollPane1.setViewportView(txtAdress);

        jLabel2.setText("Địa chỉ");

        txtSalary.setLabelText("Lương");
        txtSalary.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSalaryFocusGained(evt);
            }
        });

        jLabel3.setText("Giới tính");

        GroupGender.add(rdoMale);
        rdoMale.setSelected(true);
        rdoMale.setText("Nam");

        GroupGender.add(rdoFeMale);
        rdoFeMale.setText("Nữ");

        GroupRole.add(rdoManage);
        rdoManage.setText("Quản lý");

        GroupRole.add(rdoEmpolyee);
        rdoEmpolyee.setSelected(true);
        rdoEmpolyee.setText("Nhân Viên");

        jLabel4.setText("Chức vụ");

        txtBirth.setLabelText("Ngày sinh");
        txtBirth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBirthFocusGained(evt);
            }
        });

        jLabel5.setText("Tình trạng");

        GroupStatus.add(rdoWorking);
        rdoWorking.setSelected(true);
        rdoWorking.setText("Đang làm việc");

        GroupStatus.add(rdoLeave);
        rdoLeave.setText("Nghỉ làm");

        txtUsername.setLabelText("Username");
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
        });

        txtPassWord.setLabelText("Password");
        txtPassWord.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassWordFocusGained(evt);
            }
        });

        myButton1.setText("Thêm ");
        myButton1.setRadius(10);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        myButton2.setText("Làm mới");
        myButton2.setRadius(10);
        myButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton2ActionPerformed(evt);
            }
        });

        lblBirth.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblBirth.setForeground(new java.awt.Color(255, 51, 51));

        lblName.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 51, 51));

        lblPhone.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(255, 51, 51));

        lblSalary.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblSalary.setForeground(new java.awt.Color(255, 51, 51));

        lblEmail.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 51, 51));

        lblUser.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 51, 51));

        lblPass.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblPass.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                            .addComponent(txtSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoWorking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(rdoLeave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)))
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(rdoFeMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(143, 143, 143)
                            .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                            .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1)
                        .addComponent(lblPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPassWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoEmpolyee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(rdoManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoFeMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPass, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoEmpolyee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoManage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoWorking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoLeave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        this.insert();

    }//GEN-LAST:event_myButton1ActionPerformed

    private void myButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton2ActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_myButton2ActionPerformed

    private void txtNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusGained
        // TODO add your handling code here:
        lblName.setText("");
    }//GEN-LAST:event_txtNameFocusGained

    private void txtPhoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneFocusGained
        // TODO add your handling code here:
        lblPhone.setText("");
    }//GEN-LAST:event_txtPhoneFocusGained

    private void txtSalaryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSalaryFocusGained
        // TODO add your handling code here:
        lblSalary.setText("");
    }//GEN-LAST:event_txtSalaryFocusGained

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        // TODO add your handling code here:
        lblEmail.setText("");
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        // TODO add your handling code here:
        lblUser.setText("");
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtPassWordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassWordFocusGained
        // TODO add your handling code here:
        lblPass.setText("");
    }//GEN-LAST:event_txtPassWordFocusGained

    private void txtBirthFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBirthFocusGained
        // TODO add your handling code here:
        lblBirth.setText("");
    }//GEN-LAST:event_txtBirthFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GroupGender;
    private javax.swing.ButtonGroup GroupRole;
    private javax.swing.ButtonGroup GroupStatus;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBirth;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblSalary;
    private javax.swing.JLabel lblUser;
    private com.raven.suportSwing.MyButton myButton1;
    private com.raven.suportSwing.MyButton myButton2;
    private com.raven.suportSwing.RadioButtonCustom rdoEmpolyee;
    private com.raven.suportSwing.RadioButtonCustom rdoFeMale;
    private com.raven.suportSwing.RadioButtonCustom rdoLeave;
    private com.raven.suportSwing.RadioButtonCustom rdoMale;
    private com.raven.suportSwing.RadioButtonCustom rdoManage;
    private com.raven.suportSwing.RadioButtonCustom rdoWorking;
    private javax.swing.JTextArea txtAdress;
    private com.raven.suportSwing.TextField txtBirth;
    private com.raven.suportSwing.TextField txtEmail;
    private com.raven.suportSwing.TextField txtName;
    private com.raven.suportSwing.PasswordField txtPassWord;
    private com.raven.suportSwing.TextField txtPhone;
    private com.raven.suportSwing.TextField txtSalary;
    private com.raven.suportSwing.TextField txtUsername;
    // End of variables declaration//GEN-END:variables

}

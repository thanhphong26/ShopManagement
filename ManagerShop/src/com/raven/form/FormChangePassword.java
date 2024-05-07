/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.sportshop.DAO.AccountDao;
import com.sportshop.Validate.Validate;
import com.sportshop.entity.Account;
import com.sportshop.utils.Auth;
import com.sportshop.utils.MsgBox;

/**
 *
 * @author ducit
 */
public class FormChangePassword extends javax.swing.JPanel {

    /**
     * Creates new form ChangePassword
     */
    public FormChangePassword() {
        initComponents();
        edit();
    }
    AccountDao aDao = new AccountDao();

    public void setForm(Account a) {
        txtUsername.setText(a.getUserName());
        txtPassword.setText(a.getPassWord());
    }

    public void edit() {
        Account a = aDao.selectByIdUser(Auth.user.getIdUser());
        setForm(a);
    }

    Account getForm() {
        Account a = new Account();
        a.setPassWord(new String(txtVerifyPassword.getPassword()));
        return a;
    }

    public void updatePassword() {
        try {
            if (!Validate.checkEmpty(lblNewPassword, txtNewPassword, "Khổng bỏ trống mật khẩu mới")) {
                return;
            } else if (!Validate.checkEmpty(lblVerifyPassword, txtVerifyPassword, "Không bỏ trống xác nhận mật khẩu")) {
                return;
            } else if (!new String(txtNewPassword.getPassword()).equals(new String(txtVerifyPassword.getPassword()))) {
                MsgBox.labelAlert(lblVerifyPassword, txtVerifyPassword, "Mật khẩu không trùng khớp");
                return;
            } else if (new String(txtVerifyPassword.getPassword()).equals(new String(txtPassword.getPassword()))) {
                MsgBox.labelAlert(lblVerifyPassword, txtVerifyPassword, "Mật mới không được trùng mật khẩu trước đó");
                return;
            } else {
                Account a = getForm();
                int idUser = Auth.user.getIdUser();
                a.setIdUser(idUser);
                aDao.update(a);
                MsgBox.alert(this, "Đổi mật khẩu thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtUsername = new com.raven.suportSwing.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new com.raven.suportSwing.PasswordField();
        txtNewPassword = new com.raven.suportSwing.PasswordField();
        txtVerifyPassword = new com.raven.suportSwing.PasswordField();
        myButton1 = new com.raven.suportSwing.MyButton();
        lblNewPassword = new javax.swing.JLabel();
        lblVerifyPassword = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtUsername.setLabelText("Username");

        jLabel1.setFont(new java.awt.Font("Montserrat Black", 1, 18)); // NOI18N
        jLabel1.setText("Đổi mật khẩu");

        txtPassword.setLabelText("Password");

        txtNewPassword.setLabelText("New Password");
        txtNewPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewPasswordFocusGained(evt);
            }
        });

        txtVerifyPassword.setLabelText("VerifyPassword");
        txtVerifyPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVerifyPasswordFocusGained(evt);
            }
        });

        myButton1.setForeground(new java.awt.Color(0, 122, 255));
        myButton1.setText("Đổi mật khẩu");
        myButton1.setBorderColor(new java.awt.Color(204, 204, 204));
        myButton1.setColorClick(new java.awt.Color(189, 231, 255));
        myButton1.setColorOver(new java.awt.Color(189, 231, 255));
        myButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        myButton1.setRadius(15);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        lblNewPassword.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblNewPassword.setForeground(new java.awt.Color(255, 0, 0));

        lblVerifyPassword.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblVerifyPassword.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                            .addGap(76, 76, 76)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblVerifyPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtVerifyPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))))
                .addGap(0, 1121, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVerifyPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVerifyPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(586, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
//        this.insert();
        updatePassword();
    }//GEN-LAST:event_myButton1ActionPerformed

    private void txtVerifyPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVerifyPasswordFocusGained
        // TODO add your handling code here:
        lblVerifyPassword.setText("");
    }//GEN-LAST:event_txtVerifyPasswordFocusGained

    private void txtNewPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPasswordFocusGained
        // TODO add your handling code here:
        lblNewPassword.setText("");
    }//GEN-LAST:event_txtNewPasswordFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JLabel lblVerifyPassword;
    private com.raven.suportSwing.MyButton myButton1;
    private com.raven.suportSwing.PasswordField txtNewPassword;
    private com.raven.suportSwing.PasswordField txtPassword;
    private com.raven.suportSwing.TextField txtUsername;
    private com.raven.suportSwing.PasswordField txtVerifyPassword;
    // End of variables declaration//GEN-END:variables
}

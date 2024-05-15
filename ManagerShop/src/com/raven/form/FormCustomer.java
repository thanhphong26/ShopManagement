/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.sportshop.DAO.CategoryDAO;
import com.sportshop.DAO.CustomerDAO;
import com.sportshop.Validate.Validate;
import com.sportshop.Validate.labelValidate;
import com.sportshop.entity.Category;
import com.sportshop.entity.Customer;
import com.sportshop.entity.Products;
import com.sportshop.entity.User;
import com.sportshop.utils.Excel;
import com.sportshop.utils.MsgBox;
import com.raven.dialog.Message;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ducit
 */
public class FormCustomer extends javax.swing.JPanel {

    /**
     * Creates new form FormItems
     */
    public FormCustomer() {
        initComponents();
        rdioMale.setSelected(true);
        btnXoa.setEnabled(false);
        btnCapNhap.setEnabled(false);
        fillTable();
        setOpaque(false);
    }

    CustomerDAO cDao = new CustomerDAO();

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        model.setRowCount(0);
        List<Customer> list = cDao.selectAll();
        for (Customer c : list) {
            Object[] row = {c.getId(), c.getName(), c.getAddress(), c.getPhoneNumber(), c.getGender() ? "Nam" : "Nữ"};
            model.addRow(row);
        }
    }

    public void fillTableWhenFind() {
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        model.setRowCount(0);
        String keyString = txtTimkiem.getText();
        List<Customer> list = cDao.selectByKeyWord(keyString);
        if (list.isEmpty()) {
            lblTimKiem.setText("Không có khách hàng " + keyString);
            return;
        }
        for (Customer c : list) {
            model.addRow(new Object[]{
                c.getId(), c.getName(), c.getAddress(), c.getPhoneNumber(), c.getGender() ? "Nam" : "Nữ"
            });
        }
        lblTimKiem.setText("");
    }

    public void setForm(Customer c) {
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtPhoneNumber.setText(c.getPhoneNumber());
        if (c.getGender()) {
            rdioMale.setSelected(true);
            rdioFemale.setSelected(false);
        } else {
            rdioFemale.setSelected(true);
            rdioMale.setSelected(false);
        }
    }

    public Customer getForm() {
        Customer c = new Customer();
        c.setName(txtName.getText());
        c.setAddress(txtAddress.getText());
        c.setPhoneNumber(txtPhoneNumber.getText());
        c.setGender(rdioMale.isSelected());
        return c;
    }

    public void clearForm() {
        tableCustomer.clearSelection();
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        rdioMale.setSelected(false);
        rdioFemale.setSelected(false);
        btnThem.setEnabled(true);
        lblName.setText("");
        lblAdress.setText("");
        lblPhoneNumber.setText("");
        btnCapNhap.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    public void insert() {
        Customer c = getForm();
        try {
            cDao.insert(c);
            fillTable();
            clearForm();
            MsgBox.alert(this, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        Customer c = getForm();
        int row = tableCustomer.getSelectedRow();
        c.setId((int) tableCustomer.getValueAt(row, 0));
        try {
            cDao.update(c);
            fillTable();
            clearForm();
            MsgBox.alert(this, "Update thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        int row = tableCustomer.getSelectedRow();
        int ma = (int) tableCustomer.getValueAt(row, 0);
        if (MsgBox.confirm(this, "Bạn có muốn xóa không?")) {
            try {
                cDao.delete(ma);
                fillTable();
                clearForm();
                MsgBox.alert(this, "Xoá Thành công");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void edit() {
        int row = tableCustomer.getSelectedRow();
        btnThem.setEnabled(false);
        btnXoa.setEnabled(true);
        btnCapNhap.setEnabled(true);
        int ma = (int) tableCustomer.getValueAt(row, 0);
        Customer c = cDao.selectById(ma);
        setForm(c);
    }

    public void addEvenFillTable(ActionListener evt) {
        fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimkiem = new com.raven.suportSwing.TextField();
        btnTim = new com.raven.suportSwing.MyButton();
        lblTimKiem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomer = new com.raven.suportSwing.TableColumn();
        scrollBarCustom1 = new com.raven.suportSwing.ScrollBarCustom();
        jPanel3 = new javax.swing.JPanel();
        txtName = new com.raven.suportSwing.TextField();
        btnClear = new com.raven.suportSwing.MyButton();
        btnThem = new com.raven.suportSwing.MyButton();
        btnCapNhap = new com.raven.suportSwing.MyButton();
        txtAddress = new com.raven.suportSwing.TextField();
        txtPhoneNumber = new com.raven.suportSwing.TextField();
        btnXoa = new com.raven.suportSwing.MyButton();
        jLabel3 = new javax.swing.JLabel();
        rdioMale = new com.raven.suportSwing.RadioButtonCustom();
        rdioFemale = new com.raven.suportSwing.RadioButtonCustom();
        lblName = new javax.swing.JLabel();
        lblAdress = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Khách hàng");

        txtTimkiem.setLabelText("Tìm theo tên khách hàng");
        txtTimkiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimkiemFocusGained(evt);
            }
        });
        txtTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimkiemActionPerformed(evt);
            }
        });
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        btnTim.setForeground(new java.awt.Color(0, 122, 255));
        btnTim.setText("Tìm");
        btnTim.setBorderColor(new java.awt.Color(204, 204, 204));
        btnTim.setColorClick(new java.awt.Color(189, 231, 255));
        btnTim.setColorOver(new java.awt.Color(189, 231, 255));
        btnTim.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTim.setRadius(15);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        lblTimKiem.setForeground(new java.awt.Color(225, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(454, 454, 454))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimKiem))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setVerticalScrollBar(scrollBarCustom1);

        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Tên KH", "Địa Chỉ", "SĐT", "Giới tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCustomer);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(scrollBarCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Chi tiết khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtName.setLabelText("Họ tên");
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        btnClear.setForeground(new java.awt.Color(0, 122, 255));
        btnClear.setBorderColor(new java.awt.Color(204, 204, 204));
        btnClear.setColorClick(new java.awt.Color(189, 231, 255));
        btnClear.setColorOver(new java.awt.Color(189, 231, 255));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setLabel("Xóa form");
        btnClear.setRadius(20);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnThem.setForeground(new java.awt.Color(0, 122, 255));
        btnThem.setText("Thêm");
        btnThem.setBorderColor(new java.awt.Color(204, 204, 204));
        btnThem.setColorClick(new java.awt.Color(189, 231, 255));
        btnThem.setColorOver(new java.awt.Color(189, 231, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setRadius(20);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhap.setForeground(new java.awt.Color(0, 122, 255));
        btnCapNhap.setText("Cập Nhật");
        btnCapNhap.setBorderColor(new java.awt.Color(204, 204, 204));
        btnCapNhap.setColorClick(new java.awt.Color(189, 231, 255));
        btnCapNhap.setColorOver(new java.awt.Color(189, 231, 255));
        btnCapNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhap.setRadius(20);
        btnCapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapActionPerformed(evt);
            }
        });

        txtAddress.setLabelText("Địa chỉ");
        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });

        txtPhoneNumber.setLabelText("SĐT");
        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(0, 122, 255));
        btnXoa.setText("Xóa");
        btnXoa.setBorderColor(new java.awt.Color(204, 204, 204));
        btnXoa.setColorClick(new java.awt.Color(189, 231, 255));
        btnXoa.setColorOver(new java.awt.Color(189, 231, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setRadius(20);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel3.setText("Giới tính");

        buttonGroup1.add(rdioMale);
        rdioMale.setText("Nam");
        rdioMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdioMaleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdioFemale);
        rdioFemale.setText("Nữ");
        rdioFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdioFemaleActionPerformed(evt);
            }
        });

        lblName.setForeground(new java.awt.Color(255, 0, 0));

        lblAdress.setForeground(new java.awt.Color(225, 0, 0));

        lblPhoneNumber.setForeground(new java.awt.Color(225, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdioMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdioFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lblAdress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdioMale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdioFemale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdioFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdioFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdioFemaleActionPerformed

    private void rdioMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdioMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdioMaleActionPerformed

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (!labelValidate.checkEmpty(lblName, txtName, "Không được để trống họ và tên")) {
            return;
        } else if (!labelValidate.checkEmpty(lblAdress, txtAddress, "Không được để trống địa chỉ")) {
            return;
        } else if (!labelValidate.checkEmpty(lblPhoneNumber, txtPhoneNumber, "Không được để trống SDT")) {
            return;
        } else {
            insert();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        if (!labelValidate.checkEmpty(lblName, txtName, "Không được để trống họ và tên")) {
            return;
        } else if (!labelValidate.checkEmpty(lblAdress, txtAddress, "Không được để trống địa chỉ")) {
            return;
        } else if (!labelValidate.checkEmpty(lblPhoneNumber, txtPhoneNumber, "Không được để trống SDT")) {
            return;
        } else {
            update();
        }
    }//GEN-LAST:event_btnCapNhapActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        fillTableWhenFind();
    }//GEN-LAST:event_btnTimActionPerformed

    private void tableCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCustomerMouseClicked
        // TODO add your handling code here:
        edit();
    }//GEN-LAST:event_tableCustomerMouseClicked

    private void txtTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimkiemActionPerformed
        // TODO add your handling code here:
        fillTableWhenFind();
    }//GEN-LAST:event_txtTimkiemActionPerformed

    private void txtTimkiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimkiemFocusGained
        // TODO add your handling code here:
        fillTableWhenFind();
    }//GEN-LAST:event_txtTimkiemFocusGained

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        // TODO add your handling code here:
        fillTableWhenFind();
    }//GEN-LAST:event_txtTimkiemKeyReleased

    public void excelCustomer() throws IOException {
        Excel.outExcel((DefaultTableModel) tableCustomer.getModel());
        MsgBox.alert(this, "Xuất file thành công");
    }
    public boolean checkNameProduct(String acc) {
        for (int i = 0; i < cDao.selectAll().size(); i++) {
            if (cDao.selectAll().get(i).getPhoneNumber().equals(acc.trim())) {
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.suportSwing.MyButton btnCapNhap;
    private com.raven.suportSwing.MyButton btnClear;
    private com.raven.suportSwing.MyButton btnThem;
    private com.raven.suportSwing.MyButton btnTim;
    private com.raven.suportSwing.MyButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblTimKiem;
    private com.raven.suportSwing.RadioButtonCustom rdioFemale;
    private com.raven.suportSwing.RadioButtonCustom rdioMale;
    private com.raven.suportSwing.ScrollBarCustom scrollBarCustom1;
    private com.raven.suportSwing.TableColumn tableCustomer;
    private com.raven.suportSwing.TextField txtAddress;
    private com.raven.suportSwing.TextField txtName;
    private com.raven.suportSwing.TextField txtPhoneNumber;
    private com.raven.suportSwing.TextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}

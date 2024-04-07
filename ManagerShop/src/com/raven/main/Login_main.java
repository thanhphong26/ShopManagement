/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.main;

import com.fpt.utils.XImage;
import com.raven.suportSwing.PanelBorder;
import com.raven.suportSwing.PanelSlide;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author ducit
 */
public class Login_main extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login_main() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
         Image icon = Toolkit.getDefaultToolkit().getImage("src\\com\\raven\\icon\\shop (6).png");
        this.setIconImage(icon);
//        setIconImage(XImage.getAppImage());
        Login login = new Login();
        ForgotPassword fogotPassword = new ForgotPassword();
        VerifyEmail verifyEmail = new VerifyEmail();
        ChangePassword changePass = new ChangePassword();
        slide.setAnimate(15);
        slide.init(login, fogotPassword, verifyEmail, changePass);
        login.addEventRegister(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //  Show register form
                slide.show(1);
                fogotPassword.register();
            }
        });
        
        fogotPassword.addEventVerify(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //  Show register form
                if(!fogotPassword.checkEmail()) {
                    return;
                } else {
                    slide.show(2);
                }
            }
        });
        
        
        
        login.addEventLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (login.login() == true) {
                    dispose();
                }
            }
        });
        
        // Lấy icon từ JImage
        Icon i = JImage.getIcon();
        if (i instanceof ImageIcon) {
            ImageIcon icon2 = (ImageIcon) i;
            Image image = icon2.getImage().getScaledInstance(JImage.getWidth(), JImage.getHeight(), Image.SCALE_SMOOTH);
            JImage.setIcon(new ImageIcon(image));
        } else {
            System.err.println("Error: Not a valid ImageIcon");
        }
        
        Icon i2 = JImage1.getIcon();
        if (i2 instanceof ImageIcon) {
            ImageIcon icon3 = (ImageIcon) i2;
            Image image2 = icon3.getImage().getScaledInstance(JImage1.getWidth(), JImage1.getHeight(), Image.SCALE_SMOOTH);
            JImage1.setIcon(new ImageIcon(image2));
        } else {
            System.err.println("Error: Not a valid ImageIcon");
        }
        
        Icon i3 = JImage2.getIcon();
        if (i3 instanceof ImageIcon) {
            ImageIcon icon4 = (ImageIcon) i3;
            Image image3 = icon4.getImage().getScaledInstance(JImage2.getWidth(), JImage2.getHeight(), Image.SCALE_SMOOTH);
            JImage2.setIcon(new ImageIcon(image3));
        } else {
            System.err.println("Error: Not a valid ImageIcon");
        }
    }

    public void dispose() {
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradiente2 = new com.raven.suportSwing.PanelGradiente();
        JImage2 = new javax.swing.JLabel();
        slide = new com.raven.suportSwing.PanelSlide();
        JImage1 = new javax.swing.JLabel();
        JImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IT Shop APP");

        panelGradiente2.setColorPrimario(new java.awt.Color(146, 233, 251));
        panelGradiente2.setColorSecundario(new java.awt.Color(12, 137, 163));

        JImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/SPORTSHOP.png"))); // NOI18N
        panelGradiente2.add(JImage2);
        JImage2.setBounds(790, 130, 350, 100);

        slide.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout slideLayout = new javax.swing.GroupLayout(slide);
        slide.setLayout(slideLayout);
        slideLayout.setHorizontalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        slideLayout.setVerticalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        panelGradiente2.add(slide);
        slide.setBounds(740, 250, 400, 430);

        JImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/nen.png"))); // NOI18N
        panelGradiente2.add(JImage1);
        JImage1.setBounds(590, -30, 600, 850);

        JImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/running.jpg"))); // NOI18N
        panelGradiente2.add(JImage);
        JImage.setBounds(-40, 0, 890, 770);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelGradiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGradiente2, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Login_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JImage;
    private javax.swing.JLabel JImage1;
    private javax.swing.JLabel JImage2;
    private com.raven.suportSwing.PanelGradiente panelGradiente2;
    private com.raven.suportSwing.PanelSlide slide;
    // End of variables declaration//GEN-END:variables
}

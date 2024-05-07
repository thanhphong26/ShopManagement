package com.raven.component;

import com.sportshop.utils.Auth;
import com.raven.event.EventMenu;
import com.raven.event.EventMenuSelected;
import com.raven.event.EventShowPopupMenu;
import com.raven.model.ModelMenu;
import com.raven.swing.MenuAnimation;
import com.raven.swing.MenuItem;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        if (Auth.isManager()) {
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\1.png"), "Trang chủ"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\12.png"), "Hàng hoá", "Sản phẩm", "Mặt Hàng", "Nhà cung cấp"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\4.png"), "Giao dịch", "Nhập Hàng", "Bán Hàng", "Trả Hàng", "Đổi hàng", "Hoá Đơn Bán hàng", "Hoá đơn Nhập hàng", "Hoá đơn trả hàng", "Hóa đơn đổi hàng"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\3.png"), "Thống kê", "Thông kê doanh thu", "Thống kê doanh số"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\5.png"), "Nhân viên", "Danh sách nhân viên"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\10.png"), "Khách hàng"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\user.png"), "Cá Nhân", "Thông tin cá nhân", "Đổi mật khẩu"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\9.png"), "Khuyến mãi"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\ExitAccount.png"), "Đăng xuất"));
        } else {
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\1.png"), "Trang chủ"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\4.png"), "Giao dịch", "Bán Hàng", "Trả Hàng", "Đổi hàng", "Hoá Đơn Bán hàng", "Hoá đơn trả hàng", "Hoá đơn đổi hàng"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\3.png"), "Thống kê", "Thông kê doanh thu", "Thống kê doanh số"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\10.png"), "Khách hàng"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\user.png"), "Cá Nhân", "Thông tin cá nhân", "Đổi mật khẩu"));
            addMenu(new ModelMenu(new ImageIcon("src\\com\\raven\\icon\\ExitAccount.png"), "Đăng xuất"));

        }

    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new com.raven.component.Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;

        int width = getWidth();
        int height = getHeight();

        Color startColor = new Color(102,168,234); 

        Color endColor = new Color(69,111,153); 

        GradientPaint gradientPaint = new GradientPaint(
                0, 0, endColor,
                0, height, startColor 
        );

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(gradientPaint);

        g2.fillRect(0, 0, width, height);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.raven.component.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}

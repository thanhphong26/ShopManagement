/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author THANHPHONG
 */
public class SlideShow extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    private final List<ImageIcon> imageIcons;
    private final JLabel[] imageLabels;
    private int currentIndex = 0;

    public SlideShow(String[] imagePaths) {
        imageIcons = new ArrayList<>();
        for (String path : imagePaths) {
            ImageIcon imageIcon = new ImageIcon(path);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(
                    400, // Adjust width as needed
                    230, // Adjust height as needed
                    Image.SCALE_SMOOTH
            ));
            imageIcons.add(imageIcon);
        }

       setLayout(null); // Use null layout to position images manually

        imageLabels = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            imageLabels[i] = new JLabel();
            imageLabels[i].setPreferredSize(new Dimension(400, 230));
            imageLabels[i].setBounds(0, i * 230, 400, 230); // Position each image vertically
            add(imageLabels[i]);
        }

        // Start the slideshow
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 0; i < 3; i++) {
                    imageLabels[i].setIcon(imageIcons.get((currentIndex + i) % imageIcons.size()));
                }
                Thread.sleep(2500);
                currentIndex = (currentIndex + 1) % imageIcons.size();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
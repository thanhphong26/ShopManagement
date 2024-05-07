/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.JFrame;

import com.sportshop.DAO.DetailsChangeProductDAO;
import com.sportshop.DAO.DetailsInvoiceChangeDAO;
import com.sportshop.DAO.InvoiceChangeDAO;
import com.sportshop.DAO.ProductItemDAO;
import com.sportshop.entity.DetailInvoiceImport;
import com.sportshop.entity.DetailInvoiceReturn;
import com.sportshop.entity.DetailsChangeProducts;
import com.sportshop.entity.DetailsInvoiceChange;
import com.sportshop.entity.InvoiceChange;
import com.sportshop.entity.ProductItem;
import com.sportshop.utils.MsgBox;
import com.sportshop.utils.XDate;
import static com.sportshop.utils.convertEng.removeAccent;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.BadElementException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class FormDetailChangeProduct extends javax.swing.JFrame {

    /**
     * Creates new form FormDetailChangeProduct
     */
    DefaultTableModel model;
    int row;

    public FormDetailChangeProduct() {
        initComponents();
    }
    InvoiceChangeDAO invoiceChangeDAO = new InvoiceChangeDAO();
    ProductItemDAO productItemDAO = new ProductItemDAO();
    DetailsChangeProductDAO detailsChangeProductDAO = new DetailsChangeProductDAO();
    DetailsInvoiceChangeDAO detailsInvoiceChangeDAO = new DetailsInvoiceChangeDAO();

    public FormDetailChangeProduct(int id, DefaultTableModel model, int row) {
        this.model = model;
        this.row = row;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        fillTableOld(id);
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\com\\raven\\icon\\logosport.png");
        this.setIconImage(icon);
        fillTableNew((int) tableShowOld.getValueAt(tableShowOld.getRowCount() - 1, 0));

    }
    Locale lc = new Locale("nv", "VN");
    NumberFormat nf = NumberFormat.getInstance(lc);
    List<DetailsChangeProducts> listPNew;
    List<DetailsInvoiceChange> listPOld;

    public void fillTableOld(int id) {
        listPOld = detailsInvoiceChangeDAO.selectByIdInvoiceChange(id);
        DefaultTableModel modelOld = (DefaultTableModel) tableShowOld.getModel();
        modelOld.setRowCount(0);
        for (DetailsInvoiceChange pOld : listPOld) {
            modelOld.addRow(new Object[]{
                pOld.getId(), pOld.getIdProductItem(), pOld.getNameProduct(), pOld.getValueSize(), pOld.getValueColor(), pOld.getValueMaterial(), pOld.getQuantity(), nf.format(pOld.getPrice()) + " đ"
            });
        }

    }

    public void fillTableNew(int id) {
        listPNew = detailsChangeProductDAO.selectByIdDetailsInvoiceChange(id);
        DefaultTableModel modelNew = (DefaultTableModel) tableShowNew.getModel();
        modelNew.setRowCount(0);
        for (DetailsChangeProducts pNew : listPNew) {
            modelNew.addRow(new Object[]{
                pNew.getId(), pNew.getIdProductItem(), pNew.getNameProduct(), pNew.getValueSize(), pNew.getValueColor(), pNew.getValueMaterial(), pNew.getQuantity(), nf.format(pNew.getPrice()) + " đ"
            });
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
        jScrollPane5 = new javax.swing.JScrollPane();
        tableShowNew = new com.raven.suportSwing.TableColumn();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableShowOld = new com.raven.suportSwing.TableColumn();
        myButton1 = new com.raven.suportSwing.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tableShowNew.setBackground(new java.awt.Color(255, 255, 255));
        tableShowNew.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID SP", "Tên SP", "Size", "Color", "Chất liệu", "Số lượng", "Giá tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableShowNew.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane5.setViewportView(tableShowNew);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Hóa đơn chi tiết");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Sản phẩm trả");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Sản phẩm đổi");

        tableShowOld.setBackground(new java.awt.Color(255, 255, 255));
        tableShowOld.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID SP", "Tên SP", "Size", "Color", "Chất liệu", "Số lượng", "Giá tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableShowOld.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableShowOld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableShowOldMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableShowOld);

        myButton1.setForeground(new java.awt.Color(0, 122, 255));
        myButton1.setText("Xuất hoá đơn");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(454, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String deleteLastKey(String str) {
        if (str.charAt(str.length() - 1) == 'đ') {
            str = str.replace(str.substring(str.length() - 1), "");
            return str;
        } else {
            return str;
        }
    }

    public String fomartFloat(String txt) {
        String pattern = deleteLastKey(txt);
        return pattern = pattern.replaceAll(",", "");
    }

    public void outputPDF() throws IOException, BadElementException {
        int quantityTTNew = 0, quantityTTOld = 0;
        float priceTNew = 0, priceTOld = 0;
        Locale lc = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getInstance(lc);
        String pathnn = XDate.toString(new Date(), " hh-mm-ss aa dd-MM-yyyy");
        pathnn = pathnn.replaceAll(" ", "_");
        System.out.println(pathnn);
        String path = "D:\\InvoiceChangeProducts" + pathnn + ".pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document doc = new Document(pdfDocument);
        float col = 280f;
        float columnWidth[] = {col, col};
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(columnWidth);
        table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
        String file = "src\\com\\raven\\icon\\logosport.png";
        ImageData date = ImageDataFactory.create(file);
        com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(date);
//        doc.add(image);
        table.addCell(new Cell().add(image).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("IT SHOP").setFontSize(30f).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add("So 1 VVN, Thu Duc \n SDT: 0326012035 - 0987415260")
                .setTextAlignment(TextAlignment.RIGHT).setMarginTop(30f).setMarginBottom(30f).setBorder(Border.NO_BORDER).setMarginRight(10f)
        );

        float colWidth[] = {80, 250, 80, 150};

        com.itextpdf.layout.element.Table customerInfor = new com.itextpdf.layout.element.Table(colWidth);
        customerInfor.addCell(new Cell(0, 4).add("Hoa don doi hang").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

        customerInfor.addCell(new Cell(0, 4).add("Thong tin").setBold().setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add("Khach hang: ").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add(removeAccent(model.getValueAt(row, 4).toString())).setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add("Ma Hoa Don: ").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add(model.getValueAt(row, 0) + "").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add("SDT: ").setBorder(Border.NO_BORDER)); //
        customerInfor.addCell(new Cell().add(removeAccent(model.getValueAt(row, 5).toString())).setBorder(Border.NO_BORDER)); //

        customerInfor.addCell(new Cell().add("Thu Ngan: ").setBorder(Border.NO_BORDER)); //
        customerInfor.addCell(new Cell().add(removeAccent(model.getValueAt(row, 2).toString())).setBorder(Border.NO_BORDER)); //
        customerInfor.addCell(new Cell().add("Date: ").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add(model.getValueAt(row, 3) + "").setBorder(Border.NO_BORDER));

        float iteamInforColWidth[] = {93, 93, 93, 93, 93, 93};
        com.itextpdf.layout.element.Table itemInforTable = new com.itextpdf.layout.element.Table(iteamInforColWidth);
        itemInforTable.addCell(new Cell().add("San Pham Doi").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("Size").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("Mau").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("Chat Lieu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("So luong").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("Gia").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));

        List<InvoiceChange> list = invoiceChangeDAO.selectAll();
        float total = 0;
        int quantitySum = 0;

        for (int i = 0; i < tableShowOld.getRowCount(); i++) {
            int id = (int) tableShowOld.getValueAt(i, 1);
            String nameProduct = (String) tableShowOld.getValueAt(i, 2);
            String Size = (String) tableShowOld.getValueAt(i, 3);
            String Color = (String) tableShowOld.getValueAt(i, 4);
            String Material = (String) tableShowOld.getValueAt(i, 5);

            int quantity = (int) tableShowOld.getValueAt(i, 6);
            float price = Float.parseFloat(fomartFloat((String) tableShowOld.getValueAt(i, 7)));
            itemInforTable.addCell(new Cell().add(removeAccent(nameProduct)));
            itemInforTable.addCell(new Cell().add(removeAccent(Size)));
            itemInforTable.addCell(new Cell().add(removeAccent(Color)));
            itemInforTable.addCell(new Cell().add(removeAccent(Material)));
            itemInforTable.addCell(new Cell().add(quantity + ""));
            itemInforTable.addCell(new Cell().add(nf.format(price) + " VND"));
            quantityTTOld += quantity;
            priceTOld += price * quantity;
        }

        itemInforTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add("Tong So Luong").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add(quantityTTOld + "").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add("Tong Tien").setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add(nf.format(priceTOld) + " VND").setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
//        itemInforTable.addCell(new Cell().add(nf.format(price) + " VND").setTextAlignment(TextAlignment.RIGHT));
//---------------------------------------------------------------------------------------------------------------------------------
//        float iteamInforColWidth[] = {93, 93, 93, 93, 93, 93};
        com.itextpdf.layout.element.Table itemInforTableNew = new com.itextpdf.layout.element.Table(iteamInforColWidth);
        itemInforTableNew.addCell(new Cell().add("San Pham Tra").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(new DeviceRgb(255, 255, 255)));
        itemInforTableNew.addCell(new Cell().add("Size").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(new DeviceRgb(255, 255, 255)));
        itemInforTableNew.addCell(new Cell().add("Mau").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(new DeviceRgb(255, 255, 255)));
        itemInforTableNew.addCell(new Cell().add("Chat Lieu").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(new DeviceRgb(255, 255, 255)));
        itemInforTableNew.addCell(new Cell().add("So luong").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(new DeviceRgb(255, 255, 255)));
        itemInforTableNew.addCell(new Cell().add("Gia").setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(new DeviceRgb(255, 255, 255)).setTextAlignment(TextAlignment.RIGHT));
        float totalNew = 0;

        for (int i = 0; i < tableShowNew.getRowCount(); i++) {
            int id = (int) tableShowNew.getValueAt(i, 1);
            String nameProductNew = (String) tableShowNew.getValueAt(i, 2);
            String SizeNew = (String) tableShowNew.getValueAt(i, 3);
            String ColorNew = (String) tableShowNew.getValueAt(i, 4);
            String MaterialNew = (String) tableShowNew.getValueAt(i, 5);

            int quantityNew = (int) tableShowNew.getValueAt(i, 6);
            float priceNew = Float.parseFloat(fomartFloat((String) tableShowNew.getValueAt(i, 7)));
//            totalNew += priceNew * quantityNew;
            itemInforTableNew.addCell(new Cell().add(removeAccent(nameProductNew)));
            itemInforTableNew.addCell(new Cell().add(removeAccent(SizeNew)));
            itemInforTableNew.addCell(new Cell().add(removeAccent(ColorNew)));
            itemInforTableNew.addCell(new Cell().add(removeAccent(MaterialNew)));
            itemInforTableNew.addCell(new Cell().add(quantityNew + ""));
            itemInforTableNew.addCell(new Cell().add(nf.format(priceNew) + " VND"));
            priceTNew += priceNew * quantityNew;
            quantityTTNew += quantityNew;
        }

//        itemInforTableNew.addCell(new Cell().add(nf.format(priceTNew) + " VND").setTextAlignment(TextAlignment.RIGHT));
        itemInforTableNew.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTableNew.addCell(new Cell().add("").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTableNew.addCell(new Cell().add("Tong So Luong").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTableNew.addCell(new Cell().add(quantityTTNew + "").setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER));
        itemInforTableNew.addCell(new Cell().add("Tong Tien").setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
        itemInforTableNew.addCell(new Cell().add(nf.format(priceTNew) + " VND").setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(new DeviceRgb(63, 169, 219)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
        float colWidthNote[] = {560};

        com.itextpdf.layout.element.Table customerInforNote = new com.itextpdf.layout.element.Table(colWidthNote);
        customerInforNote.addCell(new Cell().add("Tien Tra Lai: " + nf.format((priceTOld - priceTNew)) + " VND").
                setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold().setFontSize(20).setFontColor(new DeviceRgb(0, 0, 0)));
        customerInforNote.addCell(new Cell().add("Xin cam on quy khach !!!").
                setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setItalic().setFontColor(new DeviceRgb(0, 0, 0)));
        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfor);
        document.add(new Paragraph("\n"));
        document.add(itemInforTable);
        document.add(new Paragraph("\n"));
        document.add(itemInforTableNew);
        document.add(new Paragraph("\n"));
        document.add(customerInforNote);
        document.close();
    }

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            outputPDF();
            MsgBox.alert(this, "Xuất hoá đơn thành công");
        } catch (IOException ex) {
            Logger.getLogger(FormDetailInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(FormDetailInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_myButton1ActionPerformed

    private void tableShowOldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableShowOldMouseClicked
        fillTableNew((int) tableShowOld.getValueAt(tableShowOld.getSelectedRow(), 0));
    }//GEN-LAST:event_tableShowOldMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormDetailChangeProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormDetailChangeProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormDetailChangeProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormDetailChangeProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormDetailChangeProduct().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private com.raven.suportSwing.MyButton myButton1;
    private com.raven.suportSwing.TableColumn tableShowNew;
    private com.raven.suportSwing.TableColumn tableShowOld;
    // End of variables declaration//GEN-END:variables
}

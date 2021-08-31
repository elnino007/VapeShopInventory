/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.connection.DatabaseConnection;
import com.dao.UserDAO;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author shaitozen
 */
public class ReportFrame extends javax.swing.JFrame {

    final private UserDAO userDAO = new UserDAO();
    
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
     
     
    public ReportFrame() {
        initComponents();
      
          setIconImage();
          
          loadCombobox();
          
          btnProduct.setVisible(false);
          cmbProduct.setVisible(false);
    }
    
     private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/Icon-20.png")));
    }
     
     
    public void ProductReport(){
        try {
            conn = new DatabaseConnection().getConnection();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            sql = "SELECT p.product_name, SUM(tb.quantity) AS quantity, SUM(tb.total_amount) AS total_amount FROM product AS p INNER JOIN transaction_tbl AS tb ON p.barcode = tb.barcode "
                    + "WHERE p.product_name='"+cmbProduct.getSelectedItem().toString() +"'"
                    + "AND date_created BETWEEN '"+df.format(dtFrom.getDate())+"' AND '"+df.format(dtTo.getDate())+"'";
            
           //  sql = "SELECT * FROM transaction_tbl WHERE username = '"+cmbCashier.getSelectedItem().toString()+"'";
            JasperDesign jasperDesign = JRXmlLoader.load("D:\\thesis\\VapeShopInventory\\src\\report\\transationReport.jrxml");
            
            JRDesignQuery jRDesignQuery = new JRDesignQuery();
            jRDesignQuery.setText(sql);
            jasperDesign.setQuery(jRDesignQuery);
            
            Map<String, Object> formValues = new HashMap<>();
            formValues.put("DATEFROM", df.format(dtFrom.getDate()));
            formValues.put("DATETO", df.format(dtTo.getDate()));
//            formValues.put("CHANGE", txtChange.getText());
            
            JasperReport jReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, formValues, conn);
            
            JasperViewer jasperViewer = new JasperViewer(jPrint, false); 
            jasperViewer.setTitle("My Reports");
        //    setModal(false);
            jasperViewer.setAlwaysOnTop(true);  //here
            jasperViewer.setVisible(true);
          //  setModal(true);
            
           // JasperViewer.viewReport(jPrint);
            

        } catch (JRException ex) {
          
        }
    }
     
    public void CashierReport(){
        try {
            conn = new DatabaseConnection().getConnection();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          
            if (cmbCashier.getSelectedIndex() == 0) {
                sql = "SELECT * FROM transaction_tbl WHERE date_created BETWEEN '"+df.format(dtFrom.getDate())+"' AND '"+df.format(dtTo.getDate())+"'";
            }else{
                sql = "SELECT * FROM transaction_tbl WHERE username = '"+cmbCashier.getSelectedItem().toString() +"' AND "
                    + "date_created BETWEEN '"+df.format(dtFrom.getDate())+"' AND '"+df.format(dtTo.getDate())+"'";
            }
            
            
           //  sql = "SELECT * FROM transaction_tbl WHERE username = '"+cmbCashier.getSelectedItem().toString()+"'";
            JasperDesign jasperDesign = JRXmlLoader.load("D:\\thesis\\VapeShopInventory\\src\\report\\cashierReport.jrxml");
            
            JRDesignQuery jRDesignQuery = new JRDesignQuery();
            jRDesignQuery.setText(sql);
            jasperDesign.setQuery(jRDesignQuery);
            
            Map<String, Object> formValues = new HashMap<>();
            formValues.put("USERNAME", cmbCashier.getSelectedItem());
          
            
            JasperReport jReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, formValues, conn);
            
            JasperViewer jasperViewer = new JasperViewer(jPrint, false); 
            jasperViewer.setTitle("My Reports");
        //    setModal(false);
            jasperViewer.setAlwaysOnTop(true);  //here
            jasperViewer.setVisible(true);
//            setModal(true);
            jasperViewer.setDefaultCloseOperation(0);
            JasperViewer.viewReport(jPrint);
            

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
   
     
    public void loadCombobox(){
        List<String> username = userDAO.getCashierUsername();
        cmbCashier.addItem("All");
        
        for(int i=0; i < username.size(); i++){
            cmbCashier.addItem(username.get(i));
        }
        
         List<String> productName = userDAO.getProductName();
         for(int i=0; i < productName.size(); i++){
            cmbProduct.addItem(productName.get(i));
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
        jButton1 = new javax.swing.JButton();
        btnProduct = new javax.swing.JButton();
        cmbCashier = new javax.swing.JComboBox<>();
        cmbProduct = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        dtFrom = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        dtTo = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jButton1.setLabel("Transaction By Cashier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnProduct.setText("Transaction By Product");
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });

        cmbCashier.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        cmbProduct.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Date From :");

        dtFrom.setDateFormatString("MM/dd/yyyy");
        dtFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtFromPropertyChange(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Date To :");

        dtTo.setDateFormatString("MM/dd/yyyy");
        dtTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtToPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCashier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dtFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtFromPropertyChange
   
    }//GEN-LAST:event_dtFromPropertyChange

    private void dtToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtToPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtToPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       CashierReport();
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        ProductReport();
    }//GEN-LAST:event_btnProductActionPerformed

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
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProduct;
    public javax.swing.JComboBox<String> cmbCashier;
    public javax.swing.JComboBox<String> cmbProduct;
    private com.toedter.calendar.JDateChooser dtFrom;
    private com.toedter.calendar.JDateChooser dtTo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

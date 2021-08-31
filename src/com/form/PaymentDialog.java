/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.connection.DatabaseConnection;
import com.dao.CustomerPointsDAO;
import com.dao.ProductStockDAO;
import com.dao.TransactionDAO;
import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
public class PaymentDialog extends javax.swing.JDialog {

    private POSDiaglog posDiaglog;
    final private TransactionDAO transactionDAO = new TransactionDAO();
    final private ProductStockDAO productStockDAO = new ProductStockDAO();
    final private CustomerPointsDAO customerPointsDAO = new CustomerPointsDAO();
   
    public String username;
    public String rfidNumber;
    public String points;
    public int transactionID;

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRfidNumber() {
        return rfidNumber;
    }

    public void setRfidNumber(String rfidNumber) {
        this.rfidNumber = rfidNumber;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    
    
     
    public PaymentDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public PaymentDialog(java.awt.Frame parent, Container container, boolean modal, String totalAmount) {
        super(parent, modal);
        initComponents();
        
        this.posDiaglog = (POSDiaglog) container;
        
        txtTotalAmount.setText(totalAmount);
    }
    
    public void receiptReport(){
        try {
            conn = new DatabaseConnection().getConnection();
            sql = "SELECT tt.id, p.product_name, tt.quantity,tt.price, tt.total_amount "
                    + "FROM transaction_tbl AS tt "
                    + "INNER JOIN product AS p "
                    + "ON tt.barcode  = p.barcode "
                    + "WHERE tt.id = " + getTransactionID() + "";
            JasperDesign jasperDesign = JRXmlLoader.load("D:\\thesis\\VapeShopInventory\\src\\report\\receiptReport.jrxml");
            
            JRDesignQuery jRDesignQuery = new JRDesignQuery();
            jRDesignQuery.setText(sql);
            jasperDesign.setQuery(jRDesignQuery);
            
            Map<String, Object> formValues = new HashMap<>();
            formValues.put("TOTAL_AMOUNT", txtTotalAmount.getText());
            formValues.put("CASH", txtCash.getText());
            formValues.put("CHANGE", txtChange.getText());
            
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
            Logger.getLogger(PaymentDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    public void addTransaction(){
        int rows = posDiaglog.tblPOS.getRowCount();
        setTransactionID(transactionDAO.autoNumber());
        TableModel model = posDiaglog.tblPOS.getModel();
       
        
        
        for (int i = 0; i < rows; i++) {
            List<Object> columnValues = new ArrayList<>();

            columnValues.add(getTransactionID());
            columnValues.add(model.getValueAt(i, 0).toString());
            columnValues.add(model.getValueAt(i, 2).toString());
            columnValues.add(model.getValueAt(i, 3).toString());
            columnValues.add(model.getValueAt(i, 4).toString());
            columnValues.add(getUsername());
            columnValues.add(model.getValueAt(i, 5).toString());
        
 
            transactionDAO.create(columnValues);
            
            int stockRemaining = productStockDAO.getQuantityByBarcode(model.getValueAt(i, 0).toString()) - 
                   Integer.parseInt(model.getValueAt(i, 2).toString());
            
            productStockDAO.updateStockAfterPay(model.getValueAt(i, 0).toString(), stockRemaining);
            
            
             
        }
    }
    
    public void updatePoints(String rfidNubmer, int points){
        int getPoints = customerPointsDAO.getPointsByRFID(rfidNumber);
        
        int totalPoints = getPoints + points;
        
        customerPointsDAO.updatePoints(rfidNubmer, totalPoints);
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
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        txtCash = new javax.swing.JTextField();
        btnPay = new java.awt.Button();
        btnQty1 = new java.awt.Button();
        txtTotalAmount = new java.awt.Label();
        label4 = new java.awt.Label();
        txtChange = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        label2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 102));
        label2.setName(""); // NOI18N
        label2.setText("Cash :");

        label3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label3.setForeground(new java.awt.Color(0, 0, 102));
        label3.setName(""); // NOI18N
        label3.setText("Total Amount :");

        txtCash.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCashKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCashKeyTyped(evt);
            }
        });

        btnPay.setBackground(new java.awt.Color(255, 204, 255));
        btnPay.setEnabled(false);
        btnPay.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnPay.setLabel("Pay");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnQty1.setBackground(new java.awt.Color(255, 204, 255));
        btnQty1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnQty1.setLabel("Cancel");
        btnQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQty1ActionPerformed(evt);
            }
        });

        txtTotalAmount.setAlignment(java.awt.Label.CENTER);
        txtTotalAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(0, 0, 102));
        txtTotalAmount.setText("0.00");

        label4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label4.setForeground(new java.awt.Color(0, 0, 102));
        label4.setName(""); // NOI18N
        label4.setText("Change :");

        txtChange.setAlignment(java.awt.Label.CENTER);
        txtChange.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtChange.setForeground(new java.awt.Color(0, 0, 102));
        txtChange.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(txtChange, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCash))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQty1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        addTransaction();
        updatePoints(getRfidNumber(), Integer.parseInt(getPoints()));
        receiptReport();
        posDiaglog.clearTransaction();
        posDiaglog.clearTxtAndButton();
        posDiaglog.clearButtonForPOS();
        posDiaglog.clearCustomerInfo();
        DefaultTableModel model = (DefaultTableModel) posDiaglog.tblPOS.getModel();
        model.setRowCount(0); 
        dispose();
       
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQty1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnQty1ActionPerformed

    private void txtCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyPressed
    
    }//GEN-LAST:event_txtCashKeyPressed

    private void txtCashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyReleased
        try {
            double change = 0;
            double totalAmount = Double.parseDouble(txtTotalAmount.getText());
            double cash = Double.parseDouble(txtCash.getText());
            
            change = cash - totalAmount;
            
           
            
            txtChange.setText(String.format("%.2f",change));
            
            if (change < 0){
                btnPay.setEnabled(false);
            } else {
                btnPay.setEnabled(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCashKeyReleased

    private void txtCashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCashKeyTyped
       if(Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }else{
            try {
                Double.parseDouble(txtCash.getText()+evt.getKeyChar());
            } catch (NumberFormatException e) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtCashKeyTyped

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
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PaymentDialog dialog = new PaymentDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnPay;
    private java.awt.Button btnQty1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private javax.swing.JTextField txtCash;
    private java.awt.Label txtChange;
    private java.awt.Label txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}

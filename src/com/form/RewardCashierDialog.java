/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.CustomerDAO;
import com.dao.CustomerRFIDDAO;
import com.dao.RewardDAO;
import com.utils.CustomDBUtils;
import com.utils.TableHelper;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author shaitozen
 */
public class RewardCashierDialog extends javax.swing.JDialog {

    private CustomerDAO customerDAO = new CustomerDAO();
    private CustomerRFIDDAO customerRFIDDAO = new CustomerRFIDDAO();
     final private RewardDAO rewardDAO = new RewardDAO();
     private ResultSet rs = null;
     
     public String IDIDRFID;

    public String getIDIDRFID() {
        return IDIDRFID;
    }

    public void setIDIDRFID(String IDIDRFID) {
        this.IDIDRFID = IDIDRFID;
    }
     
     
    
    public RewardCashierDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        updateTableUsers();
         setButton();
    }
    
    public void clear(){
         DefaultTableModel model = (DefaultTableModel) tblRedeem.getModel();
                            model.setRowCount(0);
                            
        txtRFIDNumber.setText("");
        txtTotalPoints.setText("");
        txtCustomerName.setText("");
        txtRedeemPoints.setText("0");
        txtQty.setText("1");
        
    }
    
    public void setButton(){
         try {
            int row = tblRedeem.getRowCount();
            
           if (row == 0){
               btnReedem.setEnabled(false);
               btnVoid.setEnabled(false);
           } else {
               if(Integer.parseInt(txtTotalPoints.getText()) < Integer.parseInt(txtRedeemPoints.getText()) ){
                   btnReedem.setEnabled(false);
                   btnVoid.setEnabled(true);
               }else {
                    btnReedem.setEnabled(true);
                    btnVoid.setEnabled(true);
               }
             
           }
        
        } catch (NumberFormatException e) {
        }
    }
    
    public void updateTableUsers(){
       rs = rewardDAO.readAllSortByID();
       tblReward.setModel(CustomDBUtils.resultSetToTableModel(rs));
       
       
       TableHelper.renderHeader(tblReward, "Reward ID","Reward Name","Points","Stocks");
         
       tblReward.getColumnModel().getColumn(0).setPreferredWidth(100);
       tblReward.getColumnModel().getColumn(1).setPreferredWidth(200);
       tblReward.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
    public void addReward(){
        int row = tblReward.getSelectedRow();
        TableModel model1 = tblReward.getModel();
        String getName = model1.getValueAt(row, 1).toString();
        int totalPoints = Integer.parseInt(model1.getValueAt(row, 2).toString()) 
                * Integer.parseInt(txtQty.getText().toString()) ;
        int totalStock = Integer.parseInt(model1.getValueAt(row, 3).toString());
        String idExisted = "";
        int qtyExisted = 0;
        int qtyTotal = 0;
        int pointsTotal = 0;
        boolean exists = false;

         for (int i = 0; i < tblRedeem.getRowCount(); i++) {
                idExisted = tblRedeem.getValueAt(i, 0).toString();
                qtyExisted = Integer.parseInt(tblRedeem.getValueAt(i, 1).toString());
                
                 if(getName.equals(idExisted)){
                      qtyTotal = qtyExisted
                         + Integer.parseInt(txtQty.getText());
                        pointsTotal = Integer.parseInt(model1.getValueAt(row, 2).toString())
                         * qtyTotal;
                        
                    if(totalStock < qtyTotal){
                        showMessageError("Insufficient Stocks");
                        exists = true;
                    } else {
                        DefaultTableModel model = (DefaultTableModel) tblRedeem.getModel();
                        model.setValueAt(qtyTotal, i, 1);
                        model.setValueAt(pointsTotal, i, 2);
                        exists = true;
                        break;   
                    }   
                      
                 }
                
               
         }
        
          if (!exists) {
               if (totalStock < Integer.parseInt(txtQty.getText())) {
              showMessageError("Insufficient Stocks");
        }else {
            DefaultTableModel model2 = (DefaultTableModel) tblRedeem.getModel();
                model2.addRow(new Object[]{model1.getValueAt(row, 1).toString(), 
                    txtQty.getText(), 
                   totalPoints});
        }
          }
       
        
        totalPoints();
                
          
    }
    
     public void showMessageError(String errorString){
        JOptionPane.showMessageDialog(null, 
                errorString, 
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
    }
     
      private Boolean isRFIDExist() {
        boolean success = true;
        if(!customerRFIDDAO.checkRFIDExisted(txtRFIDNumber.getText())) {
            success = false;
            showMessageError("Customer Card is not exist.");
            txtCustomerName.setText("");
            txtTotalPoints.setText("");
            txtRFIDNumber.setText("");
            txtRFIDNumber.requestFocus();
        }
        
        return success;
    }
      
      public void totalPoints(){
          int redeemPoints = 0;
                for (int i = 0; i < tblRedeem.getRowCount(); i++) {
                    int points33333 = Integer.parseInt(tblRedeem.getValueAt(i, 2).toString());
                    redeemPoints += points33333;
                }        
                
         txtRedeemPoints.setText(String.valueOf(redeemPoints));
         setButton();
      }
      
       private void getCustomerCardInfo(){
        List<Object> getCustomerCardInfo = 
                customerDAO.getCustomerCardPointsInfo(txtRFIDNumber.getText());
        
        txtTotalPoints.setText(getCustomerCardInfo.get(3).toString());
        txtCustomerName.setText(getCustomerCardInfo.get(2).toString() + ", "+
                getCustomerCardInfo.get(0).toString() + " " + getCustomerCardInfo.get(1).toString());
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
        txtRFIDNumber = new javax.swing.JTextField();
        txtTotalPoints = new java.awt.Label();
        label4 = new java.awt.Label();
        txtCustomerName = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReward = new javax.swing.JTable();
        label5 = new java.awt.Label();
        txtRedeemPoints = new java.awt.Label();
        btnReedem = new java.awt.Button();
        btnVoid = new java.awt.Button();
        btnBack = new java.awt.Button();
        btnClear = new java.awt.Button();
        label6 = new java.awt.Label();
        txtQty = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRedeem = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Redeem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(204, 204, 204))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        label2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label2.setForeground(new java.awt.Color(204, 204, 204));
        label2.setName(""); // NOI18N
        label2.setText("RFID Number :");

        label3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label3.setForeground(new java.awt.Color(204, 204, 204));
        label3.setName(""); // NOI18N
        label3.setText("Customer Points :");

        txtRFIDNumber.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRFIDNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRFIDNumberKeyPressed(evt);
            }
        });

        txtTotalPoints.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtTotalPoints.setForeground(new java.awt.Color(204, 204, 204));

        label4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label4.setForeground(new java.awt.Color(204, 204, 204));
        label4.setName(""); // NOI18N
        label4.setText("Customer Name :");

        txtCustomerName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(204, 204, 204));

        tblReward.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reward ID", "Reward Name", "Points", "Stocks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReward.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRewardMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblRewardMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblReward);

        label5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label5.setForeground(new java.awt.Color(204, 204, 204));
        label5.setName(""); // NOI18N
        label5.setText("Redeem Points :");

        txtRedeemPoints.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtRedeemPoints.setForeground(new java.awt.Color(204, 204, 204));
        txtRedeemPoints.setText("0");

        btnReedem.setBackground(new java.awt.Color(102, 102, 0));
        btnReedem.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnReedem.setForeground(new java.awt.Color(204, 204, 204));
        btnReedem.setLabel("Redeem IT!!");
        btnReedem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReedemActionPerformed(evt);
            }
        });

        btnVoid.setBackground(new java.awt.Color(102, 102, 0));
        btnVoid.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnVoid.setForeground(new java.awt.Color(204, 204, 204));
        btnVoid.setLabel("Void");
        btnVoid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoidActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(102, 102, 0));
        btnBack.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(204, 204, 204));
        btnBack.setLabel("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(102, 102, 0));
        btnClear.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnClear.setForeground(new java.awt.Color(204, 204, 204));
        btnClear.setLabel("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        label6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label6.setForeground(new java.awt.Color(204, 204, 204));
        label6.setName(""); // NOI18N
        label6.setText("Qty :");

        txtQty.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtQty.setText("1");
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });

        tblRedeem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reward Name", "qty", "Points"
            }
        ));
        jScrollPane2.setViewportView(tblRedeem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTotalPoints, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtRFIDNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                .addComponent(txtRedeemPoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(btnReedem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRFIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRedeemPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReedem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoid, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtRFIDNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRFIDNumberKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(isRFIDExist()) {
                setIDIDRFID(txtRFIDNumber.getText());
                getCustomerCardInfo();
                txtRFIDNumber.setText("");
            }
        }
    }//GEN-LAST:event_txtRFIDNumberKeyPressed

    private void tblRewardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRewardMouseReleased
       
    }//GEN-LAST:event_tblRewardMouseReleased

    private void btnReedemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReedemActionPerformed
         String idReward = "";
         int stocksReward = 0;
        
        for (int i = 0; i < tblRedeem.getRowCount(); i++) {
               idReward = tblRedeem.getValueAt(i, 0).toString();
               stocksReward = Integer.parseInt(tblRedeem.getValueAt(i, 1).toString());             
               rewardDAO.updateReward(idReward, stocksReward);
        }
        
        int totalPoints = Integer.parseInt(txtTotalPoints.getText()) -
                 Integer.parseInt(txtRedeemPoints.getText());
        rewardDAO.updateCustomerPoints(getIDIDRFID(), totalPoints);
        
        JOptionPane.showMessageDialog(this, "Successfully Redeem!");
        
        dispose();
    }//GEN-LAST:event_btnReedemActionPerformed

    private void btnVoidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoidActionPerformed
          DefaultTableModel model = (DefaultTableModel) tblRedeem.getModel();
                            model.removeRow(tblRedeem.getSelectedRow());
                            totalPoints();
                     
    }//GEN-LAST:event_btnVoidActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
      dispose();
                
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
       clear();
       setButton();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblRewardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRewardMouseClicked
    if(txtCustomerName.getText().equals("")){
          JOptionPane.showMessageDialog(this, "Please tap RFID Card Number");
         txtQty.setText("1");
          setButton();
      }else {
          addReward();
          txtQty.setText("1");
          setButton();
      }
    }//GEN-LAST:event_tblRewardMouseClicked

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQtyKeyPressed

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
            java.util.logging.Logger.getLogger(RewardCashierDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RewardCashierDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RewardCashierDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RewardCashierDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RewardCashierDialog dialog = new RewardCashierDialog(new javax.swing.JFrame(), true);
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
    private java.awt.Button btnBack;
    private java.awt.Button btnClear;
    private java.awt.Button btnReedem;
    private java.awt.Button btnVoid;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private javax.swing.JTable tblRedeem;
    private javax.swing.JTable tblReward;
    private java.awt.Label txtCustomerName;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtRFIDNumber;
    private java.awt.Label txtRedeemPoints;
    private java.awt.Label txtTotalPoints;
    // End of variables declaration//GEN-END:variables
}

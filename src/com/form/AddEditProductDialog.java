/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.ProductCategoryDAO;
import com.dao.ProductDAO;
import com.dao.ProductStockDAO;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author shaitozen007
 */
public class AddEditProductDialog extends javax.swing.JDialog {

    private InventoryInternalFrame inventoryInternalFrame;
    private ManagerFrame managerFrame;
    private ResultSet rs = null;
    final private ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
    final private ProductDAO productDAO = new ProductDAO();
    final private ProductStockDAO productStockDAO = new ProductStockDAO();
    public int id;
    private String type;
    /**
     * Creates new form AddEditProductDialog
     */
    public AddEditProductDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public AddEditProductDialog(java.awt.Frame parent, Container container, boolean modal, int id, String type) {
        super(parent, modal);
        initComponents();
        
        this.inventoryInternalFrame = (InventoryInternalFrame) (JInternalFrame) container;
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        this.id = id;
        this.type = type;
        updateProductCategories();
        
        if(type.equals("New")){
              btnSaveUpdate.setText("Save");
              
        }else{
            TitledBorder border = new TitledBorder("Edit Product");
            border.setTitleFont(new Font("Verdana", Font.BOLD, 12));
            border.setTitleColor(Color.WHITE);
            panelTitle.setBorder(border);
            btnSaveUpdate.setText("Update");
            getProductInfo(productDAO.getProductInfo(id));
        }
    }
    
    public void numberOnly(char c, JTextField jTextField){
        if(Character.isLetter(c)) {
             jTextField.setEditable(false);
         } else {
             jTextField.setEditable(true);
         }
    }
    
    public void showMessageError(String errorString){
        JOptionPane.showMessageDialog(null, 
                errorString, 
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    private Boolean isBarcodeExisted(){
        boolean isBarcodeExisted = false;
        
        if(productDAO.checkBarcodeExisted(txtBarcode.getText()) == true){
            isBarcodeExisted = false;
        }else{
            isBarcodeExisted = true;
        }
        return isBarcodeExisted;
    }
    
    private Boolean isBarcodeExisted(int id){
         boolean isBarcodeExisted = false;
        
        if(productDAO.checkBarcodeExisted(txtBarcode.getText(), id) == true){
            isBarcodeExisted = false;
        }else{
            isBarcodeExisted = true;
        }
        return isBarcodeExisted;
    }
    
    private Boolean validation(){
        boolean success = true;
        
        if(txtBarcode.getText().length() == 0){
            success = false;
            showMessageError("Please Input Barcode!");
        } else if(txtProductName.getText().length() == 0){
            success = false;
            showMessageError("Please Input Product Name!");
        } else if(txtDescription.getText().length() == 0){
            success = false;
            showMessageError("Please Input Description!");
        } else if(txtPurchasePrice.getText().length() == 0){
            success = false;
            showMessageError("Please Input Purchase Price!");
        } else if(txtSellingPrice.getText().length() == 0){
            success = false;
            showMessageError("Please Input Selling Price!");
        } else if(txtPoints.getText().length() == 0){
            success = false;
            showMessageError("Please Input Reward Points!");
        } else if(Double.parseDouble(txtPurchasePrice.getText()) >= Double.parseDouble(txtSellingPrice.getText())){
            success = false;
            showMessageError("Wag malulugi ka!");
        }
        
        return success;
    }
    
    public void addProducts(){
        boolean success = productDAO.create(getTextField());
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully created.");
            productStockDAO.create(getTextField());
            inventoryInternalFrame.updateTableProduct();
            inventoryInternalFrame.updateTableStock();
        }
        
        dispose();
    }
    
    public void updateProducts(){
        boolean success = productDAO.update(getTextField());
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully updated.");
            inventoryInternalFrame.updateTableProduct();
        }
        
        dispose();
    }
    
    public void getProductInfo(List<Object> getProductInfo){
        try {
            txtBarcode.setText(getProductInfo.get(0).toString());
            txtProductName.setText(getProductInfo.get(1).toString());
            cmbProductCategory.setSelectedItem(getProductInfo.get(2).toString());
            txtDescription.setText(getProductInfo.get(3).toString());
            txtPurchasePrice.setText(getProductInfo.get(4).toString());
            txtSellingPrice.setText(getProductInfo.get(5).toString());
            txtPoints.setText(getProductInfo.get(6).toString());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    public List<Object> getTextField(){
        List<Object> columnValues = new ArrayList<>();
        columnValues.add(id);
        columnValues.add(txtBarcode.getText());
        columnValues.add(txtProductName.getText());
        columnValues.add(cmbProductCategory.getSelectedItem().toString());
        columnValues.add(txtDescription.getText());
        columnValues.add(txtPurchasePrice.getText());
        columnValues.add(txtSellingPrice.getText());
        columnValues.add(txtPoints.getText());
        
             
        return columnValues;
    }
    
    public void updateProductCategories(){
        rs = productCategoryDAO.readAllSortByID();
        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            while(rs.next()){
                model.addElement(rs.getString("name"));
            }
        } catch (Exception e) {
        }
        
        cmbProductCategory.setModel(model);
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
        panelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        btnSaveUpdate = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rSButtonIconI1 = new rojerusan.RSButtonIconI();
        cmbProductCategory = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPurchasePrice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSellingPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txtPoints = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Products");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(185, 221, 217));

        panelTitle.setBackground(new java.awt.Color(0, 51, 51));
        panelTitle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Product", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Barcode :");

        btnSaveUpdate.setBackground(new java.awt.Color(0, 0, 51));
        btnSaveUpdate.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnSaveUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveUpdate.setText("Save");
        btnSaveUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUpdateActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 51));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Product Name :");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Product Category :");

        rSButtonIconI1.setBackground(new java.awt.Color(0, 51, 51));
        rSButtonIconI1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        rSButtonIconI1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconI1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Description :");

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Purchase Price :");

        txtPurchasePrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPurchasePriceKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Selling Price :");

        txtSellingPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSellingPriceKeyTyped(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtDescription.setLineWrap(true);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Reward Points :");

        txtPoints.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPointsKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPointsKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtProductName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProductCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBarcode)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonIconI1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSaveUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTitleLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTitleLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTitleLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPurchasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonIconI1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cmbProductCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPurchasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateActionPerformed
        // TODO add your handling code here:
        if(type.equals("New")){
            if(validation()) {
              if(isBarcodeExisted()){
                  addProducts();
              }else {
                  showMessageError("Already Existed Barcode!");
              }
            } 
        } else {
            if(validation()) {
                if(isBarcodeExisted(id)){
                  updateProducts();
              }else {
                  showMessageError("Already Existed Barcode!");
              }
            } 
        }
            
    }//GEN-LAST:event_btnSaveUpdateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rSButtonIconI1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconI1ActionPerformed
        ProductCategoryDialog addEditProductCategory = new ProductCategoryDialog(managerFrame,this, true);
        addEditProductCategory.setVisible(true);
    }//GEN-LAST:event_rSButtonIconI1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowActivated

    private void txtPurchasePriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPurchasePriceKeyTyped
        if(Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }else{
            try {
                Double.parseDouble(txtPurchasePrice.getText()+evt.getKeyChar());
            } catch (NumberFormatException e) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtPurchasePriceKeyTyped

    private void txtSellingPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSellingPriceKeyTyped
        if(Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }else{
            try {
                Double.parseDouble(txtPurchasePrice.getText()+evt.getKeyChar());
            } catch (NumberFormatException e) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtSellingPriceKeyTyped

    private void txtPointsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPointsKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPointsKeyTyped

    private void txtPointsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPointsKeyPressed
        numberOnly(evt.getKeyChar(), txtPoints);
    }//GEN-LAST:event_txtPointsKeyPressed

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
            java.util.logging.Logger.getLogger(AddEditProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEditProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEditProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEditProductDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEditProductDialog dialog = new AddEditProductDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSaveUpdate;
    private javax.swing.JComboBox<String> cmbProductCategory;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTitle;
    private rojerusan.RSButtonIconI rSButtonIconI1;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtPoints;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtPurchasePrice;
    private javax.swing.JTextField txtSellingPrice;
    // End of variables declaration//GEN-END:variables
}

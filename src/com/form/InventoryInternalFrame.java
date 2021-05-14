/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.ProductDAO;
import com.dao.ProductStockDAO;
import com.utils.CustomDBUtils;
import com.utils.TableHelper;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *z
 * @author shaitozen
 */
public class InventoryInternalFrame extends javax.swing.JInternalFrame {
    
  
    private final ManagerFrame managerFrame;
    final private ProductDAO productDAO = new ProductDAO();
    final private ProductStockDAO productStockDAO = new ProductStockDAO();
    private ResultSet rs = null;
    private int id;

  
    /**
     * Creates new form UsersInternalFrame
     */
     
    public InventoryInternalFrame(ManagerFrame managerFrame) {
        initComponents();
        this.managerFrame = managerFrame;
               
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
        
        setButtonForProduct();
        setButtonForStock();
        
    }
     
    private static InventoryInternalFrame myInstance;
    public static InventoryInternalFrame getInstance(ManagerFrame managerFrame){
        if(myInstance == null){
            myInstance = new InventoryInternalFrame(managerFrame);
        }
        return myInstance;
    }
    
    public void updateTableProduct(){
       rs = productDAO.readAllSortByID();
       tblProduct.setModel(CustomDBUtils.resultSetToTableModel(rs));
       
         TableHelper.renderHeader(tblProduct, "Product ID",
                 "Barcode", "Product Name",
                 "Product Category", "Purchase Price", "Selling Price"); 
    } 
    
    public void updateTableStock(){
       rs = productStockDAO.readAllSortByID();
       tblStocks.setModel(CustomDBUtils.resultSetToTableModel(rs));
       
         TableHelper.renderHeader(tblStocks, "Product ID", "Product Category", 
                 "Product Name", "Quantity"); 
    } 
    
    public void deleteProduct(int id){
        
        boolean success = productDAO.delete(id);
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully deleted.");
            updateTableProduct();
            updateTableStock();
        }   
    }
    
    private void clearForProduct(){
       btnNewProduct.setEnabled(true);
       btnEditProduct.setEnabled(false);
       btnDeleteProduct.setEnabled(false);
       tblProduct.clearSelection();
    }
    
    private void clearForStock(){
       btnNewProduct.setEnabled(false);
       btnEditProduct.setEnabled(false);
       btnDeleteProduct.setEnabled(false);
       tblStocks.clearSelection();
    }
    
    public void setButtonForProduct() {
        try {
            int row = tblProduct.getSelectedRow();
            
            if (row == -1){
               btnNewProduct.setEnabled(true);
               btnEditProduct.setEnabled(false);
               btnDeleteProduct.setEnabled(false);
          
            }else{
                btnNewProduct.setEnabled(false);
                btnEditProduct.setEnabled(true);
                btnDeleteProduct.setEnabled(true);
            
            }
        
        } catch (NumberFormatException e) {
        }
    }
    
    public void setButtonForStock() {
        try {
            int row = tblStocks.getSelectedRow();
            
            if (row == -1){
               btnNewStock.setEnabled(false);
               btnEditStock.setEnabled(false);
               btnDeleteStock.setEnabled(false);
          
            }else{
                btnNewStock.setEnabled(true);
                btnEditStock.setEnabled(true);
                btnDeleteStock.setEnabled(true);
            
            }
        
        } catch (NumberFormatException e) {
        }
    }
    
    
    public void getSelectedInfoForStock(AddEditProductStockDialog addEditProductStockDialog){
         int row = tblStocks.getSelectedRow();
         TableModel model = tblStocks.getModel();
         
         String productID = model.getValueAt(row, 0).toString();
         String productCategory = model.getValueAt(row, 1).toString();
         String productName = model.getValueAt(row, 2).toString();
         
         addEditProductStockDialog.lbProductID.setText(productID);
         addEditProductStockDialog.lbProductCategory.setText(productCategory);
         addEditProductStockDialog.lbProductName.setText(productName);
         
    }
    
    public int getSelectedIDForProduct(){
          int row = tblProduct.getSelectedRow();
          TableModel model = tblProduct.getModel();
          
          int productId = Integer.parseInt(model.getValueAt(row, 0).toString());
       
          return productId;
        
    } 
    
    
 

  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        btnNewProduct = new javax.swing.JButton();
        btnEditProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        btnClearProduct = new javax.swing.JButton();
        btnInfoProduct = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbSearch1 = new javax.swing.JComboBox<>();
        txtSearch1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        btnNewStock = new javax.swing.JButton();
        btnEditStock = new javax.swing.JButton();
        btnDeleteStock = new javax.swing.JButton();
        btnClearStock = new javax.swing.JButton();
        btnInfoStock = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStocks = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setResizable(true);
        setTitle("Products");
        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(1166, 694));
        setRequestFocusEnabled(false);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(185, 221, 217));

        jTabbedPane1.setBackground(new java.awt.Color(185, 221, 217));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 153));
        jTabbedPane1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(185, 221, 217));

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Products Information");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btnNewProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnNewProduct.setText("New");
        btnNewProduct.setFocusable(false);
        btnNewProduct.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNewProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProductActionPerformed(evt);
            }
        });
        jToolBar2.add(btnNewProduct);

        btnEditProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        btnEditProduct.setText("Edit");
        btnEditProduct.setEnabled(false);
        btnEditProduct.setFocusable(false);
        btnEditProduct.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });
        jToolBar2.add(btnEditProduct);

        btnDeleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete_16x.png"))); // NOI18N
        btnDeleteProduct.setText("Delete");
        btnDeleteProduct.setEnabled(false);
        btnDeleteProduct.setFocusable(false);
        btnDeleteProduct.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDeleteProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDeleteProduct);

        btnClearProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnClearProduct.setText("Clear");
        btnClearProduct.setFocusable(false);
        btnClearProduct.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClearProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClearProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearProductActionPerformed(evt);
            }
        });
        jToolBar2.add(btnClearProduct);

        btnInfoProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info.png"))); // NOI18N
        btnInfoProduct.setText("Info");
        btnInfoProduct.setEnabled(false);
        btnInfoProduct.setFocusable(false);
        btnInfoProduct.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInfoProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInfoProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoProductActionPerformed(evt);
            }
        });
        jToolBar2.add(btnInfoProduct);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(21, 177, 255));
        jLabel4.setText("Search");

        cmbSearch1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cmbSearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Product ID", "Barcode", "Product Name", "Product Category" }));
        cmbSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearch1ActionPerformed(evt);
            }
        });

        txtSearch1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });
        txtSearch1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSearch1PropertyChange(evt);
            }
        });

        jButton2.setText("Search It Baby!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "Barcode", "Product Name", "Product Category", "Purchase Price", "Selling Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProductMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduct);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(220, 220, 220)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSearch1)
                    .addComponent(jLabel4)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName("Product Information");

        jTabbedPane1.addTab("Products", jPanel1);

        jPanel2.setBackground(new java.awt.Color(185, 221, 217));

        jLabel3.setBackground(new java.awt.Color(0, 102, 102));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Stocks Information");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        btnNewStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnNewStock.setText("Add");
        btnNewStock.setFocusable(false);
        btnNewStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNewStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNewStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewStockActionPerformed(evt);
            }
        });
        jToolBar3.add(btnNewStock);

        btnEditStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        btnEditStock.setText("Edit");
        btnEditStock.setEnabled(false);
        btnEditStock.setFocusable(false);
        btnEditStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditStockActionPerformed(evt);
            }
        });
        jToolBar3.add(btnEditStock);

        btnDeleteStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete_16x.png"))); // NOI18N
        btnDeleteStock.setText("Delete");
        btnDeleteStock.setEnabled(false);
        btnDeleteStock.setFocusable(false);
        btnDeleteStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDeleteStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeleteStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStockActionPerformed(evt);
            }
        });
        jToolBar3.add(btnDeleteStock);

        btnClearStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnClearStock.setText("Clear");
        btnClearStock.setFocusable(false);
        btnClearStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClearStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClearStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearStockActionPerformed(evt);
            }
        });
        jToolBar3.add(btnClearStock);

        btnInfoStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info.png"))); // NOI18N
        btnInfoStock.setText("Info");
        btnInfoStock.setEnabled(false);
        btnInfoStock.setFocusable(false);
        btnInfoStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInfoStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInfoStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoStockActionPerformed(evt);
            }
        });
        jToolBar3.add(btnInfoStock);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblStocks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Category", "Product Name", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStocks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblStocksMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblStocks);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 177, 255));
        jLabel1.setText("Search");

        cmbSearch.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cmbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Product ID", "Product Name" }));
        cmbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSearchPropertyChange(evt);
            }
        });

        jButton1.setText("Search It Baby!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(230, 230, 230))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSearch)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Stocks", jPanel2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        updateTableProduct();
        updateTableStock();
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProductActionPerformed
        AddEditProductDialog addEditProductDialog = new AddEditProductDialog(managerFrame, this, true,
        productDAO.autoNumber(), "New");
        addEditProductDialog.setVisible(true);
    }//GEN-LAST:event_btnNewProductActionPerformed

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        AddEditProductDialog addEditProductDialog = new AddEditProductDialog(managerFrame, this, true,
        getSelectedIDForProduct(), "Edit");
        addEditProductDialog.setVisible(true);
        clearForProduct();
    }//GEN-LAST:event_btnEditProductActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        int input = JOptionPane.showConfirmDialog(null, 
                        "Are you sure do you want to delete this product?", 
                        "Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                        if(input == 0) {
                            int id = getSelectedIDForProduct();
                            setButtonForProduct();
                            deleteProduct(id);
                            clearForProduct();
                        }
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void btnClearProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearProductActionPerformed
        clearForProduct();
    }//GEN-LAST:event_btnClearProductActionPerformed

    private void btnInfoProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoProductActionPerformed

    }//GEN-LAST:event_btnInfoProductActionPerformed

    private void btnNewStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewStockActionPerformed
        AddEditProductStockDialog addEditProductStockDialog = new 
        AddEditProductStockDialog(managerFrame, this, true, "Add");
        getSelectedInfoForStock(addEditProductStockDialog);
        addEditProductStockDialog.setVisible(true);
        
        
    }//GEN-LAST:event_btnNewStockActionPerformed

    private void btnEditStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditStockActionPerformed

    private void btnDeleteStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteStockActionPerformed

    private void btnClearStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearStockActionPerformed
        clearForStock();
    }//GEN-LAST:event_btnClearStockActionPerformed

    private void btnInfoStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInfoStockActionPerformed

    private void tblStocksMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStocksMouseReleased
        setButtonForStock();
    }//GEN-LAST:event_tblStocksMouseReleased

    private void cmbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchActionPerformed
 
    }//GEN-LAST:event_cmbSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSearchPropertyChange

    }//GEN-LAST:event_txtSearchPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearch1ActionPerformed

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void txtSearch1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSearch1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1PropertyChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblProductMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseReleased
        setButtonForProduct();
    }//GEN-LAST:event_tblProductMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearProduct;
    private javax.swing.JButton btnClearStock;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnDeleteStock;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnEditStock;
    private javax.swing.JButton btnInfoProduct;
    private javax.swing.JButton btnInfoStock;
    private javax.swing.JButton btnNewProduct;
    private javax.swing.JButton btnNewStock;
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JComboBox<String> cmbSearch1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTable tblStocks;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}

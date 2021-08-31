/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.CustomerDAO;
import com.dao.CustomerPointsDAO;
import com.dao.CustomerRFIDDAO;
import com.utils.CustomDBUtils;
import com.utils.TableHelper;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author shaitozen
 */
public class CustomerJDialog extends javax.swing.JDialog {

    private CashierFrame cashierFrame;
    final private CustomerDAO customerDAO = new CustomerDAO();
    final private CustomerRFIDDAO customerRFIDDAO = new CustomerRFIDDAO();
    final private CustomerPointsDAO customerPointsDAO = new CustomerPointsDAO();
    private ResultSet rs = null;
    
    public CustomerJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();      
    }
    
    public CustomerJDialog(java.awt.Frame parent, boolean modal, CashierFrame cashierFrame) {
        super(parent, modal);
        initComponents();
        
         this.cashierFrame = cashierFrame;
         
        updateTableRFID();
        updateTableCustomer();
        updateTablePoints(); 
     
        jTabbedPane1.remove(2);
       
    }
    
    public void updateTablePoints(){
        rs = customerPointsDAO.readAllSortByID();
        tblPoints.setModel(CustomDBUtils.resultSetToTableModel(rs));
        
           TableHelper.renderHeader(tblPoints, "Customer ID", "Points");
    }
    
    public void updateTableCustomer(){
        rs = customerDAO.readAllSortByID();
        tblCustomer.setModel(CustomDBUtils.resultSetToTableModel(rs,4));
        
           TableHelper.renderHeader(tblCustomer, "Customer ID","Full Name",
                   "Age","Gender","Contact Number");
    }
    
    public void updateTableRFID(){
        rs = customerRFIDDAO.readAllSortByID();
        tblRFID.setModel(CustomDBUtils.resultSetToTableModel(rs));
        
           TableHelper.renderHeader(tblRFID, "Customer ID","Full Name",
                   "RFID Number");
    }
    
    private void clearForCustomer(){
       btnNewCustomer.setEnabled(true);
       btnEditCustomer.setEnabled(false);
       btnDeleteCustomer.setEnabled(false);
       tblCustomer.clearSelection();
    }
    
    private void clearForRFID(){
       btnAddRFID.setEnabled(false);
       btnEditRFID.setEnabled(false);
       btnDeleteRFID.setEnabled(false);
       tblRFID.clearSelection();
    }
    
    public void setButtonForCustomer() {
        try {
            int row = tblCustomer.getSelectedRow();
            
            if (row == -1){
               btnNewCustomer.setEnabled(true);
               btnEditCustomer.setEnabled(false);
               btnDeleteCustomer.setEnabled(false);
          
            }else{
                btnNewCustomer.setEnabled(false);
                btnEditCustomer.setEnabled(true);
                btnDeleteCustomer.setEnabled(true);
            
            }
        
        } catch (NumberFormatException e) {
        }
    }
    
    public void setButtonForRFID() {
        try {
            int row = tblRFID.getSelectedRow();
            TableModel model = tblRFID.getModel();
            
            String rfidValidate = model.getValueAt(row, 2).toString();
            if(rfidValidate.equals("none")){
                 btnAddRFID.setEnabled(true);
                 btnEditRFID.setEnabled(false);
            } else {
                 btnAddRFID.setEnabled(false);
                 btnEditRFID.setEnabled(true);
            }
            
            if (row == -1){         
               btnDeleteRFID.setEnabled(false);         
            }else{         
               btnDeleteRFID.setEnabled(true);         
            }
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    public void deleteCustomer(int id) {
        
        boolean success = customerDAO.delete(id);
        
        if(success) {
            JOptionPane.showMessageDialog(null, "Successfully deleted.");
            customerRFIDDAO.delete(id);
            customerPointsDAO.delete(id);
            updateTableCustomer();
            updateTableRFID();
            updateTablePoints();
        }
    }
    
    public void deleteCustomerRFID(int id) {
        
        boolean success = customerDAO.delete(id);
        
        if(success) {
            JOptionPane.showMessageDialog(null, "Successfully deleted.");
            customerRFIDDAO.delete(id);
            updateTableCustomer();
            updateTableRFID();
            updateTablePoints();
        }
    }
    
    public void deleteRFID(int id) {
        
        boolean success = customerRFIDDAO.deleteRFID(id);
        
        if(success) {
            JOptionPane.showMessageDialog(null, "Successfully deleted.");
            updateTableRFID();
        }
    }
    
    public int getSelectedIDForCustomer(){
          int row = tblCustomer.getSelectedRow();
          TableModel model = tblCustomer.getModel();
          
          int customerID = Integer.parseInt(model.getValueAt(row, 0).toString());
       
          return customerID;
        
    } 
    
    public int getSelectedIDForRFID(){
          int row = tblRFID.getSelectedRow();
          TableModel model = tblRFID.getModel();
          
          int rfidNumber = Integer.parseInt(model.getValueAt(row, 0).toString());
       
          return rfidNumber;
        
    } 
    
    public String getSelectedFullNameForRFID(){
          int row = tblRFID.getSelectedRow();
          TableModel model = tblRFID.getModel();
          
          String fullname = model.getValueAt(row, 1).toString();
       
          return fullname;
        
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
        btnNewCustomer = new javax.swing.JButton();
        btnEditCustomer = new javax.swing.JButton();
        btnDeleteCustomer = new javax.swing.JButton();
        btnClearCustomer = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbSearch1 = new javax.swing.JComboBox<>();
        txtSearch1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        btnAddRFID = new javax.swing.JButton();
        btnEditRFID = new javax.swing.JButton();
        btnDeleteRFID = new javax.swing.JButton();
        btnClearRFID = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRFID = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jToolBar4 = new javax.swing.JToolBar();
        btnAddPoints = new javax.swing.JButton();
        btnEditPoints = new javax.swing.JButton();
        btnClearRFID1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPoints = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cmbSearch2 = new javax.swing.JComboBox<>();
        txtSearch2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Frame");

        jPanel5.setBackground(new java.awt.Color(185, 221, 217));

        jTabbedPane1.setBackground(new java.awt.Color(185, 221, 217));
        jTabbedPane1.setForeground(new java.awt.Color(0, 0, 153));
        jTabbedPane1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(185, 221, 217));

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Customers Information");
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btnNewCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnNewCustomer.setText("New");
        btnNewCustomer.setFocusable(false);
        btnNewCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNewCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNewCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCustomerActionPerformed(evt);
            }
        });
        jToolBar2.add(btnNewCustomer);

        btnEditCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        btnEditCustomer.setText("Edit");
        btnEditCustomer.setEnabled(false);
        btnEditCustomer.setFocusable(false);
        btnEditCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCustomerActionPerformed(evt);
            }
        });
        jToolBar2.add(btnEditCustomer);

        btnDeleteCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete_16x.png"))); // NOI18N
        btnDeleteCustomer.setText("Delete");
        btnDeleteCustomer.setEnabled(false);
        btnDeleteCustomer.setFocusable(false);
        btnDeleteCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDeleteCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDeleteCustomer);

        btnClearCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnClearCustomer.setText("Clear");
        btnClearCustomer.setFocusable(false);
        btnClearCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClearCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClearCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCustomerActionPerformed(evt);
            }
        });
        jToolBar2.add(btnClearCustomer);

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

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Full Name", "Age", "Gender", "Contact Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblCustomerMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

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

        jTabbedPane1.addTab("Customer", jPanel1);

        jPanel2.setBackground(new java.awt.Color(185, 221, 217));

        jLabel3.setBackground(new java.awt.Color(0, 102, 102));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("RFID Information");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);

        btnAddRFID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnAddRFID.setText("Add");
        btnAddRFID.setEnabled(false);
        btnAddRFID.setFocusable(false);
        btnAddRFID.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAddRFID.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRFIDActionPerformed(evt);
            }
        });
        jToolBar3.add(btnAddRFID);

        btnEditRFID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        btnEditRFID.setText("Re-Issue");
        btnEditRFID.setEnabled(false);
        btnEditRFID.setFocusable(false);
        btnEditRFID.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditRFID.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditRFIDActionPerformed(evt);
            }
        });
        jToolBar3.add(btnEditRFID);

        btnDeleteRFID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete_16x.png"))); // NOI18N
        btnDeleteRFID.setText("Delete");
        btnDeleteRFID.setEnabled(false);
        btnDeleteRFID.setFocusable(false);
        btnDeleteRFID.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDeleteRFID.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeleteRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRFIDActionPerformed(evt);
            }
        });
        jToolBar3.add(btnDeleteRFID);

        btnClearRFID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnClearRFID.setText("Clear");
        btnClearRFID.setFocusable(false);
        btnClearRFID.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClearRFID.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClearRFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRFIDActionPerformed(evt);
            }
        });
        jToolBar3.add(btnClearRFID);

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

        tblRFID.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Full Name", "RFID Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRFID.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblRFID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblRFIDMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblRFID);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 177, 255));
        jLabel1.setText("Search");

        cmbSearch.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cmbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Product ID", "Product Name", "Product Category", "Quantity" }));
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

        jTabbedPane1.addTab("RFID", jPanel2);

        jPanel6.setBackground(new java.awt.Color(185, 221, 217));

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Customer Points Information");
        jLabel5.setFocusable(false);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel7.setBackground(new java.awt.Color(153, 204, 255));

        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);

        btnAddPoints.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnAddPoints.setText("Add");
        btnAddPoints.setEnabled(false);
        btnAddPoints.setFocusable(false);
        btnAddPoints.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAddPoints.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPointsActionPerformed(evt);
            }
        });
        jToolBar4.add(btnAddPoints);

        btnEditPoints.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        btnEditPoints.setText("Edit");
        btnEditPoints.setEnabled(false);
        btnEditPoints.setFocusable(false);
        btnEditPoints.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditPoints.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPointsActionPerformed(evt);
            }
        });
        jToolBar4.add(btnEditPoints);

        btnClearRFID1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnClearRFID1.setText("Clear");
        btnClearRFID1.setFocusable(false);
        btnClearRFID1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClearRFID1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnClearRFID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearRFID1ActionPerformed(evt);
            }
        });
        jToolBar4.add(btnClearRFID1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tblPoints.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Points"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPoints.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPoints.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPointsMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblPoints);

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(21, 177, 255));
        jLabel6.setText("Search");

        cmbSearch2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cmbSearch2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Product ID", "Product Name", "Product Category", "Quantity" }));
        cmbSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearch2ActionPerformed(evt);
            }
        });

        txtSearch2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch2ActionPerformed(evt);
            }
        });
        txtSearch2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSearch2PropertyChange(evt);
            }
        });

        jButton3.setText("Search It Baby!");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(230, 230, 230))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSearch2)
                    .addComponent(jLabel6)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Points", jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCustomerActionPerformed
        AddEditCustomerDialog addEditCustomerDialog = new
        AddEditCustomerDialog(cashierFrame, this, true, customerDAO.autoNumber(), "New");
        addEditCustomerDialog.setVisible(true);
        
        
    }//GEN-LAST:event_btnNewCustomerActionPerformed

    private void btnEditCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCustomerActionPerformed
        AddEditCustomerDialog addEditCustomerDialog = new
        AddEditCustomerDialog(cashierFrame, this, true, getSelectedIDForCustomer(), "Update");
        addEditCustomerDialog.setVisible(true);

        clearForCustomer();
    }//GEN-LAST:event_btnEditCustomerActionPerformed

    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed
        int input = JOptionPane.showConfirmDialog(null,
            "Are you sure do you want to delete this customer?",
            "Warning",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if(input == 0) {
            int id = getSelectedIDForCustomer();
            setButtonForCustomer();
            deleteCustomer(id);
            clearForCustomer();
        }
    }//GEN-LAST:event_btnDeleteCustomerActionPerformed

    private void btnClearCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCustomerActionPerformed
        clearForCustomer();
    }//GEN-LAST:event_btnClearCustomerActionPerformed

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

    private void tblCustomerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseReleased
        setButtonForCustomer();
    }//GEN-LAST:event_tblCustomerMouseReleased

    private void btnAddRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRFIDActionPerformed
        AddEditRFIDDiaglog addEditRFIDDiaglog = new
        AddEditRFIDDiaglog(cashierFrame, this, true, getSelectedIDForRFID(), "New");
        addEditRFIDDiaglog.txtCustomerID.setText(String.valueOf(getSelectedIDForRFID()));
        addEditRFIDDiaglog.txtFullName.setText(getSelectedFullNameForRFID());
        addEditRFIDDiaglog.setVisible(true);
        clearForRFID();
    }//GEN-LAST:event_btnAddRFIDActionPerformed

    private void btnEditRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditRFIDActionPerformed
        AddEditRFIDDiaglog addEditRFIDDiaglog = new
        AddEditRFIDDiaglog(cashierFrame, this, true, getSelectedIDForRFID(), "Updated");
        addEditRFIDDiaglog.txtCustomerID.setText(String.valueOf(getSelectedIDForRFID()));
        addEditRFIDDiaglog.txtFullName.setText(getSelectedFullNameForRFID());
        addEditRFIDDiaglog.setVisible(true);
        clearForRFID();
    }//GEN-LAST:event_btnEditRFIDActionPerformed

    private void btnDeleteRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRFIDActionPerformed
        int input = JOptionPane.showConfirmDialog(null,
            "Are you sure do you want to delete this customer?",
            "Warning",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if(input == 0) {
            int id = getSelectedIDForRFID();
            setButtonForRFID();
            deleteRFID(id);
            clearForRFID();
        }
    }//GEN-LAST:event_btnDeleteRFIDActionPerformed

    private void btnClearRFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRFIDActionPerformed
        clearForRFID();
    }//GEN-LAST:event_btnClearRFIDActionPerformed

    private void tblRFIDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRFIDMouseReleased
        setButtonForRFID();
    }//GEN-LAST:event_tblRFIDMouseReleased

    private void cmbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchActionPerformed

    }//GEN-LAST:event_cmbSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSearchPropertyChange

    }//GEN-LAST:event_txtSearchPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblPointsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPointsMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPointsMouseReleased

    private void cmbSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSearch2ActionPerformed

    private void txtSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch2ActionPerformed

    private void txtSearch2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSearch2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch2PropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnClearRFID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearRFID1ActionPerformed
        // TODO add your handling code here:
           JOptionPane.showMessageDialog(this, "Under Maintenance");
    }//GEN-LAST:event_btnClearRFID1ActionPerformed

    private void btnEditPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPointsActionPerformed
        // TODO add your handling code here:
           JOptionPane.showMessageDialog(this, "Under Maintenance");
    }//GEN-LAST:event_btnEditPointsActionPerformed

    private void btnAddPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPointsActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Under Maintenance");
    }//GEN-LAST:event_btnAddPointsActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerJDialog dialog = new CustomerJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAddPoints;
    private javax.swing.JButton btnAddRFID;
    private javax.swing.JButton btnClearCustomer;
    private javax.swing.JButton btnClearRFID;
    private javax.swing.JButton btnClearRFID1;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnDeleteRFID;
    private javax.swing.JButton btnEditCustomer;
    private javax.swing.JButton btnEditPoints;
    private javax.swing.JButton btnEditRFID;
    private javax.swing.JButton btnNewCustomer;
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JComboBox<String> cmbSearch1;
    private javax.swing.JComboBox<String> cmbSearch2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblPoints;
    private javax.swing.JTable tblRFID;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JTextField txtSearch2;
    // End of variables declaration//GEN-END:variables
}

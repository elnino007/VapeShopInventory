/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.UserDAO;
import com.utils.CustomDBUtils;
import com.utils.TableHelper;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *z
 * @author shaitozen
 */
public class UsersInternalFrame extends javax.swing.JInternalFrame {
    
    final private UserDAO userDAO = new UserDAO();
    private final ManagerFrame managerFrame;
 
    
    private ResultSet rs = null;
    private int id;
    private final String userType;
    private final String username;

  
    /**
     * Creates new form UsersInternalFrame
     * @param managerFrame
     * @param userType
     * @param username
     */
     
    public UsersInternalFrame(ManagerFrame managerFrame, String userType, String username) {
        this.userType = userType;
        this.username = username;
      
        initComponents();
        this.managerFrame = managerFrame;
               
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bui = (BasicInternalFrameUI) this.getUI();
        bui.setNorthPane(null);
     
    }
    
   
    
    public void updateTableUsers(){
       rs = userDAO.readAllSortBy();
       tblUsers.setModel(CustomDBUtils.resultSetToTableModel(rs,4));
       
       
         TableHelper.renderHeader(tblUsers, "Username","Full Name","Age","Gender",
                 "User Type");
         
       tblUsers.getColumnModel().getColumn(0).setPreferredWidth(100);
       tblUsers.getColumnModel().getColumn(1).setPreferredWidth(200);
       tblUsers.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
    
    public void updateTableUsers(String column, String columnValue){
       rs = userDAO.readAllSortBy(column, columnValue);
       tblUsers.setModel(CustomDBUtils.resultSetToTableModel(rs));
       tblUsers.getColumnModel().getColumn(0).setPreferredWidth(100);
       tblUsers.getColumnModel().getColumn(1).setPreferredWidth(200);
       tblUsers.getColumnModel().getColumn(2).setPreferredWidth(50);
    }
     
    private static UsersInternalFrame myInstance;
    public static UsersInternalFrame getInstance(ManagerFrame managerFrame, String userType, String username){
//        if(myInstance == null){
            myInstance = new UsersInternalFrame(managerFrame, userType, username);
//        }
        
        return myInstance;
    }
    
    public void deleteUsers(String username){
        
        boolean success = userDAO.delete(username);
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully deleted.");
            updateTableUsers();
        }   
    }
    
    public String getSelectedUsername(){
          int row = tblUsers.getSelectedRow();
          TableModel model = tblUsers.getModel();
          
          String usernameSelected = model.getValueAt(row, 0).toString();
       
          return usernameSelected;
        
    }
    
    public String getSelectedUserType(){
          int row = tblUsers.getSelectedRow();
          TableModel model = tblUsers.getModel();
          
          String userTypeSelected = model.getValueAt(row, 4).toString();
       
          return userTypeSelected;
        
    }
    
    
    
    public Boolean getSelectedUserType(String userType){
          boolean success = false;
          int row = tblUsers.getSelectedRow();
          TableModel model = tblUsers.getModel();
          
          String userTypeSelected = model.getValueAt(row, 4).toString();
          
          success = (userType.equals("Manager") && userTypeSelected.equals("Owner")) 
                  || (userType.equals("Manager") && userTypeSelected.equals("Manager"));
          
          return success;
        
    }
         
    private void clear(){
       btnNew.setEnabled(true);
       btnEdit.setEnabled(false);
       btnDelete.setEnabled(false);
       tblUsers.clearSelection();
    }
    
    public void setButton() {
        try {
            int row = tblUsers.getSelectedRow();
            
            if (row == -1){
               btnNew.setEnabled(true);
               btnEdit.setEnabled(false);
               btnDelete.setEnabled(false);
            }else{
                btnNew.setEnabled(false);
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
            }
        
        } catch (NumberFormatException e) {
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
        jPanel3 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBorder(null);
        setClosable(true);
        setResizable(true);
        setTitle("Users");
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

        jPanel1.setBackground(new java.awt.Color(185, 221, 217));
        jPanel1.setPreferredSize(new java.awt.Dimension(1166, 694));

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar2.add(btnNew);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar2.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete_16x.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setEnabled(false);
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDelete);

        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete.png"))); // NOI18N
        btnDelete1.setText("Clear");
        btnDelete1.setFocusable(false);
        btnDelete1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDelete1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDelete1);

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

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 177, 255));
        jLabel1.setText("Search");

        cmbSearch.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cmbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Username", "First Name", "Middle Name", "Last Name", "User Type" }));
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

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Full Name", "Age", "Gender", "User Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblUsersMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsers);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setResizable(false);
            tblUsers.getColumnModel().getColumn(1).setResizable(false);
            tblUsers.getColumnModel().getColumn(2).setResizable(false);
            tblUsers.getColumnModel().getColumn(3).setResizable(false);
            tblUsers.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton1.setText("Search It Baby!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 102, 102));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Users Information");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(262, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSearch)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblUsersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseReleased
        setButton();
    }//GEN-LAST:event_tblUsersMouseReleased

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
       updateTableUsers();
    }//GEN-LAST:event_formInternalFrameActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        switch (cmbSearch.getSelectedIndex()) {
            case 0:
                updateTableUsers();
                txtSearch.setText("");
                break;
            case 1:
                updateTableUsers("username", txtSearch.getText());
                break;
            case 2:
                updateTableUsers("first_name", txtSearch.getText());
                break;
            case 3:
                updateTableUsers("middle_name", txtSearch.getText());
                break;
            case 4:
                updateTableUsers("last_name", txtSearch.getText());
                break;
            case 5:
                updateTableUsers("user_type", txtSearch.getText());
                break;
            default:
                break;
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
               if(getSelectedUserType(userType)) {
                    JOptionPane.showMessageDialog(null, 
                    "You dont have access to delete this users!", 
                    "Error!", 
                    JOptionPane.ERROR_MESSAGE);
               } else {
                   if(getSelectedUsername().equals(username)){
                        JOptionPane.showMessageDialog(null, 
                        "You cannot delete yourself!", 
                        "Error!", 
                        JOptionPane.ERROR_MESSAGE);
                   }else{
                        int input = JOptionPane.showConfirmDialog(null, 
                        "Are you sure do you want to delete this user?", 
                        "Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                        if(input == 0) {
                            String username = getSelectedUsername();
                            setButton();
                            deleteUsers(username);
                            clear();
                        }   
                    }
               }
            
            
     
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
       try {
            String usernameForEdit = getSelectedUsername();
                     
            AddEditUserDialog addEditUserDiaglog = new AddEditUserDialog(managerFrame, this, true, usernameForEdit);
            addEditUserDiaglog.txtUsername.setText(getSelectedUsername());
            if(getSelectedUserType(userType)) {
                
                  JOptionPane.showMessageDialog(null, 
                "You dont have access to edit this users!", 
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
                
            } else {
               if(userType.equals("Owner") || userType.equals("Manager")){
                   if(getSelectedUserType().equals("Owner")){
                        addEditUserDiaglog.cmbUserType.removeAllItems();
                        addEditUserDiaglog.cmbUserType.addItem("Owner");
                   } else{
                        addEditUserDiaglog.cmbUserType.removeAllItems();
                        addEditUserDiaglog.cmbUserType.addItem("Cashier");
                        addEditUserDiaglog.cmbUserType.addItem("Manager");
                        addEditUserDiaglog.cmbUserType.setSelectedItem(getSelectedUserType());
                   }
                   
              
                
            } else {
                addEditUserDiaglog.cmbUserType.removeAllItems();
               
            }
            addEditUserDiaglog.setVisible(true);
            clear();
            }
            
            
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
       
       
        
      
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {       
            AddEditUserDialog addEditUserDiaglog = new AddEditUserDialog(managerFrame, this, true, "new");
 
            if(userType.equals("Owner")){
                addEditUserDiaglog.cmbUserType.removeAllItems();
                addEditUserDiaglog.cmbUserType.addItem("Manager");
                addEditUserDiaglog.cmbUserType.addItem("Cashier");
            } else {
                addEditUserDiaglog.cmbUserType.removeAllItems();
                addEditUserDiaglog.cmbUserType.addItem("Cashier");
            }
            addEditUserDiaglog.setVisible(true);
            
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        

      
        

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtSearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSearchPropertyChange
       
    }//GEN-LAST:event_txtSearchPropertyChange

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cmbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchActionPerformed
        // TODO add your handling code here:
         switch (cmbSearch.getSelectedIndex()) {
            case 0:
                updateTableUsers();
                txtSearch.setText("");
                break;
        }
    }//GEN-LAST:event_cmbSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

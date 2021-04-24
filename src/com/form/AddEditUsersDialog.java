/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.UserDAO;
import com.model.Users;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author shaitozen007
 */
public class AddEditUsersDialog extends javax.swing.JDialog {
    
   
    private UsersInternalFrame usersInternalFrame;
    private ManagerFrame managerFrame;
    final private UserDAO userDAO = new UserDAO();
    public int id;
    
    /**
     * Creates new form ModalAddEditUsersFrame
     * @param parent
     * @param modal
     */
    public AddEditUsersDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
     
    }
    
    public AddEditUsersDialog(java.awt.Frame parent, Container container, boolean modal, int id) {
        super(parent, modal);
        initComponents();
        
        this.usersInternalFrame = (UsersInternalFrame) (JInternalFrame) container;
        
        setIconImage();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        if (id == 0){
            lbAddEditTittle.setText("Add Users");
            btnSave.setText("Save");
        }else{
            lbAddEditTittle.setText("Edit Users");
            btnSave.setText("Update");
           // getUsersInfo(userDAO.getUsersInfo(id));
        }
        
        this.id = id;

        
        
    }
    
    public void showMessageError(String errorString){
        JOptionPane.showMessageDialog(null, 
                errorString, 
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    public void numberOnly(char c, JTextField jTextField){
        if(Character.isLetter(c)) {
             jTextField.setEditable(false);
         } else {
             jTextField.setEditable(true);
         }
    }
    
    public void letterOnly(char c, JTextField jTextField){
        if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c)) {
             jTextField.setEditable(true);
         } else {
             jTextField.setEditable(false);
         }
    }
    
    public Boolean isUsernameExisted(){
        boolean isUsernameExisted = false;
        
        if(userDAO.checkUsernameExist(txtUsername.getText()) == true){
            isUsernameExisted = false;
        }else{
            isUsernameExisted = true;
        }
        
        return isUsernameExisted;
    }
    
//    public Boolean isUsernameExisted(int id){
//        boolean isUsernameExisted = false;
//        
//        if(userDAO.checkUsernameExist(id,txtUsername.getText()) == true){
//            isUsernameExisted = false;
//        }else{
//            isUsernameExisted = true;
//        }
//        
//        return isUsernameExisted;
//    }
    
    public Boolean validation(){
        boolean valid = false;
   
        if(txtFirstName.getText().length() == 0){         
            showMessageError("Please Input First Name!");
            valid = false;
        } 
        else if (txtMiddlename.getText().length() == 0) {
            showMessageError("Please Input Middle Name!");
            valid = false;
        } 
        else if (txtLastName.getText().length() == 0) {
            showMessageError("Please Input Last Name!");
            valid = false;
        }
        else if (txtAge.getText().length() == 0) {
            showMessageError("Please Input Age!");
            valid = false;
        }
        else if (rbMale.isSelected() == false && rbFemale.isSelected() == false) {
            showMessageError("Please Select Gender!");
            valid = false;
        }
        else if (txtUsername.getText().length() == 0) {
            showMessageError("Please Input Username!");
            valid = false;
        }
        else if (txtPassword.getText().length() == 0) {
            showMessageError("Please Input Password!");
            valid = false;
        }
        else if (txtConfirmPassword.getText().length() == 0) {
            showMessageError("Please Input Repeat Password!");
            valid = false;
        }else{
            valid = true;
        }
        
         return valid;
        
    }
    
     public void getUsersInfo(List<Object> getUsersInfo){
        txtFirstName.setText(getUsersInfo.get(0).toString());
        txtMiddlename.setText(getUsersInfo.get(1).toString());
        txtLastName.setText(getUsersInfo.get(2).toString());
        txtAge.setText(getUsersInfo.get(3).toString());
        txtUsername.setText(getUsersInfo.get(4).toString());
        txtPassword.setText(getUsersInfo.get(5).toString());
        txtConfirmPassword.setText(getUsersInfo.get(5).toString());
        cmbUserType.setSelectedItem(getUsersInfo.get(6).toString());
        
        boolean genderStatus = (Boolean) getUsersInfo.get(7);
        if(genderStatus == false){
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
    }
    
    public List<Object> getTextField(){
        List<Object> columnValues = new ArrayList<>();
        columnValues.add(Integer.parseInt(txtUserID.getText()));
        columnValues.add(txtFirstName.getText());
        columnValues.add(txtMiddlename.getText());
        columnValues.add(txtLastName.getText());
        columnValues.add(Integer.parseInt(txtAge.getText()));
        columnValues.add(txtUsername.getText());
        columnValues.add(txtPassword.getText());
        columnValues.add(cmbUserType.getSelectedItem().toString());
        if(rbMale.isSelected() == true){
            columnValues.add(false);
        }else{
            columnValues.add(true);
        }
             
        return columnValues;
    }
     
    public void newUsers(){
        
        boolean success = userDAO.create(getTextField());
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully created.");
            usersInternalFrame.updateTableUsers();
            
        }
        
        dispose();
        
    }
    
    public void editUsers(){
        
        boolean success = userDAO.update(getTextField());
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully updated.");
            usersInternalFrame.updateTableUsers();
            usersInternalFrame.setButton();
        }

        dispose();
        
    }
    
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/Icon-20.png")));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbAddEditTittle = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMiddlename = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        txtLastName = new javax.swing.JTextField();
        cmbUserType = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        txtAge = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();

        setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(185, 221, 217));

        lbAddEditTittle.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        lbAddEditTittle.setText("Add Users");

        txtUserID.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtUserID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserID.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Re Password:");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("First Name:");

        txtFirstName.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtFirstName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("User Type:");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Middle Name:");

        txtMiddlename.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtMiddlename.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMiddlename.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMiddlenameKeyPressed(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Last Name:");

        txtConfirmPassword.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtConfirmPassword.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtLastName.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtLastName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLastNameKeyPressed(evt);
            }
        });

        cmbUserType.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        cmbUserType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Cashier", "Manager"}));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Age:");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtAge.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtAge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAgeKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Password:");

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("User ID:");

        txtUsername.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(0, 0, 153));
        txtUsername.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Username:");

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Gender:");

        rbMale.setBackground(new java.awt.Color(185, 221, 217));
        buttonGroup1.add(rbMale);
        rbMale.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        rbMale.setText("Male");

        rbFemale.setBackground(new java.awt.Color(185, 221, 217));
        buttonGroup1.add(rbFemale);
        rbFemale.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        rbFemale.setText("Female");

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbAddEditTittle, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMiddlename, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(rbMale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbFemale))
                                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAddEditTittle)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMiddlename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rbMale)
                    .addComponent(rbFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(jButton2))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

//        if(validation()){
//            if(id == 0){
//                if(isUsernameExisted() == true){
//                 newUsers();
//                } else {
//                    showMessageError("The username is already existed!");
//                }
//            } else {
//                if(isUsernameExisted(id) == true){
//                      editUsers();
//                } else {
//                     showMessageError("The username is already existed!");
//                }
//            }
//        }   
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
      
    }//GEN-LAST:event_formWindowActivated

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        // TODO add your handling code here:
//        if(id == 0){
//            if(userDAO.checkUsernameExist(txtUsername.getText()) == true){
//            txtUsername.setForeground(new Color(204,0,0));
//        }else{
//            txtUsername.setForeground(new Color(0,0,153));
//        }
//        } else {
//            if(userDAO.checkUsernameExist(id, txtUsername.getText()) == true){
//            txtUsername.setForeground(new Color(204,0,0));
//        }else{
//            txtUsername.setForeground(new Color(0,0,153));
//        }
//        }
        
        
    }//GEN-LAST:event_txtUsernameKeyReleased
    
    private void txtFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyPressed
        // TODO add your handling code here:
        letterOnly(evt.getKeyChar(), txtFirstName);
    }//GEN-LAST:event_txtFirstNameKeyPressed

    private void txtMiddlenameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMiddlenameKeyPressed
        // TODO add your handling code here:
        letterOnly(evt.getKeyChar(), txtMiddlename);
    }//GEN-LAST:event_txtMiddlenameKeyPressed

    private void txtLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyPressed
        // TODO add your handling code here:
        letterOnly(evt.getKeyChar(), txtLastName);
    }//GEN-LAST:event_txtLastNameKeyPressed

    private void txtAgeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAgeKeyPressed
        // TODO add your handling code here:
        numberOnly(evt.getKeyChar(), txtAge);
    }//GEN-LAST:event_txtAgeKeyPressed

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
            java.util.logging.Logger.getLogger(AddEditUsersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEditUsersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEditUsersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEditUsersDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEditUsersDialog dialog = new AddEditUsersDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbUserType;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAddEditTittle;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JTextField txtAge;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMiddlename;
    private javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtUserID;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.UserDAO;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author shaitozen007
 */
public class AddEditUserDialog extends javax.swing.JDialog {

    private UsersInternalFrame usersInternalFrame;
    private ManagerFrame managerFrame;
    final private UserDAO userDAO = new UserDAO();
    public int id;
    public String username = "";
    
    
    public AddEditUserDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public AddEditUserDialog(java.awt.Frame parent, Container container, boolean modal, String username) {
        super(parent, modal);
        initComponents();
        setIconImage();
        this.usersInternalFrame = (UsersInternalFrame) (JInternalFrame) container;
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
       
        
        if (username.equals("new")){
            lbAddEditUsersTitle.setText("Add Users");
            btnSaveUpdate.setText("Save");
        }else{
            lbAddEditUsersTitle.setText("Edit Users");
            btnSaveUpdate.setText("Update");
            getUsersInfo(userDAO.getUsersInfo(username));
        }       
        
        this.username = username;
    }
    
     public void showMessageError(String errorString){
        JOptionPane.showMessageDialog(null, 
                errorString, 
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
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
    
    public Boolean isUsernameExisted(String username){
        boolean isUsernameExisted = false;
        
        if(userDAO.checkUsernameExist(txtUsername.getText(), this.username) == true){
            isUsernameExisted = false;
        }else{
            isUsernameExisted = true;
        }
        
        return isUsernameExisted;
    }
    
    public Boolean validation(){
        boolean valid = false;
   
        if (txtUsername.getText().length() == 0) {
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
        }
        else if(txtFirstName.getText().length() == 0){         
            showMessageError("Please Input First Name!");
            valid = false;
        } 
        else if (txtMiddleName.getText().length() == 0) {
            showMessageError("Please Input Middle Name!");
            valid = false;
        } 
        else if (txtLastName.getText().length() == 0) {
            showMessageError("Please Input Last Name!");
            valid = false;
        }
          else if (jDateBirthday.getDate() == null) {
            showMessageError("Please Input Birthday!");
            valid = false;
        }
        else if (rbMale.isSelected() == false && rbFemale.isSelected() == false) {
            showMessageError("Please Select Gender!");
            valid = false;
        }
        else if (txtAddress.getText().length() == 0) {
            showMessageError("Please Input Address");
            valid = false;    
        }
        else if (txtContact.getText().length() == 0) {
            showMessageError("Please Input Contact Number");
            valid = false;
        }
        else if (txtPassword.getText().equals(txtConfirmPassword.getText()) == false) {
            showMessageError("Password and Confirm Password did not match");
            valid = false;
        }
        else if (18 > Integer.parseInt(txtAge.getText())) {
            showMessageError("You cannot add users below 17 years old");
            valid = false;
        }
        
        else{
            valid = true;
        }
        
         return valid;
        
    }
    
    public void addUsers(){
        boolean success = userDAO.create(getTextField());
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully created.");
            usersInternalFrame.updateTableUsers();
            
        }
        
        dispose();
    }
    
    public void editUsers(){
        boolean success = userDAO.update(getTextFieldForUpdate());
        
        if(success){
            JOptionPane.showMessageDialog(null, "Successfully updated.");
            usersInternalFrame.updateTableUsers();
            
        }
        
        dispose();
    }
    
    public void getUsersInfo(List<Object> getUsersInfo){
        
        try {
            txtPassword.setText(getUsersInfo.get(0).toString());
            txtConfirmPassword.setText(getUsersInfo.get(0).toString());
            cmbUserType.setSelectedItem(getUsersInfo.get(1).toString());
            txtFirstName.setText(getUsersInfo.get(2).toString());
            txtMiddleName.setText(getUsersInfo.get(3).toString());
            txtLastName.setText(getUsersInfo.get(4).toString());
            Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(getUsersInfo.get(5).toString());
            jDateBirthday.setDate(birthdate);
            txtAge.setText(getUsersInfo.get(6).toString());
            txtAddress.setText(getUsersInfo.get(7).toString());
            txtContact.setText(getUsersInfo.get(8).toString());
            boolean genderStatus = (Boolean) getUsersInfo.get(9);
                if(genderStatus == false){
                    rbMale.setSelected(true);
                } else {
                    rbFemale.setSelected(true);
                }
        } catch (Exception e) {
        }
      
      
        
       
        
       
    }
    
    public List<Object> getTextFieldForUpdate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
         
        List<Object> columnValues = new ArrayList<>();
        columnValues.add(txtUsername.getText());
        columnValues.add(txtPassword.getText());
        columnValues.add(cmbUserType.getSelectedItem().toString());
        columnValues.add(txtFirstName.getText());
        columnValues.add(txtMiddleName.getText());
        columnValues.add(txtLastName.getText());
        columnValues.add(df.format(jDateBirthday.getDate()));
        columnValues.add(Integer.parseInt(txtAge.getText()));
        columnValues.add(txtAddress.getText());
        columnValues.add(txtContact.getText());
        if(rbMale.isSelected() == true){
            columnValues.add(false);
        }else{
            columnValues.add(true);
        }
        columnValues.add(this.username);    
        
        return columnValues;
    }
    
     public List<Object> getTextField(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
         
        List<Object> columnValues = new ArrayList<>();
        columnValues.add(txtUsername.getText());
        columnValues.add(txtPassword.getText());
        columnValues.add(cmbUserType.getSelectedItem().toString());
        columnValues.add(txtFirstName.getText());
        columnValues.add(txtMiddleName.getText());
        columnValues.add(txtLastName.getText());
        columnValues.add(df.format(jDateBirthday.getDate()));
        columnValues.add(Integer.parseInt(txtAge.getText()));
        columnValues.add(txtAddress.getText());
        columnValues.add(txtContact.getText());
        if(rbMale.isSelected() == true){
            columnValues.add(false);
        }else{
            columnValues.add(true);
        }
             
        return columnValues;
    }
    
    private void getAge(){
        try {
              DateFormat df = new SimpleDateFormat("yyyy");
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");  
              LocalDate now = LocalDate.now();
              int selectedYear = Integer.parseInt(df.format(jDateBirthday.getDate()));
              int currentYear = Integer.parseInt(dtf.format(now));
              int age = currentYear - selectedYear;
              
              
              txtAge.setText(String.valueOf(age));
        } catch (Exception e) {
          //  JOptionPane.showMessageDialog(this, e);
              txtAge.setText("0");
        }
    }
    
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/Icon-20.png")));
    }
    
    public void genderImage(){
        if(rbMale.isSelected() == true){
            jLabelGenderImage.setIcon(new ImageIcon(getClass().getResource("/resources/admin_male_100x.png")));
        }else{
             jLabelGenderImage.setIcon(new ImageIcon(getClass().getResource("/resources/admin_girl_100x.png")));
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbAddEditUsersTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelGenderImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbUserType = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        txtFirstName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMiddleName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jDateBirthday = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        txtAddress = new javax.swing.JTextField();
        btnSaveUpdate = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(185, 221, 217));

        lbAddEditUsersTitle.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lbAddEditUsersTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAddEditUsersTitle.setText("Registration Form");

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setToolTipText("");
        jPanel2.setName(""); // NOI18N

        jLabelGenderImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_male_100x.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username :");

        txtUsername.setForeground(new java.awt.Color(51, 102, 0));
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password :");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Confirm Password:");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Type :");

        cmbUserType.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelGenderImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelGenderImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbUserType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("First Name :");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Middle Name :");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Last Name :");

        jDateBirthday.setDateFormatString("MM/dd/yyyy");
        jDateBirthday.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateBirthdayPropertyChange(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Birthday :");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Age :");

        txtAge.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Address :");

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Contact :");

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sex :");

        buttonGroup1.add(rbMale);
        rbMale.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        rbMale.setForeground(new java.awt.Color(255, 255, 255));
        rbMale.setSelected(true);
        rbMale.setText("Male");
        rbMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMaleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbFemale);
        rbFemale.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        rbFemale.setForeground(new java.awt.Color(255, 255, 255));
        rbFemale.setText("Female");
        rbFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFemaleActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLastName)
                                    .addComponent(jDateBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(rbMale)
                        .addGap(29, 29, 29)
                        .addComponent(rbFemale))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnSaveUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(txtContact)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMiddleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(rbMale)
                    .addComponent(rbFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSaveUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(lbAddEditUsersTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAddEditUsersTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMaleActionPerformed
        // TODO add your handling code here:
        genderImage();
        
    }//GEN-LAST:event_rbMaleActionPerformed

    private void rbFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFemaleActionPerformed
        // TODO add your handling code here:
        genderImage();
    }//GEN-LAST:event_rbFemaleActionPerformed

    private void jDateBirthdayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateBirthdayPropertyChange
        getAge();
    }//GEN-LAST:event_jDateBirthdayPropertyChange

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSaveUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUpdateActionPerformed
        // TODO add your handling code here:
        if(validation()){
            if(username.equals("new")){
                if(isUsernameExisted() == true){
                 addUsers();
                } else {
                    showMessageError("The username is already existed!");
                }
            } else {
                if(isUsernameExisted(username) == true){
                      editUsers();
                } else {
                     showMessageError("The username is already existed!");
                }
            }
        }
        
    }//GEN-LAST:event_btnSaveUpdateActionPerformed

    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        // TODO add your handling code here:
        if(username.equals("new")){
            if(userDAO.checkUsernameExist(txtUsername.getText()) == true){
                txtUsername.setForeground(new Color(204,0,0));
            }else{
                txtUsername.setForeground(new Color(51,102,0));
            }
        } else {
            if(userDAO.checkUsernameExist(txtUsername.getText(), this.username) == true){
                txtUsername.setForeground(new Color(204,0,0));
            }else{
                txtUsername.setForeground(new Color(51,102,0));
            }
        }
    }//GEN-LAST:event_txtUsernameKeyReleased

    
   
    
    
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
            java.util.logging.Logger.getLogger(AddEditUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEditUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEditUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEditUserDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEditUserDialog dialog = new AddEditUserDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox<String> cmbUserType;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateBirthday;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelGenderImage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbAddEditUsersTitle;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMiddleName;
    private javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

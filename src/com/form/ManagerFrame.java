/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author shaitozen
 */
public final class ManagerFrame extends javax.swing.JFrame {

    /**
     * Creates new form ManagerForm
     */
    public boolean gender;
    public String username;

    public ManagerFrame() {
        initComponents();
        setIconImage();
        showTime();
      
        showHomeInternalFrame();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
       
    }
    
    private void showInternalFrame(JInternalFrame internalFrame) {
        removeDesktopComponents();
        desktopReal.add(internalFrame);
        
        try {
            internalFrame.setMaximum(true);
        } catch (Exception e) {
       
        }
        internalFrame.setVisible(true);
    }
    
    public void showProductInternalFrame(){
        InventoryInternalFrame productInternalFrame = InventoryInternalFrame.getInstance(this);
        showInternalFrame(productInternalFrame);
    }
    
    public void showHomeInternalFrame(){
        HomeInternalFrame homeInternalFrame = HomeInternalFrame.getInstance(this);
        showInternalFrame(homeInternalFrame);
    }
    
    public void showUsersInternalFrame(){
        UsersInternalFrame usersInternalFrame = UsersInternalFrame.getInstance(this, lbUserType.getText(), username);
        showInternalFrame(usersInternalFrame);
            
    }
    
    public void genderIcon(){
        try {
            if(gender == false){
                jLabelAdmin.setIcon(new ImageIcon(getClass().getResource("/resources/admin_male_100x.png")));
            }else{
                jLabelAdmin.setIcon(new ImageIcon(getClass().getResource("/resources/admin_girl_100x.png")));
            }
        } catch (Exception e) {
        }
    }
    
    private void showDate(){
        try {
             Date d = new Date();
                    SimpleDateFormat s = new SimpleDateFormat("MMMM-dd-yyyy");
                    jLabelDate.setText(s.format(d));
        } catch (Exception e) {
        }
    }
    
    private void showTime(){
        try {
            new Timer(0, new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent ae) {
                    Date d = new Date();
                    SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                    jLabelTime.setText(s.format(d));
                    showDate();
                }
            }).start();
        } catch (Exception e) {
        }
    }
    
     private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/Icon-20.png")));
    }
    
   public void removeDesktopComponents(){
       int count = this.desktopReal.getComponentCount();
       
       
       for (int i = 0; i < count; i++) {
           desktopReal.remove(desktopReal.getComponent(i));
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

        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        btnUsers = new rojerusan.RSButtonIconI();
        btnHome = new rojerusan.RSButtonIconI();
        btnLogout = new rojerusan.RSButtonIconI();
        btnProduct = new rojerusan.RSButtonIconI();
        btnReport = new rojerusan.RSButtonIconI();
        btnCustomer = new rojerusan.RSButtonIconI();
        btnReward = new rojerusan.RSButtonIconI();
        btnHistory = new rojerusan.RSButtonIconI();
        lbUserType = new javax.swing.JLabel();
        jLabelAdmin = new javax.swing.JLabel();
        lbLastname = new javax.swing.JLabel();
        desktopPanel = new javax.swing.JPanel();
        desktopReal = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jLabelTime = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 747));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUsers.setBackground(new java.awt.Color(0, 102, 102));
        btnUsers.setForeground(new java.awt.Color(51, 51, 51));
        btnUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/management.png"))); // NOI18N
        btnUsers.setText("Users");
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });
        jPanel1.add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 200, 50));

        btnHome.setBackground(new java.awt.Color(0, 102, 102));
        btnHome.setForeground(new java.awt.Color(51, 51, 51));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/house.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 200, 50));

        btnLogout.setBackground(new java.awt.Color(0, 102, 102));
        btnLogout.setForeground(new java.awt.Color(51, 51, 51));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 100, 20));

        btnProduct.setBackground(new java.awt.Color(0, 102, 102));
        btnProduct.setForeground(new java.awt.Color(51, 51, 51));
        btnProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/product.png"))); // NOI18N
        btnProduct.setText("Inventory");
        btnProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductActionPerformed(evt);
            }
        });
        jPanel1.add(btnProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 200, 50));

        btnReport.setBackground(new java.awt.Color(0, 102, 102));
        btnReport.setForeground(new java.awt.Color(51, 51, 51));
        btnReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/report.png"))); // NOI18N
        btnReport.setText("Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });
        jPanel1.add(btnReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 50));

        btnCustomer.setBackground(new java.awt.Color(0, 102, 102));
        btnCustomer.setForeground(new java.awt.Color(51, 51, 51));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/customer.png"))); // NOI18N
        btnCustomer.setText("Customer");
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(btnCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 200, 50));

        btnReward.setBackground(new java.awt.Color(0, 102, 102));
        btnReward.setForeground(new java.awt.Color(51, 51, 51));
        btnReward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reward.png"))); // NOI18N
        btnReward.setText("Reward");
        btnReward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRewardActionPerformed(evt);
            }
        });
        jPanel1.add(btnReward, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 200, 50));

        btnHistory.setBackground(new java.awt.Color(0, 102, 102));
        btnHistory.setForeground(new java.awt.Color(51, 51, 51));
        btnHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/history.png"))); // NOI18N
        btnHistory.setText("History");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });
        jPanel1.add(btnHistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 200, 50));

        lbUserType.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        lbUserType.setForeground(new java.awt.Color(255, 255, 255));
        lbUserType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUserType.setText("Admin");
        jPanel1.add(lbUserType, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 190, 31));

        jLabelAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/admin_girl_100x.png"))); // NOI18N
        jPanel1.add(jLabelAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, 90));

        lbLastname.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbLastname.setForeground(new java.awt.Color(255, 255, 255));
        lbLastname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLastname.setText("Name: Malabiga");
        lbLastname.setName(""); // NOI18N
        jPanel1.add(lbLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 190, 31));

        desktopPanel.setBackground(new java.awt.Color(185, 221, 217));
        desktopPanel.setPreferredSize(new java.awt.Dimension(1166, 694));

        desktopReal.setBackground(new java.awt.Color(0, 153, 153));
        desktopReal.setPreferredSize(new java.awt.Dimension(1166, 694));

        javax.swing.GroupLayout desktopRealLayout = new javax.swing.GroupLayout(desktopReal);
        desktopReal.setLayout(desktopRealLayout);
        desktopRealLayout.setHorizontalGroup(
            desktopRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        desktopRealLayout.setVerticalGroup(
            desktopRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout desktopPanelLayout = new javax.swing.GroupLayout(desktopPanel);
        desktopPanel.setLayout(desktopPanelLayout);
        desktopPanelLayout.setHorizontalGroup(
            desktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopReal, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
        );
        desktopPanelLayout.setVerticalGroup(
            desktopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopReal, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(1166, 53));

        jLabelTime.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabelTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTime.setText("Time");

        jLabelDate.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabelDate.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDate.setText("Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(153, 204, 255));

        jMenu2.setText("File");

        jMenuItem3.setText("Change Password");
        jMenu2.add(jMenuItem3);

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
                    .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        showUsersInternalFrame();
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        showHomeInternalFrame();
    
    }//GEN-LAST:event_btnHomeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
         System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        dispose();
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
         
    }//GEN-LAST:event_formWindowActivated

    private void btnProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductActionPerformed
        // TODO add your handling code here:
        showProductInternalFrame();
    }//GEN-LAST:event_btnProductActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void btnRewardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRewardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRewardActionPerformed

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistoryActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonIconI btnCustomer;
    private rojerusan.RSButtonIconI btnHistory;
    private rojerusan.RSButtonIconI btnHome;
    private rojerusan.RSButtonIconI btnLogout;
    private rojerusan.RSButtonIconI btnProduct;
    private rojerusan.RSButtonIconI btnReport;
    private rojerusan.RSButtonIconI btnReward;
    private rojerusan.RSButtonIconI btnUsers;
    private javax.swing.JPanel desktopPanel;
    private javax.swing.JDesktopPane desktopReal;
    private javax.swing.JLabel jLabelAdmin;
    public javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelTime;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JLabel lbLastname;
    public javax.swing.JLabel lbUserType;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import com.dao.ProductDAO;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author shaitozen
 */
public class POSDiaglog extends javax.swing.JFrame {

    
    private ProductDAO productDAO = new ProductDAO();
    private CashierFrame cashierFrame = new CashierFrame();
    
    public String cashierUsername;
    public String cashierName;

    public String getCashierUsername() {
        return cashierUsername;
    }

    public void setCashierUsername(String cashierUsername) {
        this.cashierUsername = cashierUsername;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }
    
    
    /**
     * Creates new form POSDiaglog
     * @param parent
     * @param modal
     */
    public POSDiaglog(java.awt.Frame parent, boolean modal) {
       // super(parent, modal);
        initComponents();
        
        tblPOS.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblPOS.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblPOS.getColumnModel().getColumn(2).setPreferredWidth(25);
//        tblPOS.getColumnModel().getColumn(3).setPreferredWidth(100);
//        tblPOS.getColumnModel().getColumn(4).setPreferredWidth(100);
        setButtonForPOS();
        setIconImage();
    }
    
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/Icon-20.png")));
    }
    
        
 
        
    public void numberOnly(char c, TextField TextField){
        if(Character.isLetter(c)) {
             TextField.setEditable(false);
         } else {
             TextField.setEditable(true);
         }
    }    
        
    public void transactionInfo(){
        
        try {
            double totalAmount = 0;
            double vat = 0;
            double subTotal = 0;
            int totalPoints = 0;
                for (int i = 0; i < tblPOS.getRowCount(); i++) {
                    double amount = Double.parseDouble(tblPOS.getValueAt(i, 4).toString());
                     totalAmount += amount;
                    int points = Integer.parseInt(tblPOS.getValueAt(i, 5).toString());
                    totalPoints += points;
                }
            
            vat = totalAmount * .12;
            subTotal = totalAmount * .88;
            txtTotalAmount.setText(String.format("%.2f",totalAmount));
            txtVAT.setText(String.format("%.2f",vat));
            txtSubTotal.setText(String.format("%.2f",subTotal));
            txtPoints.setText(String.valueOf(totalPoints));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
         
    }
    
    public void clearTxtAndButton(){
        txtQty.setText("1");   
        txtBarcode.setText(" ");
        txtBarcode.setText("");
        tblPOS.clearSelection();
        setButtonForPOS();
        txtBarcode.requestFocus();
    }
    
    public void clearTransaction(){
        txtSubTotal.setText("0.00");
        txtVAT.setText("0.00");
        txtTotalAmount.setText("0.00");
        txtPoints.setText("0");
    }
    
    public void clearCustomerInfo(){
        txtCustomerCard.setText("None");
        txtCustomerName.setText("None");
    }
    
    public List<Object> getPOSData(){
        int row = tblPOS.getSelectedRow();
        TableModel model = tblPOS.getModel();
        
        List<Object> columnValues = new ArrayList<>();
        columnValues.add(model.getValueAt(row, 0).toString());
        columnValues.add(model.getValueAt(row, 1).toString());
        columnValues.add(model.getValueAt(row, 2).toString());
        columnValues.add(model.getValueAt(row, 3).toString());
        columnValues.add(productDAO.getStocks(model.getValueAt(row, 0).toString()));
        return columnValues;
    }
    
    public void updatePOSTable(String barcode){
        
        if(productDAO.checkBarcodeExisted(barcode)){
            List<Object> getProductInfo = productDAO.getBarcodeProductInfo(barcode);
            boolean exists = false;
            String barcodeExisted = "";
            String qtyExisted = "";
            int pointsExisted = Integer.parseInt(getProductInfo.get(3).toString());
            int qtyTotal = 0;
            int pointsTotal = 0;
            
            
            for (int i = 0; i < tblPOS.getRowCount(); i++) {
                 barcodeExisted = tblPOS.getValueAt(i, 0).toString();
                 qtyExisted = tblPOS.getValueAt(i, 2).toString();
              

                 if(txtBarcode.getText().equals(barcodeExisted)){
                    qtyTotal = Integer.parseInt(qtyExisted) 
                         + Integer.parseInt(txtQty.getText());
                    Double amount = qtyTotal
                    * Double.parseDouble(getProductInfo.get(2).toString()); 
                    pointsTotal = pointsExisted
                         * qtyTotal;
                    
                     
                    if(productDAO.getStocks(barcode) < qtyTotal){
                        showMessageError("Insufficient Stocks");
                        exists = true;
                        clearTxtAndButton();
                        break;
                    } else {
                        DefaultTableModel model = (DefaultTableModel) tblPOS.getModel();
                        model.setValueAt(qtyTotal, i, 2);
                        model.setValueAt(amount, i, 4);
                        model.setValueAt(pointsTotal, i, 5);
                        clearTxtAndButton();
                        exists = true;
                        break;
                    }               
                 }
            } 

            if (!exists) {
                if(productDAO.getStocks(barcode) < Integer.parseInt(txtQty.getText())){
                    showMessageError("Insufficient Stocks");
                    clearTxtAndButton();
                } else {
                    Double amount = Double.parseDouble(txtQty.getText()) 
                    * Double.parseDouble(getProductInfo.get(2).toString());

                DefaultTableModel model = (DefaultTableModel) tblPOS.getModel();
                model.addRow(new Object[]{barcode, 
                    getProductInfo.get(1), 
                    txtQty.getText(),
                    getProductInfo.get(2),
                    amount,
                    pointsExisted});

                clearTxtAndButton();
                } 
            }                     
        } else {
            showMessageError("Invalid Barcode");
            clearTxtAndButton();
        }
    }
    
    public void clearButtonForPOS(){
        btnVoid.setEnabled(false);
        btnPayment.setEnabled(false);
        btnQty.setEnabled(false);
        btnVoid.setEnabled(false);
        btnTransaction.setEnabled(false);
    }
    
    public void setButtonForPOS() {
        try {
            int row = tblPOS.getSelectedRow();
            
            if (row == -1){
                btnQty.setEnabled(false);
                btnVoid.setEnabled(false);
            }else{
                btnQty.setEnabled(true);
                btnVoid.setEnabled(true);    
            }
            
            if(tblPOS.getRowCount() == 0){
                btnPayment.setEnabled(false);
                btnTransaction.setEnabled(false);
            }else {
                btnPayment.setEnabled(true);
                btnTransaction.setEnabled(true);
            }
        
        } catch (NumberFormatException e) {
        }
    }
    
    public void showMessageError(String errorString){
        JOptionPane.showMessageDialog(null, 
                errorString, 
                "Error!", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        txtCustomerCard = new java.awt.Label();
        txtSubTotal = new java.awt.Label();
        txtVAT = new java.awt.Label();
        txtTotalAmount = new java.awt.Label();
        label9 = new java.awt.Label();
        txtCustomerName = new java.awt.Label();
        label10 = new java.awt.Label();
        txtPoints = new java.awt.Label();
        btnPayment = new java.awt.Button();
        btnCustomerCard = new java.awt.Button();
        btnQty = new java.awt.Button();
        btnVoid = new java.awt.Button();
        lbName = new java.awt.Label();
        txtQty = new java.awt.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPOS = new javax.swing.JTable();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        btnTransaction = new java.awt.Button();
        btnCancel = new java.awt.Button();
        txtBarcode = new java.awt.TextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("POS Frame");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        label1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 0, 102));
        label1.setText("Barcode :");

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaction Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 1, 12), new java.awt.Color(0, 0, 102))); // NOI18N

        label2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 102));
        label2.setText("VAT :");

        label3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label3.setForeground(new java.awt.Color(0, 0, 102));
        label3.setText("Sub Total :");

        label4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label4.setForeground(new java.awt.Color(0, 0, 102));
        label4.setText("Total Amount :");

        label5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label5.setForeground(new java.awt.Color(0, 0, 102));
        label5.setText("Customer Card :");

        txtCustomerCard.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtCustomerCard.setForeground(new java.awt.Color(0, 0, 102));
        txtCustomerCard.setText("None");

        txtSubTotal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtSubTotal.setForeground(new java.awt.Color(0, 0, 102));
        txtSubTotal.setText("0.00");

        txtVAT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtVAT.setForeground(new java.awt.Color(0, 0, 102));
        txtVAT.setText("0.00");

        txtTotalAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtTotalAmount.setForeground(new java.awt.Color(0, 0, 102));
        txtTotalAmount.setText("0.00");

        label9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label9.setForeground(new java.awt.Color(0, 0, 102));
        label9.setText("Customer Name :");

        txtCustomerName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtCustomerName.setForeground(new java.awt.Color(0, 0, 102));
        txtCustomerName.setText("None");

        label10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label10.setForeground(new java.awt.Color(0, 0, 102));
        label10.setText("Points :");

        txtPoints.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtPoints.setForeground(new java.awt.Color(0, 0, 102));
        txtPoints.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPoints, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCustomerCard, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addComponent(txtCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtVAT, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPayment.setBackground(new java.awt.Color(255, 204, 255));
        btnPayment.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnPayment.setLabel("Payment [F5]");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        btnCustomerCard.setBackground(new java.awt.Color(255, 204, 255));
        btnCustomerCard.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnCustomerCard.setLabel("Customer Card [F6]");
        btnCustomerCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerCardActionPerformed(evt);
            }
        });

        btnQty.setBackground(new java.awt.Color(255, 204, 255));
        btnQty.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnQty.setLabel("Add Qty [F9]");
        btnQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQtyActionPerformed(evt);
            }
        });

        btnVoid.setBackground(new java.awt.Color(255, 204, 255));
        btnVoid.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnVoid.setLabel("Void [F10]");
        btnVoid.setName(""); // NOI18N
        btnVoid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoidActionPerformed(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lbName.setForeground(new java.awt.Color(0, 0, 102));
        lbName.setText("cashierName");

        txtQty.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtQty.setText("1");
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
        });

        tblPOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Product Name", "Qty", "Price", "Amount", "Points"
            }
        ));
        tblPOS.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPOSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPOS);

        label7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label7.setForeground(new java.awt.Color(0, 0, 102));
        label7.setText("Cashier Name :");

        label8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        label8.setForeground(new java.awt.Color(0, 0, 102));
        label8.setText("Qty :");

        btnTransaction.setBackground(new java.awt.Color(255, 204, 255));
        btnTransaction.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTransaction.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        btnTransaction.setLabel("New Transaction [Esc]");
        btnTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransactionActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 204, 255));
        btnCancel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnCancel.setLabel("Cancel [F12]");
        btnCancel.setName(""); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtBarcode.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCustomerCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnQty, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(btnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVoid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCustomerCard, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnQty, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(btnVoid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoidActionPerformed
        int input = JOptionPane.showConfirmDialog(null, 
                        "Are you sure do you want void this product", 
                        "Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                        if(input == 0) {
                            DefaultTableModel model = (DefaultTableModel) tblPOS.getModel();
                            model.removeRow(tblPOS.getSelectedRow());
                            clearTxtAndButton(); 
                            transactionInfo();
                        }
        
    }//GEN-LAST:event_btnVoidActionPerformed

    private void tblPOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPOSMouseClicked
        setButtonForPOS();
    }//GEN-LAST:event_tblPOSMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearTxtAndButton();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransactionActionPerformed
        int input = JOptionPane.showConfirmDialog(null, 
                        "Are you sure do you want discard your transaction?", 
                        "Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                        if(input == 0) {
                            DefaultTableModel model = (DefaultTableModel) tblPOS.getModel();
                            model.setRowCount(0);
                            clearTxtAndButton();
                            clearTransaction();
                            txtCustomerCard.setText("None");
                            txtCustomerName.setText("None");       
                        }
    }//GEN-LAST:event_btnTransactionActionPerformed

    private void txtBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            updatePOSTable(txtBarcode.getText().trim());
            transactionInfo();
        }
    }//GEN-LAST:event_txtBarcodeKeyPressed

    private void btnCustomerCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerCardActionPerformed
        CustomerCardDialog customerCardDialog = 
                new CustomerCardDialog(cashierFrame, this, true);
        customerCardDialog.setVisible(true);
    }//GEN-LAST:event_btnCustomerCardActionPerformed

    private void btnQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQtyActionPerformed
        AddQuantityPOSDialog addQuantityPOSDialog = new
            AddQuantityPOSDialog(cashierFrame, this, true, getPOSData());
        addQuantityPOSDialog.setVisible(true);
    }//GEN-LAST:event_btnQtyActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        PaymentDialog paymentDialog = 
                new PaymentDialog(cashierFrame, this, true, txtTotalAmount.getText());
        paymentDialog.setUsername(getCashierUsername());
        paymentDialog.setRfidNumber(txtCustomerCard.getText());
        paymentDialog.setPoints(txtPoints.getText());
        paymentDialog.setVisible(true);
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
  numberOnly(evt.getKeyChar(), txtQty);      
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
            java.util.logging.Logger.getLogger(POSDiaglog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POSDiaglog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POSDiaglog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POSDiaglog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                POSDiaglog dialog = new POSDiaglog(new javax.swing.JFrame(), true);
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
    private java.awt.Button btnCancel;
    private java.awt.Button btnCustomerCard;
    private java.awt.Button btnPayment;
    private java.awt.Button btnQty;
    private java.awt.Button btnTransaction;
    private java.awt.Button btnVoid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    public java.awt.Label lbName;
    public javax.swing.JTable tblPOS;
    private java.awt.TextField txtBarcode;
    public java.awt.Label txtCustomerCard;
    public java.awt.Label txtCustomerName;
    public java.awt.Label txtPoints;
    private java.awt.TextField txtQty;
    private java.awt.Label txtSubTotal;
    private java.awt.Label txtTotalAmount;
    private java.awt.Label txtVAT;
    // End of variables declaration//GEN-END:variables
}

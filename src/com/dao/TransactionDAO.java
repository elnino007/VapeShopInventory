/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author shaitozen
 */
public class TransactionDAO {
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public int autoNumber(){
          conn = new DatabaseConnection().getConnection();
          sql = "SELECT MAX(id) as number FROM transaction_tbl";
          int autoNumber = 0;
          try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                autoNumber = rs.getInt("number");
            }
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return autoNumber + 1;
    }
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO transaction_tbl VALUES(?,?,?,?,?,?,?,?)";   
        boolean success = false;
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");  
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(columnValues.get(0).toString()));
            pst.setString(2, columnValues.get(1).toString());
            pst.setInt(3, Integer.parseInt(columnValues.get(2).toString()));
            pst.setDouble(4, Double.parseDouble(columnValues.get(3).toString()));
            pst.setDouble(5, Double.parseDouble(columnValues.get(4).toString()));
            pst.setString(6, columnValues.get(5).toString());
            pst.setInt(7, Integer.parseInt(columnValues.get(6).toString()));
            pst.setDate(8, sqlDate);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Create in User failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
}

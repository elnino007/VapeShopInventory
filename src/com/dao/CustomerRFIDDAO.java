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
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author shaitozen
 */
public class CustomerRFIDDAO {
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO customer_rfid VALUES(?,?)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(columnValues.get(0).toString()));
            pst.setString(2, "none");
            
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
    
    public boolean updateRFID(int id, String rfidNumber){
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE customer_rfid SET rfid_number=? WHERE id=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rfidNumber);
            pst.setInt(2, id);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Delete in Product failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public boolean deleteRFID(int id){
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE customer_rfid SET rfid_number=? WHERE id=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, "none");
            pst.setInt(2, id);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Delete in failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public boolean checkRFIDExisted(String rfid){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM customer_rfid WHERE rfid_number=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rfid);
            
            rs = pst.executeQuery();
            
            if(rs.next() == true){
                success = true;
            }else{
                success = false;
            }

            conn.close();        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public boolean delete(int id){
        conn = new DatabaseConnection().getConnection();
        
        sql = "DELETE FROM customer_rfid WHERE id = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
               // throw new SQLException("Delete in Product failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public ResultSet readAllSortByID(){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT cr.id, "
                + "concat(c.last_name,',', c.first_name,' ', c.middle_name) AS fullname, "
                + "cr.rfid_number "
                + "FROM customer AS c "
                + "INNER JOIN customer_rfid AS cr "
                + "ON c.id = cr.id "
                + "ORDER BY c.id";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    }
}

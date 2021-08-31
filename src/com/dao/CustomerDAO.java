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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author shaitozen
 */
public class CustomerDAO {
    
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public List<Object> getCustomerInfo(int id){
        List<Object> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT * FROM customer WHERE id=?";
         
         try {
             pst = conn.prepareStatement(sql);
             pst.setInt(1, id);      
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("first_name"));
                getUsersInfo.add(rs.getString("middle_name"));
                getUsersInfo.add(rs.getString("last_name"));
                getUsersInfo.add(rs.getString("age"));
                getUsersInfo.add(rs.getBoolean("gender"));
                getUsersInfo.add(rs.getString("address"));      
                getUsersInfo.add(rs.getString("contact_number")); 
            }    
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
    public List<Object> getCustomerCardPointsInfo(String card){
        List<Object> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT c.first_name, c.middle_name, c.last_name, cp.points "
                 + "FROM customer AS c "
                 + "INNER JOIN customer_rfid AS cr "
                 + "ON c.id = cr.id "
                 + "INNER JOIN customer_points AS cp "
                 + "ON c.id = cp.id "
                 + "WHERE cr.rfid_number = ?";
         
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, card);      
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("first_name"));
                getUsersInfo.add(rs.getString("middle_name"));
                getUsersInfo.add(rs.getString("last_name"));
                getUsersInfo.add(rs.getString("points"));
                
            }    
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
    public List<Object> getCustomerCardInfo(String card){
        List<Object> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT c.last_name, "
                 + "cr.rfid_number FROM customer AS c "
                 + "INNER JOIN customer_rfid AS cr "
                 + "ON c.id = cr.id "
                 + "WHERE cr.rfid_number = ?";
         
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, card);      
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("last_name"));
                getUsersInfo.add(rs.getString("rfid_number"));
                
            }    
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, columnValues.get(0).toString());
            pst.setString(2, columnValues.get(1).toString());
            pst.setString(3, columnValues.get(2).toString());
            pst.setString(4, columnValues.get(3).toString());
            pst.setString(5, columnValues.get(4).toString());
            pst.setBoolean(6, Boolean.parseBoolean(columnValues.get(5).toString()));
            pst.setString(7, columnValues.get(6).toString());
            pst.setString(8, columnValues.get(7).toString());   
            
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
    
    public boolean update(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE customer "
                + "SET first_name=?, "
                + "middle_name=?, "
                + "last_name=?, "
                + "age=?, "
                + "gender=?, "
                + "address=?, "
                + "contact_number=? "
                + "WHERE id=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, columnValues.get(1).toString());
            pst.setString(2, columnValues.get(2).toString());
            pst.setString(3, columnValues.get(3).toString());
            pst.setInt(4, Integer.parseInt(columnValues.get(4).toString()));
            pst.setBoolean(5, Boolean.parseBoolean(columnValues.get(5).toString()));
            pst.setString(6, columnValues.get(6).toString());
            pst.setString(7, columnValues.get(7).toString());
            pst.setInt(8, Integer.parseInt(columnValues.get(0).toString()));
 
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Create in Product failed, no rows affected.");
            }
            conn.close();                
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return success;
    }
    
    public boolean delete(int id){
        conn = new DatabaseConnection().getConnection();
        
        sql = "DELETE FROM customer WHERE id = ?";   
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
    
    public int autoNumber(){
          conn = new DatabaseConnection().getConnection();
          sql = "SELECT MAX(id) as number FROM customer";
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
    
    public ResultSet readAllSortByID(){
        conn = new DatabaseConnection().getConnection();
        
         sql = "SELECT "
                + "id, "
                + "concat(last_name,',', first_name,' ', middle_name) AS fullname, "
                + "age, "
                + "gender, "
                + "contact_number "
                + "FROM customer "
                + "ORDER BY id";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    } 
}

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
 * @author shaitozen007
 */
public class ProductCategoryDAO {
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public boolean checkNameExisted(String name){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM product_category WHERE name=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            
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
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO product_category VALUES(?,?)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(columnValues.get(0).toString()));
            pst.setString(2, columnValues.get(1).toString());
       
            
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
        
        sql = "UPDATE product_category "
                + "SET name=? "
                + "WHERE id=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);  
            pst.setString(1, columnValues.get(1).toString());
            pst.setInt(2, Integer.parseInt(columnValues.get(0).toString()));
 
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Update in User failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        return success;
    }
     
    public boolean delete(int id){
        conn = new DatabaseConnection().getConnection();
        
        sql = "DELETE FROM product_category WHERE id = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Delete in Product Category failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public int autoNumber(){
          conn = new DatabaseConnection().getConnection();
          sql = "SELECT MAX(id) as number FROM product_category";
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
        
        sql = "SELECT * FROM product_category ORDER BY id";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    }
    
    public ResultSet readAllSortByName(){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM product_category ORDER BY name";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    }
}

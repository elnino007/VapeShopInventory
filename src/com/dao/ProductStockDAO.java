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
public class ProductStockDAO {
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO product_stock VALUES(?,0)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(columnValues.get(0).toString()));
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Create in Stocks failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
        
        return success;
    }
    
    public int getQuantityStock(int id){
        
        conn = new DatabaseConnection().getConnection();
        sql = "SELECT quantity FROM product_stock WHERE id = ?";
        int quantityStock = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            while(rs.next()){
                quantityStock = rs.getInt("quantity");
            }
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return quantityStock;
    }
    
    public boolean add(List<Object> columnValues){
        int quantityTotal = getQuantityStock(Integer.parseInt(columnValues.get(0).toString())) +
        Integer.parseInt(columnValues.get(1).toString());
        
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE product_stock SET quantity = ? WHERE id =?";   
        boolean success = false;

       
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, quantityTotal);
            pst.setInt(2, Integer.parseInt(columnValues.get(0).toString()));
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Add in Stocks failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return success;
    }
    
    public boolean update(List<Object> columnValues){
//        int quantityTotal = getQuantityStock(Integer.parseInt(columnValues.get(0).toString())) +
//        Integer.parseInt(columnValues.get(1).toString());
        
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE product_stock SET quantity = ? WHERE id =?";   
        boolean success = false;

       
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(columnValues.get(1).toString()));
            pst.setInt(2, Integer.parseInt(columnValues.get(0).toString()));
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Add in Stocks failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return success;
    }
    
    public boolean delete(int id){
        conn = new DatabaseConnection().getConnection();
        
        sql = "DELETE FROM product_stock WHERE id = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
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
    
    public boolean makeZeroTheStocks(int id){
//        int quantityTotal = getQuantityStock(Integer.parseInt(columnValues.get(0).toString())) +
//        Integer.parseInt(columnValues.get(1).toString());
        
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE product_stock SET quantity = 0 WHERE id =?";   
        boolean success = false;

       
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Delete in Stocks failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return success;
    }
    
    public ResultSet readAllSortByID(){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT ps.id, "
                + "p.product_category_name, "
                + "p.product_name, "
                + "ps.quantity "
                + "FROM product AS p "
                + "INNER JOIN product_stock AS ps "
                + "ON p.id = ps.id "
                + "ORDER BY ps.id";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    } 
    
    
}

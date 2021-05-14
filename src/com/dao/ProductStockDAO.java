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

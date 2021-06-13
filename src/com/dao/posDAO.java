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
import javax.swing.JOptionPane;

/**
 *
 * @author shaitozen
 */
public class posDAO {
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public ResultSet posTable(){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT id,"
                + "barcode,"
                + "product_name, "
                + "product_category_name,"
                + "purchase_price, "
                + "selling_price, "
                + "points "
                + "FROM product ORDER BY id";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
        
        
    }
}

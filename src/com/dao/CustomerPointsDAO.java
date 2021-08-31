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
public class CustomerPointsDAO {
    
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public int getIDByRFID(String rfidNumber){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT cp.id FROM customer_rfid AS cr "
                + "INNER JOIN customer_points AS cp "
                + "ON cr.id = cp.id "
                + "WHERE cr.rfid_number = ?";   
        
        int points = 0;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rfidNumber);
            
             rs = pst.executeQuery();
            
               while(rs.next()){
                points = rs.getInt("id");
            }    
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return points;
    }
    
    public int getPointsByRFID(String rfidNumber){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT cp.points FROM customer_rfid AS cr "
                + "INNER JOIN customer_points AS cp "
                + "ON cr.id = cp.id "
                + "WHERE cr.rfid_number = ?";   
        
        int points = 0;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, rfidNumber);
            
             rs = pst.executeQuery();
            
               while(rs.next()){
                points = rs.getInt("points");
            }    
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return points;
    }
    
    public boolean updatePoints(String rfidNumber, int points){
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE customer_rfid AS cr "
                + "INNER JOIN customer_points AS cp "
                + "ON cr.id = cp.id S"
                + "ET cp.points = ? "
                + "WHERE cr.rfid_number = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, points);
            pst.setString(2, rfidNumber);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
            //    throw new SQLException("Create in Points failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO customer_points VALUES(?,?)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(columnValues.get(0).toString()));
            pst.setInt(2, 0);
            
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
              //  throw new SQLException("Delete in Product failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public ResultSet readAllSortByID(){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT c.id, cp.points FROM "
                + "customer AS c "
                + "INNER JOIN customer_points AS cp "
                + "ON c.id = cp.id "
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

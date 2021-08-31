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
public class RewardDAO {
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
     final private CustomerPointsDAO customerPointsDAO = new CustomerPointsDAO();
    
    public boolean updateCustomerPoints(String rfid, int points){
        
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE customer_points SET points = ? WHERE id =?";   
        boolean success = false;

       
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, points);
            pst.setInt(2, customerPointsDAO.getIDByRFID(rfid));
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("updated in Stocks failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return success;
    }
    
    public boolean updateReward(String name, int stocks){
        
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE rewards SET stocks = ? WHERE name =?";   
        boolean success = false;

       
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, stocks);
            pst.setString(2, name);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("updated in Stocks failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return success;
    }
    
    public boolean checkedRewardName(String name){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM rewards WHERE name=?";   
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
    
    public List<Object> getRewardInfo(int id){
        List<Object> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT * FROM rewards WHERE id=?";
         
         try {
             pst = conn.prepareStatement(sql);
             pst.setInt(1, id);      
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("name"));
                getUsersInfo.add(rs.getString("points"));
                getUsersInfo.add(rs.getString("stocks"));
            }    
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
     public int autoNumber(){
          conn = new DatabaseConnection().getConnection();
          sql = "SELECT MAX(id) as number FROM rewards";
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
        
        sql = "SELECT * FROM rewards ORDER BY id";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    } 
    
    public boolean create(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "INSERT INTO rewards VALUES(?,?,?,?)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, columnValues.get(0).toString());
            pst.setString(2, columnValues.get(1).toString());
            pst.setString(3, columnValues.get(2).toString());
            pst.setString(4, columnValues.get(3).toString());
//            pst.setInt(1, Integer.parseInt(columnValues.get(0).toString()));
//            pst.setString(2, columnValues.get(1).toString());
//            pst.setInt(3, Integer.parseInt(columnValues.get(2).toString()));
//            pst.setInt(4, Integer.parseInt(columnValues.get(3).toString()));
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Create in reward failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    public boolean update(List<Object> columnValues){
        conn = new DatabaseConnection().getConnection();
        
        sql = "UPDATE rewards SET name=?,points=?,stocks=? WHERE id=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(4, Integer.parseInt(columnValues.get(0).toString()));
            pst.setString(1, columnValues.get(1).toString());
            pst.setInt(2, Integer.parseInt(columnValues.get(2).toString()));
            pst.setInt(3, Integer.parseInt(columnValues.get(3).toString()));
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Create in reward failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
        
    
    public boolean delete(int id){
        conn = new DatabaseConnection().getConnection();
        
        sql = "DELETE FROM rewards WHERE id = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Delete in reward failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }
    
    
    
    
    
}


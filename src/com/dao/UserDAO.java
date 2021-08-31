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
 * @author shaitozen007
 */
public class UserDAO {
    
    public String sql;
    public Connection conn = null;
    public PreparedStatement pst = null;
    public ResultSet rs = null;
    
    public List<String> getProductName(){
         List<String> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT product_name FROM product ORDER BY product_name";
         
         try {
             pst = conn.prepareStatement(sql);
  
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("product_name"));                    
            }    
            
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
    public List<String> getCashierUsername(){
         List<String> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT username FROM user_management WHERE user_type='Cashier' ORDER BY username";
         
         try {
             pst = conn.prepareStatement(sql);
  
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("username"));                    
            }    
            
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
    public boolean checkUsernameExist(String username){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM user_management WHERE username=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
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
    
    public boolean checkUsernameExist(String username,String usernameNow){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM user_management WHERE username=? AND username<>?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, usernameNow);
            
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
    
    public List<Object> getUsersInfo(String username){
        List<Object> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT * FROM user_management WHERE username=?";
         
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, username);      
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("password"));
                getUsersInfo.add(rs.getString("user_type"));
                getUsersInfo.add(rs.getString("first_name"));
                getUsersInfo.add(rs.getString("middle_name"));
                getUsersInfo.add(rs.getString("last_name"));
                getUsersInfo.add(rs.getString("birthday"));
                getUsersInfo.add(rs.getInt("age"));
                getUsersInfo.add(rs.getString("address"));
                getUsersInfo.add(rs.getString("contact"));
                getUsersInfo.add(rs.getBoolean("gender"));                        
            }    
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return getUsersInfo;
    }
    
    public int autoNumber(){
          conn = new DatabaseConnection().getConnection();
          sql = "SELECT MAX(id) as number FROM user_management";
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
        
        sql = "INSERT INTO user_management VALUES(?,?,?,?,?,?,?,?,?,?,?)";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, columnValues.get(0).toString());
            pst.setString(2, columnValues.get(1).toString());
            pst.setString(3, columnValues.get(2).toString());
            pst.setString(4, columnValues.get(3).toString());
            pst.setString(5, columnValues.get(4).toString());
            pst.setString(6, columnValues.get(5).toString());
            pst.setString(7, columnValues.get(6).toString());
            pst.setString(8, columnValues.get(7).toString());
            pst.setString(9, columnValues.get(8).toString());
            pst.setString(10, columnValues.get(9).toString());
            pst.setBoolean(11, Boolean.parseBoolean(columnValues.get(10).toString()));
            
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
        
        sql = "UPDATE user_management "
                + "SET username=?, "
                + "password=?, "
                + "user_type=?, "
                + "first_name=?, "
                + "middle_name=?, "
                + "last_name=?, "
                + "birthday=?, "
                + "age=?, "
                + "address=?, "
                + "contact=?, "
                + "gender=? "
                + "WHERE username=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);  
            pst.setString(1, columnValues.get(0).toString());
            pst.setString(2, columnValues.get(1).toString());
            pst.setString(3, columnValues.get(2).toString());
            pst.setString(4, columnValues.get(3).toString());
            pst.setString(5, columnValues.get(4).toString());
            pst.setString(6, columnValues.get(5).toString());
            pst.setString(7, columnValues.get(6).toString());
            pst.setString(8, columnValues.get(7).toString());
            pst.setString(9, columnValues.get(8).toString());
            pst.setString(10, columnValues.get(9).toString());
            pst.setBoolean(11, Boolean.parseBoolean(columnValues.get(10).toString()));
            pst.setString(12, columnValues.get(11).toString());
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
    
    public boolean delete(String username){
        conn = new DatabaseConnection().getConnection();
        
        sql = "DELETE FROM user_management WHERE username = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            
            int rows = pst.executeUpdate();
            
            if(rows == 1){
                success = true;
            }else{
                throw new SQLException("Delete in User failed, no rows affected.");
            }
            
            conn.close();        
                    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return success;
    }

    public ResultSet readAllSortBy(){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT "
                + "username, "
                + "concat(last_name,',', first_name,' ', middle_name) AS fullname, "
                + "age, "
                + "gender, "
                + "user_type "
                + "FROM user_management "
                + "ORDER BY username";
        
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    }
    
    public ResultSet readAllSortBy(String column, String columnValue){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT "
                + "username, "
                + "concat(last_name,',', first_name,' ', middle_name) AS fullname, "
                + "age, "
                + "gender, "
                + "user_type "
                + "FROM user_management "
                + "WHERE "+column+" LIKE '%"+columnValue+"%' "
                + "ORDER BY username";
        
        try {
            pst = conn.prepareStatement(sql); 
            rs = pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
          return rs;
    }
    
   
}


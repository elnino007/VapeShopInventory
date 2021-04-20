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
    
    public boolean checkUsernameExist(int id, String username){
        conn = new DatabaseConnection().getConnection();
        
        sql = "SELECT * FROM user_management WHERE username=? AND id<>?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setInt(2, id);
            
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
    
    public List<Object> getUsersInfo(int id){
        List<Object> getUsersInfo =  new ArrayList<>();
        
         conn = new DatabaseConnection().getConnection();
         sql = "SELECT * FROM user_management WHERE id=?";
         
         try {
             pst = conn.prepareStatement(sql);
             pst.setInt(1, id);      
             rs = pst.executeQuery();
            
            while(rs.next()){
                getUsersInfo.add(rs.getString("first_name"));
                getUsersInfo.add(rs.getString("middle_name"));
                getUsersInfo.add(rs.getString("last_name"));
                getUsersInfo.add(rs.getInt("age"));
                getUsersInfo.add(rs.getString("username"));
                getUsersInfo.add(rs.getString("password"));
                getUsersInfo.add(rs.getString("user_type"));
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
        
        sql = "INSERT INTO user_management VALUES(?,?,?,?,?,?,?,?,?)";   
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
            pst.setBoolean(9, Boolean.parseBoolean(columnValues.get(8).toString()));
            
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
                + "SET first_name=?, "
                + "middle_name=?, "
                + "last_name=?, "
                + "age=?, "
                + "username=?, "
                + "password=?, "
                + "user_type=?, "
                + "gender=? "
                + "WHERE id=?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, columnValues.get(1).toString());
            pst.setString(2, columnValues.get(2).toString());
            pst.setString(3, columnValues.get(3).toString());
            pst.setString(4, columnValues.get(4).toString());
            pst.setString(5, columnValues.get(5).toString());
            pst.setString(6, columnValues.get(6).toString());
            pst.setString(7, columnValues.get(7).toString());
            pst.setBoolean(8, Boolean.parseBoolean(columnValues.get(8).toString()));
            pst.setInt(9, Integer.parseInt(columnValues.get(0).toString()));
            
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
        
        sql = "DELETE FROM user_management WHERE id = ?";   
        boolean success = false;
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            
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
                + "id, "
                + "concat(last_name,',', first_name,' ', middle_name) AS fullname, "
                + "age, "
                + "gender, "
                + "username, "
                + "user_type "
                + "FROM user_management "
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


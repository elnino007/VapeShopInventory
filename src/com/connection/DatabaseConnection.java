package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    
    private Connection conn;
    final private String DRIVER = "com.mysql.jdbc.Driver";
    final private String USER = "root";
    final private String PASSWORD = "";
    final private String URL = "jdbc:mysql://localhost:3306/";
    final private String DATABASE = "vapeshop";
    
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return conn;
    }
}

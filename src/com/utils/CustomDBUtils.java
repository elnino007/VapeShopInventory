package com.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class CustomDBUtils {
    public static TableModel resultSetToTableModel(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
           
            Vector columnNames = new Vector();

            // Get the column names
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }
//            columnNames.addElement("Username");
//            columnNames.addElement("Full Name");
//            columnNames.addElement("Age");
//            columnNames.addElement("Gender");
//            columnNames.addElement("User Type");

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {            
                         newRow.addElement(rs.getObject(i)); 
                }

                rows.addElement(newRow);
            }
            
            DefaultTableModel tableModel = new DefaultTableModel() {
          
                @Override
                public boolean isCellEditable(int row, int column) {
                   //all cells false
                   return false;
                }
            };
            tableModel.setDataVector(rows, columnNames);

//            return new DefaultTableModel(rows, columnNames);
            return tableModel;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    
    public static TableModel resultSetToTableModel(ResultSet rs, String forUser) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
           
            Vector columnNames = new Vector();

            // Get the column names
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }
//            columnNames.addElement("Username");
//            columnNames.addElement("Full Name");
//            columnNames.addElement("Age");
//            columnNames.addElement("Gender");
//            columnNames.addElement("User Type");

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {  
                    if(i == 4){
                        boolean genderStatus = (Boolean) rs.getObject(i);
               
                        if(genderStatus == false) {
                            newRow.addElement("Male");
                        }else{
                            newRow.addElement("Female");
                        }
                        
                    }else{
                         newRow.addElement(rs.getObject(i));
                    }
                    
                   
                }

                rows.addElement(newRow);
            }
            
            DefaultTableModel tableModel = new DefaultTableModel() {
          
                @Override
                public boolean isCellEditable(int row, int column) {
                   //all cells false
                   return false;
                }
            };
            tableModel.setDataVector(rows, columnNames);

//            return new DefaultTableModel(rows, columnNames);
            return tableModel;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}

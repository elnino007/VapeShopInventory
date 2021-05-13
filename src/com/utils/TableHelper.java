/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author shaitozen007
 */
public class TableHelper {
    public static void renderHeader(JTable table, String... headerNames) {
        int count = 0;
        for (String headerName : headerNames) {
            table.getColumnModel().getColumn(count).setHeaderValue(headerName);
            count++;
        }
    }
    
     public static void setColumnFixedWidth(JTable table, int... columnWidth) {
        
        try {
            TableColumnModel model = table.getColumnModel();
            for (int i = 0; i < columnWidth.length; i++) {
                TableColumn column = model.getColumn(i);
                column.setPreferredWidth(columnWidth[i]);
            }
        } catch (Exception e) {
        
        }
    }
}

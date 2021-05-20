/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author shaitozen
 */
public class MyTableCellRender extends DefaultTableCellRenderer{
    public MyTableCellRender() {
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
  
        String number = (String)value;
        int val = Integer.parseInt(number);

            if(val > 5){
                if(val >= 5000) {
                setForeground(Color.black);
                setBackground(Color.red);
                }
                else {
                setForeground(Color.black);
                setBackground(Color.yellow);
                }
            }
            else{
            setBackground(Color.white);
            setForeground(Color.black);
            }
         setText(value !=null ? value.toString() : "");
        return this;
        }
}

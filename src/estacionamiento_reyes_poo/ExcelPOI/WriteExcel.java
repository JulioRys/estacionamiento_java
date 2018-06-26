/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamiento_reyes_poo.ExcelPOI;

import java.awt.Desktop;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import org.apache.poi.hssf.usermodel.*;

/**
 *
 * @author reyesj1g
 */
public class WriteExcel {
    

    public void crearArchivoExcel(String rango, TableModel model) throws FileNotFoundException, IOException{


    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Sammy Tab");
    
    String userHomeFolder = System.getProperty("user.home")+ "/Desktop";
    File excelFile = new File(userHomeFolder, rango+".xls");
    
    if(excelFile.exists()){
        JOptionPane.showMessageDialog(null, "El archivo ya existe.");
        int respuesta = JOptionPane.showConfirmDialog(null, "Desea generar archivo nuevo?","Estacionamiento Reyes", JOptionPane.YES_NO_OPTION);
        //System.out.println(respuesta);
        
        if(respuesta==1){
        Desktop.getDesktop().open(excelFile);
        }else{
            
            HSSFRow headerRow = sheet.createRow(0);
        for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }
    
    HSSFRow row = sheet.createRow(1); //fila para descargar datos
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
            for(int cols = 0; cols < model.getColumnCount(); cols++){ //For each table column
                row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
                sheet.autoSizeColumn(cols);
            }

        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
        }
        
        
        workbook.write(new FileOutputStream(excelFile));
        JOptionPane.showMessageDialog(null, "Se guardo el archivo: "+rango+".xls en :\n"+userHomeFolder);
        workbook.close();
        Desktop.getDesktop().open(excelFile);
            
        }
        
    }else{
    
    HSSFRow headerRow = sheet.createRow(0);
        for(int headings = 0; headings < model.getColumnCount(); headings++){ //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }
    
    HSSFRow row = sheet.createRow(1); //fila para descargar datos
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
            for(int cols = 0; cols < model.getColumnCount(); cols++){ //For each table column
                row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
                sheet.autoSizeColumn(cols);
            }

        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
        }
        
        
        workbook.write(new FileOutputStream(excelFile));
        JOptionPane.showMessageDialog(null, "Se guardo el archivo: "+rango+".xls en :\n"+userHomeFolder);
        workbook.close();
        Desktop.getDesktop().open(excelFile);
    
    }
    }
    
}

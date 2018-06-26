/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamiento_reyes_poo;

import estacionamiento_reyes_poo.ExcelPOI.WriteExcel;
import java.awt.Color;
import java.io.IOException;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author reyesj1g
 */
public class Ganancias_ER extends javax.swing.JFrame {

    /**
     * Creates new form Ganancias_ER
     */
    public Ganancias_ER() {
        initComponents();
        setTimeFormat();
    }
    
    private double ivaSAT = 0.16, ivaPagar, subTotal, totalFinal;
    private int totalTarjeta = 0, totalEfectivo = 0;
    private int servicios = 0, Total = 0, pagoTarjeta = 0, pagoEfectivo = 0;
    private Date date = new Date();
    private DateFormat formatoEntrada= new SimpleDateFormat("MMM d, yyyy");
    private DateFormat formatoSalidaFecha = new SimpleDateFormat("yyyy-MM-dd");
    private SQLAdmin sql = new SQLAdmin("localhost:3306","java","password","estacionamiento_reyes");
    public DefaultTableModel dftm = new DefaultTableModel();
    
    public void cargarTabla(){
        sql.cerrarModelo(dftm);
        dateChooserCombo1.setDateFormat(formatoEntrada);
        dateChooserCombo2.setDateFormat(formatoEntrada);
        
        Date f1, f2;
        try {
            f1 = formatoEntrada.parse(dateChooserCombo1.getText());
            f2 = formatoEntrada.parse(dateChooserCombo2.getText());

                if(f1.after(date)||f1.after(f2)||f2.after(date)||f2.before(f1)){
                    //System.out.println("Error con las fechas");
                    JOptionPane.showMessageDialog(this,"Error en seleccion de fechas.","Error",JOptionPane.ERROR_MESSAGE);
                    setTimeFormat();
                }else{
//                System.out.println("Fecha inicial: "+formatoSalidaFecha.format(f1));
//                System.out.println("Fecha final: "+formatoSalidaFecha.format(f2));  
                dftm = sql.historico(formatoSalidaFecha.format(f1), formatoSalidaFecha.format(f2));
                jTableVentas.setModel(dftm);
                jTableVentas.setDefaultEditor(Object.class, null);
                
                for(int i = 0; i < jTableVentas.getRowCount(); i++){
                    if(jTableVentas.getValueAt(i, 5)=="Efectivo"){
                        
                    }
                }
                jTableVentas.setSelectionBackground(Color.yellow);
        }
        } catch (ParseException ex) {
            //System.out.println("Error en fechas");
            JOptionPane.showMessageDialog(this,"Error en seleccion de fechas.","Error",JOptionPane.ERROR_MESSAGE);
            setTimeFormat();
        }

    }
    
    public void sumarPagos(){
        totalEfectivo = 0; totalTarjeta = 0; servicios = 0; Total = 0; pagoTarjeta = 0; pagoEfectivo = 0;
        
        String tempPago = null, tipoPago = null;
        
        for ( int i = 0; i < jTableVentas.getRowCount(); i++){
            tempPago = (String) jTableVentas.getModel().getValueAt(i, 4);
            tipoPago = (String) jTableVentas.getModel().getValueAt(i, 5);

            //calcular ganancias del periodo
            String strTemp = tempPago.replace("$ ", "");
            //System.out.println(strTemp);
            Total = Total + Integer.parseInt(strTemp);
            
            //servicios del periodo
            servicios++;
            
            //determinar tipo de pago
            if(tipoPago.equals("Efectivo")){
                    pagoEfectivo++;
                    totalEfectivo+=Integer.parseInt(strTemp);
                }else{
                    pagoTarjeta++;
                    totalTarjeta+=Integer.parseInt(strTemp);
            }
                
        }
        //calcular IVA
        ivaPagar = Total * ivaSAT;
        subTotal = Total - ivaPagar;
        totalFinal = ivaPagar + subTotal;
        //jTextFieldIva.setText("$ "+ivaPagar);
        jTextFieldIva.setText(String.format("$ %.2f", ivaPagar));
        jTextFieldIngresoTotal.setText(String.format("$ %.2f", totalFinal));
        jTextFieldIngresos.setText(String.format("$ %.2f", subTotal));
        jTextFieldServicios.setText(Integer.toString(servicios));
        jTextFieldEfectivo.setText(Integer.toString(pagoEfectivo));
        jTextFieldTarjeta.setText(Integer.toString(pagoTarjeta));
        jTextFieldTotalEfectivo.setText("$ "+totalEfectivo+".00");
        jTextFieldTotalTarjeta.setText("$ "+totalTarjeta+".00");
        
        
    }
    
    public void setTimeFormat(){

        dateChooserCombo1.setDateFormat(formatoEntrada);
        dateChooserCombo2.setDateFormat(formatoEntrada);
        
        Calendar cal = Calendar.getInstance();
        dateChooserCombo1.setSelectedDate(cal);
        dateChooserCombo2.setSelectedDate(cal);
        
        
    }
    
    public void exportarExcel(){
                Date f1 = null, f2 = null;
        
        try {
            f1 = formatoEntrada.parse(dateChooserCombo1.getText());
            f2 = formatoEntrada.parse(dateChooserCombo2.getText());
            
        } catch (ParseException ex) {
            Logger.getLogger(Ganancias_ER.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        try {
            WriteExcel xls = new WriteExcel();
            String rango = formatoSalidaFecha.format(f1)+"-"+formatoSalidaFecha.format(f2);
            xls.crearArchivoExcel(rango, jTableVentas.getModel() );
        } catch (IOException ex) {
            Logger.getLogger(Ganancias_ER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        jTextFieldServicios = new javax.swing.JTextField();
        jTextFieldIngresos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldEfectivo = new javax.swing.JTextField();
        jTextFieldTarjeta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIva = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldIngresoTotal = new javax.swing.JTextField();
        jTextFieldTotalEfectivo = new javax.swing.JTextField();
        jTextFieldTotalTarjeta = new javax.swing.JTextField();
        jButtonExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jLabel1.setText("F. Inicio");

        jLabel2.setText("F. Fin");

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Cargar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try {
            dateChooserCombo1.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2018, 0, 9),
                new java.util.GregorianCalendar(2018, 0, 9))));
    } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
        e1.printStackTrace();
    }

    try {
        dateChooserCombo2.setDefaultPeriods(new datechooser.model.multiple.PeriodSet(new datechooser.model.multiple.Period(new java.util.GregorianCalendar(2018, 0, 9),
            new java.util.GregorianCalendar(2018, 0, 9))));
} catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
    e1.printStackTrace();
    }

    jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {},
            {},
            {},
            {}
        },
        new String [] {

        }
    ));
    jScrollPane1.setViewportView(jTableVentas);

    jTextFieldServicios.setEditable(false);
    jTextFieldServicios.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldServicios.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jTextFieldIngresos.setEditable(false);
    jTextFieldIngresos.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldIngresos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jLabel3.setText("Servicios:");

    jLabel4.setText("Ingresos: ");

    jLabel5.setText("Pago efectivo: ");

    jLabel6.setText("Pago tarjeta: ");

    jTextFieldEfectivo.setEditable(false);
    jTextFieldEfectivo.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jTextFieldTarjeta.setEditable(false);
    jTextFieldTarjeta.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldTarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jButton2.setBackground(new java.awt.Color(255, 51, 51));
    jButton2.setForeground(new java.awt.Color(255, 255, 255));
    jButton2.setText("Salir");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jLabel7.setText("Iva:");

    jTextFieldIva.setEditable(false);
    jTextFieldIva.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldIva.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jLabel8.setText("Total:");

    jTextFieldIngresoTotal.setEditable(false);
    jTextFieldIngresoTotal.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldIngresoTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jTextFieldTotalEfectivo.setEditable(false);
    jTextFieldTotalEfectivo.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldTotalEfectivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jTextFieldTotalTarjeta.setEditable(false);
    jTextFieldTotalTarjeta.setBackground(new java.awt.Color(255, 255, 255));
    jTextFieldTotalTarjeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jButtonExportar.setBackground(new java.awt.Color(153, 255, 153));
    jButtonExportar.setText("Exportar Excel");
    jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonExportarActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(56, 56, 56)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldIngresos, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jTextFieldIva)
                                .addComponent(jTextFieldIngresoTotal))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldEfectivo)
                                        .addComponent(jTextFieldTarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                    .addComponent(jTextFieldServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldTotalTarjeta)
                                .addComponent(jTextFieldTotalEfectivo))
                            .addGap(0, 0, Short.MAX_VALUE)))))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel1)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTotalTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldIngresoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExportar)))
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton2)
            .addGap(19, 19, 19))
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarTabla();
        sumarPagos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        sql.cerrarModelo(dftm);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        cargarTabla();
        sumarPagos();
        exportarExcel();
    }//GEN-LAST:event_jButtonExportarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ganancias_ER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ganancias_ER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ganancias_ER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ganancias_ER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ganancias_ER().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonExportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableVentas;
    private javax.swing.JTextField jTextFieldEfectivo;
    private javax.swing.JTextField jTextFieldIngresoTotal;
    private javax.swing.JTextField jTextFieldIngresos;
    private javax.swing.JTextField jTextFieldIva;
    private javax.swing.JTextField jTextFieldServicios;
    private javax.swing.JTextField jTextFieldTarjeta;
    private javax.swing.JTextField jTextFieldTotalEfectivo;
    private javax.swing.JTextField jTextFieldTotalTarjeta;
    // End of variables declaration//GEN-END:variables
}

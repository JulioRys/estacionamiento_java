/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamiento_reyes_poo;

import java.sql.SQLException;
import java.text.*;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author reyesj1g
 */
public class Ingreso_Nuevo_ER extends javax.swing.JFrame {

    private DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbm2 = new DefaultComboBoxModel();
    private String cajon = null;
    
    private SQLAdmin sql = new SQLAdmin("localhost:3306","java","password","estacionamiento_reyes");
    private Main m = new Main();
    
    
    public Ingreso_Nuevo_ER() {
        initComponents();
        horaEntrada();         
        
        this.cajon = sql.cajonDisponible();
        try {
            cbm = sql.cargarListaEmpleados();
            cbm2 = sql.cargarMarcas();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        jComboBoxAcomodador.setModel(cbm);
        jComboBoxMarca.setModel(cbm2);
    }
    //sobre carga de constructor
        public Ingreso_Nuevo_ER(String c) {
        initComponents();
        horaEntrada();
        this.cajon = c;
        
        try {
            cbm = sql.cargarListaEmpleados();
            cbm2 = sql.cargarMarcas();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        jComboBoxAcomodador.setModel(cbm);
        jComboBoxMarca.setModel(cbm2);
    }
        public Ingreso_Nuevo_ER(String placas, String marca, String modelo, String color, String cajon){
        initComponents();
        horaEntrada();         
        
        if(cajon == null){
            this.cajon = sql.cajonDisponible();
        }else{
            this.cajon = cajon;
        }
        
        try {
            cbm = sql.cargarListaEmpleados();
            cbm2 = sql.cargarMarcas();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        jComboBoxAcomodador.setModel(cbm);
        jComboBoxMarca.setModel(cbm2);  
            
            
            jComboBoxMarca.setSelectedItem(marca);
            jTextFieldPlacas.setText(placas);
            jTextFieldModelo.setText(modelo);
            jTextFieldColor.setText(color);
            
        }
    
    private String horaEntrada(){
        Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat hr = new SimpleDateFormat("HH:mm");
            String fechaEntrada = df.format(date)+ " " + hr.format(date);
            String fechaBoleto =  df.format(date)+ hr.format(date);
            jTextFieldHora.setText(fechaEntrada);
        return fechaBoleto;
    }
    
    private void generarBoleto(){
        String boleto = jTextFieldHora.getText()+"_"+jTextFieldPlacas.getText()+"_"+
                jComboBoxMarca.getSelectedItem().toString()+"_"+jTextFieldModelo.getText()+"_"+jTextFieldColor.getText();
        jTextFieldBoleto.setText(boleto);
    }
    
    public boolean validacion(){
        boolean status = true;
        if(jTextFieldColor.getText().isEmpty()||jTextFieldModelo.getText().isEmpty()||jTextFieldPlacas.getText().isEmpty()){
            status = false;
        }
        if(jComboBoxAcomodador.getSelectedIndex()==0||jComboBoxMarca.getSelectedIndex()==0||jComboBoxEstado.getSelectedIndex()==-1){
            status = false;
        }
        
        return status;
    }
    
        public void setLabels(){
                
        //m.jLabel4.setText(FechaActual.format(date));
        
        int ocupado = sql.getInventarioTotal();
        int disponible = 30 - ocupado;
        
        m.jTextFieldOcupados.setText(Integer.toString(ocupado));
        m.jTextFieldOcupados.setEditable(false);
        m.jTextFieldDisponibles.setText(Integer.toString(disponible));
        m.jTextFieldDisponibles.setEditable(false);
        
        sql.setInventarioTotal(0);
        System.out.println("Se calculo la ocupacion de labels.");
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldHora = new javax.swing.JTextField();
        jTextFieldBoleto = new javax.swing.JTextField();
        jTextFieldPlacas = new javax.swing.JTextField();
        jTextFieldColor = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jComboBoxEstado = new javax.swing.JComboBox<>();
        jComboBoxAcomodador = new javax.swing.JComboBox<>();
        jComboBoxMarca = new javax.swing.JComboBox<>();
        jButtonGuardar = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Marca");

        jLabel2.setText("Modelo");

        jLabel3.setText("Color");

        jLabel5.setText("Placas");

        jLabel6.setText("Estado");

        jLabel7.setText("Acomodador");

        jLabel8.setText("Fecha y hora");

        jLabel9.setText("Num Boleto");

        jTextFieldHora.setEnabled(false);

        jTextFieldBoleto.setEnabled(false);

        jTextFieldPlacas.setBackground(new java.awt.Color(204, 255, 204));

        jTextFieldColor.setBackground(new java.awt.Color(255, 255, 204));

        jTextFieldModelo.setBackground(new java.awt.Color(255, 255, 204));

        jComboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bueno", "Regular", "Malo" }));
        jComboBoxEstado.setSelectedIndex(-1);

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar auto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jTextFieldPlacas)
                            .addComponent(jTextFieldModelo)
                            .addComponent(jTextFieldColor)
                            .addComponent(jTextFieldHora)
                            .addComponent(jComboBoxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAcomodador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldBoleto))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAcomodador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed

        //Este metodo valida que no haya campos en blanco.
        if(!validacion()){
            JOptionPane.showMessageDialog(this,"Favor de llenar todos los campos.");
        }else{
        generarBoleto();
        
        JOptionPane.showMessageDialog(this, "Cajon asignado: "+cajon);
        sql.guardarAuto(jComboBoxMarca.getSelectedItem().toString(), jTextFieldModelo.getText(), 
                jTextFieldColor.getText(), jTextFieldPlacas.getText(), jComboBoxEstado.getSelectedItem().toString(), 
                jComboBoxAcomodador.getSelectedItem().toString(), jTextFieldHora.getText(), jTextFieldBoleto.getText(), cajon);
        this.dispose();
        Main m = new Main();
        sql.cerrarModelo(m.dftm);
        m.setModels();
        setLabels();
        }
        
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(cajon == null){
            Ingreso_Anterior_ER ia = new Ingreso_Anterior_ER();
            ia.setVisible(true);
        }else{
            Ingreso_Anterior_ER iac = new Ingreso_Anterior_ER(cajon);
            iac.setVisible(true);
            
        }
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ingreso_Nuevo_ER().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxAcomodador;
    private javax.swing.JComboBox<String> jComboBoxEstado;
    private javax.swing.JComboBox<String> jComboBoxMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextFieldBoleto;
    private javax.swing.JTextField jTextFieldColor;
    private javax.swing.JTextField jTextFieldHora;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPlacas;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamiento_reyes_poo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author reyesj1g
 */
public class Cobro_ER extends javax.swing.JFrame {

    /**
     * Creates new form Cobro_ER
     */
    
    private DefaultComboBoxModel cbm3 = new DefaultComboBoxModel();
    private String fechaSalida, horaSalida, fechaEntrada, horaEntrada, tipoPago, cajonImport;
    private long tarifaFinal;
    private long horas, minutos, dias, tarifaXDia = 150, tarifaXHora = 25, tarifaMinAd = 6;
    private double tarifaBase = .208;
    
    //variables para manejo de tiempo
    private Date date = new Date();
    private DateFormat fs = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat hs = new SimpleDateFormat("HH:mm");
    private DateFormat formatoEntrada= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private DateFormat formatoSalidaFecha = new SimpleDateFormat("yyyy-MM-dd");
    private DateFormat formatoSalidaHora = new SimpleDateFormat("HH:mm");
    
    private SQLAdmin sql = new SQLAdmin("localhost:3306","java","password","estacionamiento_reyes");
    
    public Cobro_ER() {
        initComponents();
        cbm3 = sql.cargarCajonesEnUso();
        jComboBoxCajonesOcupados.setModel(cbm3);
        
    }
    public Cobro_ER(String cajon){
        this.cajonImport = cajon;
        initComponents();
        cbm3 = sql.cargarCajonesEnUso();
        jComboBoxCajonesOcupados.setModel(cbm3);
        jComboBoxCajonesOcupados.setSelectedItem(cajon);
    }
    
    
    public void cargarDatosBoletos(String[] cdb){
        
        jTextFieldCajon.setText(cdb[1]);
        jTextFieldMarca.setText(cdb[3]);
        jTextFieldModelo.setText(cdb[4]);
        jTextFieldColor.setText(cdb[5]);
        jTextFieldPlacas.setText(cdb[6]);
        jTextFieldEstado.setText(cdb[7]);
        jTextFieldAcomodador.setText(cdb[8]);
        jTextFieldFechaHora.setText(cdb[9]);
        jTextFieldBoleto.setText(cdb[10]);
        
        fechaSalida = fs.format(date);
        horaSalida = hs.format(date);
        
        try {
        
            jTextFieldFechaSalida.setText(fechaSalida);
            jTextFieldHoraSalida.setText(horaSalida);            
            
            Date f1 = formatoEntrada.parse(jTextFieldFechaHora.getText());
                        
            jTextFieldFechaEntrada.setText(formatoSalidaFecha.format(f1));
            jTextFieldHoraEntrada.setText(formatoSalidaHora.format(f1));
            
            Date f2 = null;
            Date f3 = null;
            
            f2 = formatoEntrada.parse(jTextFieldFechaHora.getText());
            f3 = formatoEntrada.parse(jTextFieldFechaSalida.getText()+" "+jTextFieldHoraSalida.getText());
            
             long diff = f3.getTime() - f2.getTime();
             minutos = TimeUnit.MILLISECONDS.toMinutes(diff);
             horas = TimeUnit.MILLISECONDS.toHours(diff);
             dias = TimeUnit.MILLISECONDS.toDays(diff);
            
                        
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
//        System.out.println("Minutos :\t"+minutos);
//        System.out.println("Horas :\t\t"+horas );
//        System.out.println("Dias :\t\t"+dias);

    }
    
    public void calcularTarifa(){
        
//calcular pago dependiendo del timpo de uso.
//        System.out.println("Minutos :\t"+minutos);
//        System.out.println("Horas :\t\t"+horas );
//        System.out.println("Dias :\t\t"+dias);
        if( dias > 1){
            tarifaFinal = dias * tarifaXDia;
            jTextFieldTarifaFinal.setText("$ "+tarifaFinal);
            jLabelMensaje.setText("Se cobra tarifa base diaria.");
        } else{
            if(horas >= 12) {
            dias = dias * 1;
            tarifaFinal = 1 * tarifaXDia;
            jTextFieldTarifaFinal.setText("$ "+tarifaFinal);
            jLabelMensaje.setText("Se cobra tarifa base diaria.");
            }else {
                horas = minutos / 60;
//                int minutosExtra = (int) (minutos - 60*horas);

//                System.out.println("Horas "+horas);
//                System.out.println("Minutos "+minutosExtra);

                tarifaFinal = (long) (tarifaBase * minutos);
                jTextFieldTarifaFinal.setText("$ "+tarifaFinal);
                jLabelMensaje.setText("Se cobra tarifa base por minuto.");
            }
    }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCajon = new javax.swing.JTextField();
        jTextFieldMarca = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldColor = new javax.swing.JTextField();
        jTextFieldPlacas = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        jTextFieldAcomodador = new javax.swing.JTextField();
        jTextFieldFechaHora = new javax.swing.JTextField();
        jTextFieldBoleto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxCajonesOcupados = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldFechaSalida = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldHoraSalida = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldFechaEntrada = new javax.swing.JTextField();
        jTextFieldHoraEntrada = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTarifaFinal = new javax.swing.JTextField();
        jRadioButtonEfectivo = new javax.swing.JRadioButton();
        jRadioButtonTarjeta = new javax.swing.JRadioButton();
        jButtonGuardarPago = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jLabelMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Cajon");

        jLabel2.setText("Marca");

        jLabel3.setText("Modelo");

        jLabel4.setText("Color");

        jLabel5.setText("Placas");

        jLabel6.setText("Estado");

        jLabel7.setText("Acomodador");

        jLabel8.setText("Hora");

        jLabel9.setText("Boleto");

        jTextFieldCajon.setEditable(false);
        jTextFieldCajon.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldMarca.setEditable(false);
        jTextFieldMarca.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldModelo.setEditable(false);
        jTextFieldModelo.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldColor.setEditable(false);
        jTextFieldColor.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldPlacas.setEditable(false);
        jTextFieldPlacas.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldEstado.setEditable(false);
        jTextFieldEstado.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldAcomodador.setEditable(false);
        jTextFieldAcomodador.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldFechaHora.setEditable(false);
        jTextFieldFechaHora.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jTextFieldBoleto.setEditable(false);
        jTextFieldBoleto.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N

        jLabel10.setText("Seleccione cajon:");

        jComboBoxCajonesOcupados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCajonesOcupadosActionPerformed(evt);
            }
        });

        jLabel11.setText("Fecha salida: ");

        jTextFieldFechaSalida.setEditable(false);
        jTextFieldFechaSalida.setBackground(new java.awt.Color(255, 204, 153));
        jTextFieldFechaSalida.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N

        jLabel12.setText("Hora salida:");

        jTextFieldHoraSalida.setEditable(false);
        jTextFieldHoraSalida.setBackground(new java.awt.Color(255, 204, 153));
        jTextFieldHoraSalida.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N

        jLabel13.setText("Fecha Entrada:");

        jLabel14.setText("Hora Entrada: ");

        jTextFieldFechaEntrada.setEditable(false);
        jTextFieldFechaEntrada.setBackground(new java.awt.Color(204, 255, 204));
        jTextFieldFechaEntrada.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N

        jTextFieldHoraEntrada.setEditable(false);
        jTextFieldHoraEntrada.setBackground(new java.awt.Color(204, 255, 204));
        jTextFieldHoraEntrada.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N

        jLabel15.setText("Tarifa a pagar:");

        jTextFieldTarifaFinal.setEditable(false);
        jTextFieldTarifaFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jRadioButtonEfectivo.setText("Efectivo");
        jRadioButtonEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEfectivoActionPerformed(evt);
            }
        });

        jRadioButtonTarjeta.setText("Tarjeta");
        jRadioButtonTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTarjetaActionPerformed(evt);
            }
        });

        jButtonGuardarPago.setText("Guardar Pago");
        jButtonGuardarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarPagoActionPerformed(evt);
            }
        });

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(41, 41, 41)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxCajonesOcupados, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldEstado, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldAcomodador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldModelo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldColor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldPlacas, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCajon, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldFechaHora, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTarifaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelMensaje)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonGuardarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jRadioButtonEfectivo)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButtonTarjeta))
                                            .addComponent(jButtonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(18, 18, 18))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(21, 21, 21)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12))
                                        .addGap(26, 26, 26)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldHoraEntrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                        .addComponent(jTextFieldFechaEntrada, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldHoraSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBoxCajonesOcupados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldCajon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldFechaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextFieldHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel12))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldTarifaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldAcomodador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonEfectivo)
                    .addComponent(jRadioButtonTarjeta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMensaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldBoleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGuardarPago))
                .addGap(4, 4, 4)
                .addComponent(jButtonSalir))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCajonesOcupadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCajonesOcupadosActionPerformed
        String[] datosBoleto = sql.cargarBoleto(jComboBoxCajonesOcupados.getSelectedItem().toString());
        cargarDatosBoletos(datosBoleto);
        calcularTarifa();
    }//GEN-LAST:event_jComboBoxCajonesOcupadosActionPerformed

    private void jRadioButtonEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEfectivoActionPerformed
        jRadioButtonTarjeta.setSelected(false);
    }//GEN-LAST:event_jRadioButtonEfectivoActionPerformed

    private void jRadioButtonTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTarjetaActionPerformed
        jRadioButtonEfectivo.setSelected(false);
    }//GEN-LAST:event_jRadioButtonTarjetaActionPerformed

    private void jButtonGuardarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarPagoActionPerformed
        if(jRadioButtonEfectivo.isSelected()||jRadioButtonTarjeta.isSelected()){
            if(jRadioButtonEfectivo.isSelected()){
            tipoPago = "Efectivo";
        }else{
            tipoPago = "Tarjeta";
        }
        sql.guardarPago(jTextFieldCajon.getText(), jTextFieldBoleto.getText(), jTextFieldAcomodador.getText(), 
                jTextFieldFechaHora.getText(), jTextFieldFechaSalida.getText()+" "+jTextFieldHoraSalida.getText(), 
                    jTextFieldTarifaFinal.getText(), tipoPago);
        this.dispose();
        Main m = new Main();
        sql.cerrarModelo(m.dftm);
        m.setModels();
        m.setLabels();
        }else{
            JOptionPane.showMessageDialog(null, "Favor de seleccionar tipo de pago", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonGuardarPagoActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

                
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cobro_ER().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGuardarPago;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JComboBox<String> jComboBoxCajonesOcupados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMensaje;
    private javax.swing.JRadioButton jRadioButtonEfectivo;
    private javax.swing.JRadioButton jRadioButtonTarjeta;
    private javax.swing.JTextField jTextFieldAcomodador;
    private javax.swing.JTextField jTextFieldBoleto;
    private javax.swing.JTextField jTextFieldCajon;
    private javax.swing.JTextField jTextFieldColor;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldFechaEntrada;
    private javax.swing.JTextField jTextFieldFechaHora;
    private javax.swing.JTextField jTextFieldFechaSalida;
    private javax.swing.JTextField jTextFieldHoraEntrada;
    private javax.swing.JTextField jTextFieldHoraSalida;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPlacas;
    private javax.swing.JTextField jTextFieldTarifaFinal;
    // End of variables declaration//GEN-END:variables
}

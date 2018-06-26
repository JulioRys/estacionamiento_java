
package estacionamiento_reyes_poo;

import javax.swing.*;
import javax.swing.table.*;
import java.util.Date;
import java.text.*;


public final class Main extends javax.swing.JFrame {
    
    private SQLAdmin sql = new SQLAdmin("localhost:3306","java","password","estacionamiento_reyes");
    private Date date = new Date();
    private DateFormat FechaActual= new SimpleDateFormat("dd-MM-yyyy");
    public DefaultTableModel dftm = new DefaultTableModel();
   
    
    public Main(){
        initComponents();
        setModels();
        setLabels();
        
    }
    
    public void setModels(){
        dftm = sql.mostrarInventario();
        jTableInventario.setModel(dftm);
        jTableInventario.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel = jTableInventario.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(5);    
        colModel.getColumn(1).setPreferredWidth(5);  
        colModel.getColumn(2).setPreferredWidth(5); 
        colModel.getColumn(3).setPreferredWidth(5); 
        colModel.getColumn(4).setPreferredWidth(5); 
        colModel.getColumn(5).setPreferredWidth(5);
        jTableInventario.setDefaultEditor(Object.class, null);
        
    }
    
    public void setLabels(){
                
        jLabel4.setText(FechaActual.format(date));
        
        int ocupado = sql.getInventarioTotal();
        int disponible = 30 - ocupado;
        
        jTextFieldOcupados.setText(Integer.toString(ocupado));
        jTextFieldOcupados.setEditable(false);
        jTextFieldDisponibles.setText(Integer.toString(disponible));
        jTextFieldDisponibles.setEditable(false);
        
        sql.setInventarioTotal(0);
        System.out.println("Se calculo la ocupacion de labels.");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonIngreso = new javax.swing.JButton();
        jButtonCobro = new javax.swing.JButton();
        jButtonNomina = new javax.swing.JButton();
        jButtonEmpleados = new javax.swing.JButton();
        jButtonChat = new javax.swing.JButton();
        jButtonCajones = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInventario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldOcupados = new javax.swing.JTextField();
        jTextFieldDisponibles = new javax.swing.JTextField();
        jButtonModels = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemVersion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estacionamiento Reyes");

        jButtonIngreso.setBackground(new java.awt.Color(153, 255, 153));
        jButtonIngreso.setText("Entrada");
        jButtonIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresoActionPerformed(evt);
            }
        });

        jButtonCobro.setBackground(new java.awt.Color(255, 102, 102));
        jButtonCobro.setText("Salida");
        jButtonCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCobroActionPerformed(evt);
            }
        });

        jButtonNomina.setBackground(new java.awt.Color(20, 238, 238));
        jButtonNomina.setText("Nomina");

        jButtonEmpleados.setBackground(new java.awt.Color(20, 238, 238));
        jButtonEmpleados.setText("Empleados");
        jButtonEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEmpleadosActionPerformed(evt);
            }
        });

        jButtonChat.setBackground(new java.awt.Color(20, 238, 238));
        jButtonChat.setText("Chat");

        jButtonCajones.setBackground(new java.awt.Color(20, 238, 238));
        jButtonCajones.setText("Cajones");
        jButtonCajones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCajonesActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTableInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableInventario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Inventario Actual EXPERIMENTAL");

        jLabel2.setText("Cajones en uso: ");

        jLabel3.setText("Cajones disponibles: ");

        jLabel4.setText("Fecha: ");

        jTextFieldOcupados.setBackground(new java.awt.Color(255, 255, 204));
        jTextFieldOcupados.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldDisponibles.setBackground(new java.awt.Color(204, 255, 204));
        jTextFieldDisponibles.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButtonModels.setBackground(new java.awt.Color(205, 207, 219));
        jButtonModels.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estacionamiento_reyes_poo/reloadXS.png"))); // NOI18N
        jButtonModels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModelsActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(20, 238, 238));
        jButton2.setText("Ganancias");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Hola");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemSalir);

        jMenuBar1.add(jMenu1);

        jMenuEdit.setText("Edit");

        jMenuItemVersion.setText("Version");
        jMenuItemVersion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVersionActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemVersion);

        jMenuBar1.add(jMenuEdit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButtonCajones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jButtonIngreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldOcupados)
                                    .addComponent(jTextFieldDisponibles)
                                    .addComponent(jButtonModels, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonIngreso)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldOcupados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCajones)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEmpleados))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonModels, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCobro)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonChat)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNomina)))
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresoActionPerformed
        Ingreso_Nuevo_ER i = new Ingreso_Nuevo_ER();
        i.setVisible(true);
    }//GEN-LAST:event_jButtonIngresoActionPerformed

    private void jButtonEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEmpleadosActionPerformed
        Empleados_ER e = new Empleados_ER();
        e.setVisible(true);
    }//GEN-LAST:event_jButtonEmpleadosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonCajonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCajonesActionPerformed
 
        sql.cargarBotoncitos();
    }//GEN-LAST:event_jButtonCajonesActionPerformed

    private void jButtonCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCobroActionPerformed

        if(jTableInventario.getSelectedRow()>=0){
            System.out.println("Se cobra con la seleccion de tabla");
            String cajon = (String) dftm.getValueAt(jTableInventario.getSelectedRow(), 0);
            Cobro_ER c = new Cobro_ER(cajon);
            c.setVisible(true);
        }else{
                    Cobro_ER c = new Cobro_ER();
                    c.setVisible(true);
        }

    }//GEN-LAST:event_jButtonCobroActionPerformed

    private void jMenuItemVersionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVersionActionPerformed
        JOptionPane.showMessageDialog(null,"Programa: Estacionamiento Reyes \nVersion: 1.0 \nUltima actualizacion: 02/01/20018");
    }//GEN-LAST:event_jMenuItemVersionActionPerformed

    private void jButtonModelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModelsActionPerformed
        sql.cerrarModelo(dftm);
        setModels();
        setLabels();
    }//GEN-LAST:event_jButtonModelsActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Ganancias_ER g = new Ganancias_ER();
        g.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCajones;
    private javax.swing.JButton jButtonChat;
    private javax.swing.JButton jButtonCobro;
    private javax.swing.JButton jButtonEmpleados;
    private javax.swing.JButton jButtonIngreso;
    private javax.swing.JButton jButtonModels;
    private javax.swing.JButton jButtonNomina;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemVersion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTableInventario;
    public javax.swing.JTextField jTextFieldDisponibles;
    public javax.swing.JTextField jTextFieldOcupados;
    // End of variables declaration//GEN-END:variables
}

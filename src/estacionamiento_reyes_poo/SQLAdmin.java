
package estacionamiento_reyes_poo;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class SQLAdmin {
    
    private String user;
    private String password;
    private String db;
    private String host;
    private String url;
    
    private Color grin = new Color(153,255,153);
    
    private Connection conn = null;
    private Statement st;
    private ResultSet rs;
    private static Object[] tempBD;
    private static String[] stringArray;
    private static String[][] infoBD;
    private int inventarioTotal;

    public void setInventarioTotal(int inventarioTotal) {
        this.inventarioTotal = inventarioTotal;
    }

    public int getInventarioTotal() {
        return inventarioTotal;
    }

    private static String[] columasEmpleados = {"ID Empleado","Nombre", "Puesto","Salario", "Status"} ;
    private static String[] columasInventario = {"Cajon","Marca", "Modelo","Color", "Placas", "Acomodador", "Hora Entrada"} ;
    private static String[] columasHistoricoAutos = {"Placas", "Marca", "Modelo", "Color"} ;
    private static String[] columasHistoricoVentas = {"Boleto", "Acomodador", "Entrada", "Salida", "Cobro","TipoPago","Cajon"} ;
    private static DefaultTableModel modeloTablaEmpleados = new DefaultTableModel(infoBD, columasEmpleados);
    private static DefaultTableModel modeloTablaInventario = new DefaultTableModel(infoBD, columasInventario);
    private static DefaultTableModel modeloTablaHistoricoAutos = new DefaultTableModel(infoBD, columasHistoricoAutos);
    private static DefaultTableModel modeloTablaHistoricoVentas = new DefaultTableModel(infoBD, columasHistoricoVentas);
    
    DefaultComboBoxModel comboboxmodel;
    
        public SQLAdmin()
    {
        this.url = "jdbc:mysql://"+this.host+"/"+this.db;
    }
        public SQLAdmin(String server, String usuario, String contrasena, String bd)
    {
        this.user = usuario;
        this.password = contrasena;
        this.db = bd;
        this.host = server;
        this.url = "jdbc:mysql://"+this.host+"/"+this.db;
    }
    
    public void conectar(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn!= null)
            {
                System.out.println("Conexion a base de datos "+url+" ...Ok");
                st = conn.createStatement();
            }
        }
        catch(SQLException ex){
            System.out.println("Hubo un problema al intentar conectarse con la base de datos "+url);
        }
        catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
    }
        
    public void desconectar(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            conn.close();
            System.out.println("Desconectar a base de datos "+url+" ...Ok");
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
    }
    
    public DefaultTableModel mostrarEmpleados(){
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT * FROM estacionamiento_reyes.empleados";
            rs = st.executeQuery(queryString);
            String status = null;
            while(rs.next()){
                
                if("1".equals(rs.getString(5))){
                    status = "Activo";
                }else{
                    status = "Inactivo";
                }
                
                tempBD = new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),
                    "$ "+rs.getString(4),status};
                modeloTablaEmpleados.addRow(tempBD);
                }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
        
        return modeloTablaEmpleados;
        
    }
    
    public DefaultTableModel mostrarInventario(){
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT * FROM estacionamiento_reyes.cajones\n" +
                                    "WHERE disponible = 1;";
            rs = st.executeQuery(queryString);
            
            while(rs.next()){
                tempBD = new Object[]{rs.getString(2),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(9),rs.getString(10)};
                modeloTablaInventario.addRow(tempBD);
                inventarioTotal++;
                }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
        
        return modeloTablaInventario;
        
    }
    
    public DefaultTableModel mostrarHistoricoAutos(String marca){
        
        String[] splited = null;
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT DISTINCT MID(boleto, 18,50) AS boletoNvo\n" +
                                    "FROM estacionamiento_reyes.historico\n" +
                                    "WHERE boleto LIKE '%"+marca+"%'";
            rs = st.executeQuery(queryString);
            
            while(rs.next()){
                
                String autoTemp = rs.getString(1);
                splited = autoTemp.split("_");

                                                                
                tempBD = new Object[]{splited[0],splited[1], splited[2], splited[3]};
                modeloTablaHistoricoAutos.addRow(tempBD);
                                
                }

            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
        
        return modeloTablaHistoricoAutos;
        
    }
    
    public void cerrarModelo(DefaultTableModel m){
        m.setRowCount(0);
    }
    
    public void guardarEmpleados(String nombre, String puesto, String salario, int activo){
        
        try{
            conectar();
            st = conn.createStatement();
           
            String queryString = "INSERT INTO empleados (Nombre, Puesto, Salario, activo) VALUES"
                    + " ('"+nombre+"','"+puesto+"','"+salario+"','"+activo+"');";
            st.executeUpdate(queryString);
            
            JOptionPane.showMessageDialog(null,"Se ha agregado al empleado "+nombre+" a la base de datos.");
                        
        } catch (SQLException sql) {
    
            System.out.println(sql);

        }finally{
            desconectar();
        }
    }
    
    public void guardarPago(String cajon, String boleto, String acomodador, String horaEntrada, String horaSalida, String pagoTotal, String tipoPago){
        
        try{
            conectar();
            st = conn.createStatement();
            //guardar datos en tabla historico
            String insertString = "INSERT INTO estacionamiento_reyes.historico(boleto, acomodador, horaEntrada, horaSalida, pagoTotal, tipoPago, cajon)\n" +
                    "VALUES ('"+boleto+"','"+acomodador+"','"+horaEntrada+"','"+horaSalida+"','"+pagoTotal+"','"+tipoPago+"','"+cajon+"')";
            st.executeUpdate(insertString);
            //liberar cajon
            String updateString = "UPDATE estacionamiento_reyes.cajones\n" +
                                    "SET disponible = 0, Marca = '', Modelo = '', Color = '',Placas = '', Estado = '', "
                                        + "Acomodador = '', hora = null,boleto = ''\n" +
                                    "WHERE cajon = '"+cajon+"';";
            st.executeUpdate(updateString);
            
            JOptionPane.showMessageDialog(null, "Se ha guardado el pago de boleto: "+boleto+"\n"+"Se ha liberado el cajon: "+cajon);
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
        
    }
    
    public DefaultComboBoxModel cargarListaEmpleados() throws SQLException{
        
        comboboxmodel = new DefaultComboBoxModel();
        comboboxmodel.addElement(" ");
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT Nombre FROM estacionamiento_reyes.empleados\n" +
                                        "where Puesto = 'acomodador' AND activo = 1;";
            rs = st.executeQuery(queryString);
            while(rs.next()){
                comboboxmodel.addElement(rs.getString(1));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
             desconectar();
        }
        
        return comboboxmodel;
        
    }
    
    public DefaultComboBoxModel cargarMarcas() throws SQLException{
        
        comboboxmodel = new DefaultComboBoxModel();
        comboboxmodel.addElement(" ");

        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT marcas FROM estacionamiento_reyes.marcas\n" +
                                        "order by marcas;";
            rs = st.executeQuery(queryString);
            while(rs.next()){
                
                //jc.addItem(rs.getString(1));
                comboboxmodel.addElement(rs.getString(1));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
             desconectar();
        }
        
        return comboboxmodel;
        
    }
    
    public DefaultComboBoxModel cargarCajonesEnUso(){
        comboboxmodel = new DefaultComboBoxModel();
        comboboxmodel.addElement(" ");
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT cajon, disponible FROM estacionamiento_reyes.cajones\n" +
                                    "WHERE disponible = 1;";
            rs = st.executeQuery(queryString);
            while(rs.next()){
                comboboxmodel.addElement(rs.getString(1));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
             desconectar();
        }
        
        return comboboxmodel;
    }
    
    public String cajonDisponible(){
        
        String cajon = null;
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT * FROM estacionamiento_reyes.cajones\n" +
                                    "WHERE disponible = 0\n" +
                                            "limit 1;";
            rs = st.executeQuery(queryString);
            
            rs.first();
            cajon = rs.getString(2);
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
                desconectar();
            }
        
        return cajon;
        }
    
    public void guardarAuto(String Marca, String Modelo, String Color, String Placas, String Estado, String Acomodador, String Hora, String Boleto, String Cajon){
        try{
            conectar();
            st = conn.createStatement();
            String updateString = "UPDATE estacionamiento_reyes.cajones\n" 
                    + "SET disponible=1, Marca = '"+Marca+"', Modelo = '"+Modelo+"', Color = '"+Color+"', "
                    + "Placas = '"+Placas+"', Estado = '"+Estado+"', Acomodador = '"+Acomodador+"', "
                    + "Hora = '"+Hora+"', boleto = '"+Boleto+"'\n" +
                        "WHERE cajon = '"+Cajon+"';";
            st.executeUpdate(updateString);

            JOptionPane.showMessageDialog(null, "Se cargo el boleto: "+Boleto);
            
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
            
        }finally{
            desconectar();
        }
    }
    
    public void desactivarEmpleado(String id){
        try{
            conectar();
            st = conn.createStatement();
            String updateString ="UPDATE estacionamiento_reyes.empleados\n" +
                                    "SET activo = 2\n" +
                                    "WHERE idempleados = "+id+"";
            st.executeUpdate(updateString);

            JOptionPane.showMessageDialog(null, "Se desactivo el empleado #: "+id);
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
    }
    
    public void activarEmpleado(String id){
        try{
            conectar();
            st = conn.createStatement();
            String updateString ="UPDATE estacionamiento_reyes.empleados\n" +
                                    "SET activo = 1\n" +
                                    "WHERE idempleados = "+id+"";
            st.executeUpdate(updateString);

            JOptionPane.showMessageDialog(null, "Se activo el empleado #: "+id);
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
    }
    
    public void cargarBotoncitos(){
        JButton jb;
        JFrame frame = new JFrame("Cajones Disponibles");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel pl = new JPanel ();
        JPanel windowContent = new JPanel();
        GridLayout gl =new GridLayout(3,10);
        gl.setHgap(10);
        gl.setVgap(10);
        pl.setLayout(gl);
        windowContent.add("Center",pl);
                
        //magia
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT idcajones, cajon, disponible FROM estacionamiento_reyes.cajones;";
            rs = st.executeQuery(queryString);
                        
            while(rs.next()){

                String nombreBoton = rs.getString(2);
                int track = rs.getInt(3);    

                jb = new JButton(nombreBoton);                       
                jb.addActionListener((ActionEvent ae) -> {
                    if(track == 1){
                        JOptionPane.showMessageDialog(null,"El cajon "+nombreBoton+" esta ocupado.");
                        int respuesta = JOptionPane.showConfirmDialog(null, "Desea liberar cajon?","Estacionamiento Reyes", JOptionPane.YES_NO_OPTION);
                        if(respuesta==0){
                            frame.dispose();
                            Cobro_ER cer = new Cobro_ER(nombreBoton);
                            cer.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"El cajon "+nombreBoton+" esta disponible.");
                        int respuesta = JOptionPane.showConfirmDialog(null, "Desea asignar cajon?","Estacionamiento Reyes", JOptionPane.YES_NO_OPTION);
                        if(respuesta == 0){
                            frame.dispose();
                            Ingreso_Nuevo_ER i = new Ingreso_Nuevo_ER(nombreBoton);
                            i.setVisible(true);
                        }
                    }
                });

                if(track == 0){

                    jb.setBackground(grin);
                    }else{
                        jb.setBackground(Color.LIGHT_GRAY);
                    }

                 pl.add(jb);
                        
            }
            
        }catch(SQLException e){
            System.out.println(e);
            
        }finally{
            desconectar();
        }
        
        windowContent.add("Center",pl);
        frame.setContentPane(windowContent);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        
    }
    
    public String[] cargarBoleto(String c){
        
        System.out.println(c);
        
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT * FROM estacionamiento_reyes.cajones\n" +
                                            "where cajon = '"+c+"';";
            rs = st.executeQuery(queryString);
            
            rs.first();
            stringArray = new String[]{rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11)};
            
           
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }

        return stringArray;
    }
   
    public DefaultTableModel historico(String inicio, String fin){
         
        String in = inicio+" 00:00:00";
        String fn = fin+" 23:59:59";
        try{
            conectar();
            st = conn.createStatement();
            String queryString = "SELECT * FROM estacionamiento_reyes.historico\n" +
                                    "WHERE horaSalida BETWEEN '"+in+"' AND '"+fn+"'\n" +
                                    "order by horaSalida";
            rs = st.executeQuery(queryString);
            
            while(rs.next()){
                tempBD = new Object[]{rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
                modeloTablaHistoricoVentas.addRow(tempBD);

                
                }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            desconectar();
        }
        return modeloTablaHistoricoVentas;
    }
}

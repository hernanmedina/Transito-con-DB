package modelo;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class VehiculoDAO {

    ConexionDB miConexion= new ConexionDB();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public static String placa_vehiculo = "";
    public static String marca_vehiculo = "";
    public static int anhoFab_vehiculo = 0;
    
    //Validar Placa
    public boolean validarPlaca(String placa) {
        return placa.matches("^[A-Z]{3}[0-9]{3}$");
    }


    //Registar vehiculo
    public boolean insertarVehiculo(Vehiculo unVeiculo){
        if (!unVeiculo.getPlaca().matches("^[A-Z]{3}[0-9]{3}$") || !(String.valueOf(unVeiculo.getAnhoFab()).matches("^[0-9]{4}$"))) {
            JOptionPane.showMessageDialog(null, "La placa debe tener el formato ABC123 \n (3 letras mayúsculas y 3 números y el año de fabricación debe tener 4 dígitos)");
            return false;
        }
        
        String query= "INSERT INTO vehiculo(placa, marca, anhoFab)" + "VALUES(?,?,?)";
         
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst= this.con.prepareStatement(query); 
            pst.setString(1, unVeiculo.getPlaca());
            pst.setString(2, unVeiculo.getMarca());
            pst.setInt(3, unVeiculo.getAnhoFab()); 
            pst.execute(); // ejecutamos el query
            
            System.out.println(pst);
            
            return true;
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al ingresar los datos: "+e);
            return false;
        }
        
    }
    
    //Consultar vehiculo
    public Vehiculo consultarQuery(String plak){
        String query = "SELECT placa, marca, anhoFab FROM vehiculo WHERE placa = ?";
        Vehiculo vehiculo= new Vehiculo();
        
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst = this.con.prepareStatement(query);
            pst.setString(1, plak);
            System.out.println("contenido del query:\n"+pst);
            rs= pst.executeQuery();
            
            if(rs.next()){
                vehiculo.setPlaca(rs.getString("placa"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setAnhoFab(rs.getInt("anhoFab"));
                
                
            }else{
                JOptionPane.showMessageDialog(null, "Vehiculo no Encontrado");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del vehiculo: "+e);
        }
        
        return vehiculo;
    }
      
    //Modificar Vehiculo
    public boolean actualizarVehiculo(Vehiculo unVehiculo){
        String query= "UPDATE vehiculo SET marca=?, anhoFab=? WHERE placa=?";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst= this.con.prepareStatement(query);
            
            pst.setString(1, unVehiculo.getMarca());
            pst.setInt(2, unVehiculo.getAnhoFab());
            pst.setString(3, unVehiculo.getPlaca());
            System.out.println(pst);
            pst.execute();
            
            return true;
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al modificar los datos (Clase DAO):\n"+e);
            return false;
        }
    }
    
    //Eliminar Vehiculo
    public boolean eliminarVehiculo(String plak){
        String query= "DELETE FROM vehiculo WHERE placa=?";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            pst.setString(1, plak);
            System.out.println(pst);
            pst.execute();
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos: "+e);
            return false;
        } 
    }
    
    //mostrar vehiculo en lista
    public void mostrarLista(JTable tabla) {
        String query = "SELECT placa, marca, anhoFab FROM vehiculo ORDER BY placa ASC";

        try {
            this.con = this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            // Crear modelo para la tabla
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
            modelo.addColumn("Placa");
            modelo.addColumn("Marca");
            modelo.addColumn("Año Fabr");

            // Agregar datos al modelo
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("placa");
                fila[1] = rs.getString("marca");
                fila[2] = rs.getInt("anhoFab");
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e);
        }
    }
    
      
      
    
   
}

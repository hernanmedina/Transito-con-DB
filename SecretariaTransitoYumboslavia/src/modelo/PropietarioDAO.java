package modelo;

import java.awt.Component;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import vista.VistaPropietario;

/**
 *
 * @author Hernan Medina
 */
public class PropietarioDAO {

    ConexionDB miConexion = new ConexionDB();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public static int dni_propietario;
    public static String nombres_Propietario;
    public static String apellidos_propietario;
    public static String direccion_propietario;

    public boolean ingresarPropietario(Propietario unPropietario) {

        if (unPropietario.getDni() <= 0) {
            JOptionPane.showMessageDialog(null, "El DNI debe ser un número positivo y diferente de cero");
            return false;

        }
        String query = "INSERT INTO propietario(dni, nombres, apellidos, direccion)" + "VALUES(?,?,?,?)";

        try {
            this.con = this.miConexion.obtenerConexion();

            pst = this.con.prepareStatement(query);
            pst.setInt(1, unPropietario.getDni());
            pst.setString(2, unPropietario.getNombres());
            pst.setString(3, unPropietario.getApellidos());
            pst.setString(4, unPropietario.getDireccion());
            pst.execute(); // ejecutamos el query

            System.out.println(pst);

            return true;
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al ingresar los datos: " + e);
            return false;
        }
    }

    //Modificar Propietario
    public boolean actualizarPropietario(Propietario unPropietario) {
        String query = "UPDATE propietario SET nombres=?, apellidos=?, direccion=? WHERE dni=?";

        try {
            this.con = this.miConexion.obtenerConexion();

            pst = this.con.prepareStatement(query);

            pst.setString(1, unPropietario.getNombres());
            pst.setString(2, unPropietario.getApellidos());
            pst.setString(3, unPropietario.getDireccion());
            pst.setInt(4, unPropietario.getDni());
            System.out.println(pst);
            pst.execute();

            return true;
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al modificar los datos (Clase DAO):\n" + e);
            return false;
        }
    }
    
    //Consultar Propietario
    public Propietario consultarQuery(int cedula){
        String query = "SELECT dni, nombres, apellidos, direccion FROM propietario WHERE dni = ?";
        Propietario unPropietario = new Propietario();
        
        try{
            this.con= this.miConexion.obtenerConexion();
            
            pst = this.con.prepareStatement(query);
            pst.setInt(1, cedula);
            System.out.println("contenido del query:\n"+pst);
            rs= pst.executeQuery();
            
            if(rs.next()){
                unPropietario.setDni(rs.getInt("dni"));
                unPropietario.setNombres(rs.getString("nombres"));
                unPropietario.setApellidos(rs.getString("apellidos"));
                unPropietario.setDireccion(rs.getString("direccion"));

            }else{
                JOptionPane.showMessageDialog(null, "Propietario no Encontrado");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al obtener los datos del propietario: "+e);
        }
        
        return unPropietario;
    }
    
    //Eliminar Propietario
    public boolean eliminarPropietario(int cedula){
        String query= "DELETE FROM propietario WHERE dni=?";
        
        try{
            this.con= this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            pst.setInt(1, cedula);
            System.out.println(pst);
            pst.execute();
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos: "+e);
            return false;
        } 
    }
    
    //Listar Propietario
    public void mostrarLista(JTable tabla) {
        String query = "SELECT dni, nombres, apellidos, direccion FROM propietario ORDER BY nombres ASC";

        try {
            this.con = this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            // Crear modelo para la tabla
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
            modelo.addColumn("Cedula");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Direccion");

            // Agregar datos al modelo
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("dni");
                fila[1] = rs.getString("nombres");
                fila[2] = rs.getString("apellidos");
                fila[3] = rs.getString("direccion");
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e);
        }
    }

}

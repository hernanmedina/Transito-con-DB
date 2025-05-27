/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Hernan Medina
 */
public class TarjetaPropiedadDAO {
    ConexionDB miConexion = new ConexionDB();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    //Listar TarjetaPropiedad
    public void mostrarLista(JTable tabla) {
        String query = "SELECT p.nombres AS propietario, v.placa AS vehiculo, t.codigo_tarjeta, t.fecha_emision\n" +
                        "FROM tarjeta_propiedad t\n" +
                        "JOIN propietario p ON t.id_propietario = p.id\n" +
                        "JOIN vehiculo v ON t.id_vehiculo = v.id\n" +
                        "ORDER BY p.nombres ASC;";

        try {
            this.con = this.miConexion.obtenerConexion();
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            // Crear modelo para la tabla
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Crear cabeceras de la tabla
            modelo.addColumn("Propietario");
            modelo.addColumn("Vehiculo");
            modelo.addColumn("Codigo");
            modelo.addColumn("Fecha Exp");

            // Agregar datos al modelo
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("propietario");
                fila[1] = rs.getString("vehiculo");
                fila[2] = rs.getInt("codigo_tarjeta");
                fila[3] = rs.getString("fecha_emision");
                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + ex);
        }
    }

    //Agregar Targeta de Propiedad
    public boolean insertarTarjetaPropiedad(TarjetaPropiedad tarjeta) {
        String query = "INSERT INTO tarjeta_propiedad (id_propietario, id_vehiculo, codigo_tarjeta, fecha_emision) VALUES (?, ?, ?, ?)";

        try {
            this.con = this.miConexion.obtenerConexion();

            // Buscar el ID del vehiculo según su placa
            VehiculoDAO vehiculoDAO = new VehiculoDAO();
            PropietarioDAO propietarioDAO = new PropietarioDAO();
            
            int idVehiculo = vehiculoDAO.obtenerIdVehiculoPorPlaca(tarjeta.getVehiculo().getPlaca());
            int idPropietario = propietarioDAO.obtenerIdPropietarioPorDni(tarjeta.getPropietario().getDni());

            if (idPropietario == -1) {
                JOptionPane.showMessageDialog(null, "El propietario no existe");
                return false;
            }
            
            if (idVehiculo == -1) {
                JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
                return false;
            }

            this.pst = this.con.prepareStatement(query);

            // Asignamos los valores
            this.pst.setInt(1, idPropietario);
            this.pst.setInt(2, idVehiculo); 
            this.pst.setInt(3, tarjeta.getCodigo());
            this.pst.setString(4, tarjeta.getFechaExp());

            int resultado = this.pst.executeUpdate();

            return resultado > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar tarjeta: " + ex);
            return false;
        }
    }
    
    //modificar tarjeta de propiedad 
    public boolean modificarTarjetaPropiedad(TarjetaPropiedad tarjeta) {
        String query = "UPDATE tarjeta_propiedad SET id_propietario = ?, id_vehiculo = ?,  fecha_emision = ? WHERE codigo_tarjeta= ?";

        try {
            this.con = this.miConexion.obtenerConexion();
            this.pst = this.con.prepareStatement(query);
            
            VehiculoDAO vehiculoDAO = new VehiculoDAO();              // Buscar el ID del vehiculo según su placa
            PropietarioDAO propietarioDAO = new PropietarioDAO();     // Buscar el ID del propietario según su dni
            
            int idVehiculo = vehiculoDAO.obtenerIdVehiculoPorPlaca(tarjeta.getVehiculo().getPlaca());
            int idPropietario = propietarioDAO.obtenerIdPropietarioPorDni(tarjeta.getPropietario().getDni());

            if (idPropietario == -1) {
                JOptionPane.showMessageDialog(null, "El propietario no existe");
                return false;
            }
            
            if (idVehiculo == -1) {
                JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
                return false;
            }

            // Asignamos los valores a los ?
            this.pst.setInt(1, idPropietario);
            this.pst.setInt(2, idVehiculo);                   
            this.pst.setString(3, tarjeta.getFechaExp());    
            this.pst.setInt(4, tarjeta.getCodigo()); 
                           

            int resultado = this.pst.executeUpdate();

            return resultado > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar tarjeta: " + ex);
            return false;
        }
    }

    //Consultar tarjeta de propiedad
    public void consultarTarjetaPorId(int idTarjeta) {
        String query = "SELECT p.nombres AS propietario, v.placa AS vehiculo, t.codigo_tarjeta, t.fecha_emision "
                     + "FROM tarjeta_propiedad t "
                     + "JOIN propietario p ON t.id_propietario = p.id "
                     + "JOIN vehiculo v ON t.id_vehiculo = v.id "
                     + "WHERE t.codigo_tarjeta = ?";

        try {
            this.con = this.miConexion.obtenerConexion();
            this.pst = this.con.prepareStatement(query);
            this.pst.setInt(1, idTarjeta);
            this.rs = this.pst.executeQuery();

            if (rs.next()) {
                String propietario = rs.getString("propietario");
                String vehiculo = rs.getString("vehiculo");
                int codigo = rs.getInt("codigo_tarjeta");
                String fecha = rs.getString("fecha_emision");

                String mensaje = "Propietario: " + propietario
                               + "\nVehículo: " + vehiculo
                               + "\nCódigo Tarjeta: " + codigo
                               + "\nFecha de Emisión: " + fecha;

                JOptionPane.showMessageDialog(null, mensaje, "Información de la Tarjeta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna tarjeta con codigo: " + idTarjeta, "Consulta", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar tarjeta: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Eliminar trajeta de propiedad
    public boolean eliminarTarjetaPropiedad(int idTarjeta) {
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        PreparedStatement pst4 = null;
        ResultSet rs = null;

        try {
            con = this.miConexion.obtenerConexion();
            con.setAutoCommit(false);  // iniciar transacción

            // obtener id_vehiculo e id_propietario relacionado
            String queryObtener = "SELECT id_vehiculo, id_propietario FROM tarjeta_propiedad WHERE codigo_tarjeta = ?";
            pst1 = con.prepareStatement(queryObtener);
            pst1.setInt(1, idTarjeta);
            rs = pst1.executeQuery();

            if (rs.next()) {
                int idVehiculo = rs.getInt("id_vehiculo");
                int idPropietario = rs.getInt("id_propietario");

                // Eliminar tarjeta_propiedad
                String queryTarjeta = "DELETE FROM tarjeta_propiedad WHERE codigo_tarjeta = ?";
                pst2 = con.prepareStatement(queryTarjeta);
                pst2.setInt(1, idTarjeta);
                pst2.executeUpdate();

                // Eliminar vehiculo
                String queryVehiculo = "DELETE FROM vehiculo WHERE id = ?";
                pst3 = con.prepareStatement(queryVehiculo);
                pst3.setInt(1, idVehiculo);
                pst3.executeUpdate();

                // Eliminar propietario
                String queryPropietario = "DELETE FROM propietario WHERE id = ?";
                pst4 = con.prepareStatement(queryPropietario);
                pst4.setInt(1, idPropietario);
                pst4.executeUpdate();

                con.commit();   //confirmar transacción
                JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Tarjeta no encontrada.");
                return false;
            }

        } catch (SQLException ex) {
            try {
                if (con != null) {
                    con.rollback();  // si algo falla, deshacer todo
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                //e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex);
            return false;
        } 
    }
}
 
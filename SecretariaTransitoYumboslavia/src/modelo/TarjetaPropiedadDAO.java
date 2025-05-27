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
    
    private Propietario propietario;
    private Vehiculo vehiculo;
    private int codigo;
    private String fecha_emision;
    
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


}

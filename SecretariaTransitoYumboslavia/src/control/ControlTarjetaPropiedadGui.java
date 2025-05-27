/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.TarjetaPropiedad;
import modelo.Propietario;
import modelo.PropietarioDAO;
import modelo.TarjetaPropiedadDAO;
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import vista.VistaTarjetaPropiedad;

/**
 *
 * @author UNIVALLE
 */
public class ControlTarjetaPropiedadGui implements ActionListener{

    private VistaTarjetaPropiedad vistaTarjePro;
    private TarjetaPropiedad unaTarjetaProp;
    private TarjetaPropiedadDAO unaTarjetaPropiedadDAO; 
    
    public ControlTarjetaPropiedadGui(){
        
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        PropietarioDAO propietarioDAO = new PropietarioDAO();
        this.unaTarjetaPropiedadDAO = new TarjetaPropiedadDAO();


        this.vistaTarjePro = new VistaTarjetaPropiedad();
        this.vistaTarjePro.setVisible(true);
        
        this.vistaTarjePro.jbtn_aceptar.addActionListener(this);
        this.vistaTarjePro.jbtn_consultar.addActionListener(this);
        this.vistaTarjePro.jbtn_eliminar.addActionListener(this);
        this.vistaTarjePro.jbtn_modificar.addActionListener(this);
        this.vistaTarjePro.jbtn_listar.addActionListener(this);
  
        // Llenar ComboBox de vehículos
        this.vistaTarjePro.jComb_vehiculo.removeAllItems();
        for (Vehiculo v : vehiculoDAO.obtenerVehiculos()) {
            this.vistaTarjePro.jComb_vehiculo.addItem(v);
        }

        // Llenar ComboBox de propietarios
        this.vistaTarjePro.jComb_propietarios.removeAllItems();
        for (Propietario p : propietarioDAO.obtenerPropietarios()) {
            this.vistaTarjePro.jComb_propietarios.addItem(p);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Boton Agregar Tarjeta de Propiedad
        if(e.getSource() == this.vistaTarjePro.jbtn_aceptar){
            try {
                Propietario prop = (Propietario) this.vistaTarjePro.jComb_propietarios.getSelectedItem();
                Vehiculo veh = (Vehiculo) this.vistaTarjePro.jComb_vehiculo.getSelectedItem();
                int codigo = Integer.parseInt(this.vistaTarjePro.jtf_codigo.getText());
                String fecha = this.vistaTarjePro.jtf_fecha.getText();

                TarjetaPropiedad nuevaTarjeta = new TarjetaPropiedad(prop, veh, codigo, fecha);

                if (this.unaTarjetaPropiedadDAO.insertarTarjetaPropiedad(nuevaTarjeta)) {
                    JOptionPane.showMessageDialog(null, "Tarjeta ingresada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo ingresar la tarjeta.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
        //Boton Listar Tarjetas de Propiedad
        if(e.getSource() == this.vistaTarjePro.jbtn_listar){
            this.unaTarjetaPropiedadDAO.mostrarLista(this.vistaTarjePro.jTable_tarjetaPropiedad);
        }
        
        //Boton Modificar targetas por codigo
        if (e.getSource()== this.vistaTarjePro.jbtn_modificar) {
            
        }
        
        //Boton de consultar tarjeta
        if (e.getSource()== this.vistaTarjePro.jbtn_consultar) {
            
        }
        
        //Boton Eliminar tarjetas de propiedad
        if (e.getSource()== this.vistaTarjePro.jbtn_eliminar) {
            
        }
    }
}

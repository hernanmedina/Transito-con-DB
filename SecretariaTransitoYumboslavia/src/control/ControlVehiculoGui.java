/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Vehiculo;
import vista.*;


/**
 *
 * @author UNIVALLE
 */
public class ControlVehiculoGui implements ActionListener{

    private VistaVehiculo vistaVehic;
    private VistaPropietario vistaProp;
    private Vehiculo vehiculo;
    private List<Vehiculo> listadoAutos;
    
    public ControlVehiculoGui(List<Vehiculo> listaAuts){
        
        this.vistaVehic= new VistaVehiculo();
        this.vistaVehic.setVisible(true);
        this.vistaVehic.jbtn_aceptar.addActionListener(this);
        this.listadoAutos= listaAuts;
        
    }
    
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }
    
    
    
    // obtener el año mas antiguo en la lista
    private int obtenerAnhoMasAntiguo() {
        if (listadoAutos.isEmpty()) {
            return 1886; // si no hay autos aun, usamos el valor base historico de el primer auto fabricado
        }

        int anhoMin = listadoAutos.get(0).getAnhoFab();
        for (Vehiculo v : listadoAutos) {
            if (v.getAnhoFab() < anhoMin) {
                anhoMin = v.getAnhoFab();
            }
        }
        return anhoMin;
    }
           
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vistaVehic.jbtn_aceptar) {

            boolean datosValidos = true;
            this.vehiculo = new Vehiculo();

            // Validar placa
            String placa = this.vistaVehic.jtf_placa.getText().trim();
            if (placa.matches("^[A-Z]{3}[0-9]{3}$")) {
                this.vehiculo.setPlaca(placa);
            } else {
                JOptionPane.showMessageDialog(vistaVehic, "Formato de placa no valido. Use el formato ABC123.");
                datosValidos = false;
            }

            // Validar marca
            String marca = this.vistaVehic.jtf_marca.getText().trim();
            if (!marca.isEmpty()) {
                this.vehiculo.setMarca(marca);
            } else {
                JOptionPane.showMessageDialog(vistaVehic, "El campo 'Marca' no puede estar vacio.");
                datosValidos = false;
            }

            // Validar año de fabricacion 
            try {
                int anhoFab = Integer.parseInt(this.vistaVehic.jtf_anhoFab.getText().trim());
                int anhoMin = obtenerAnhoMasAntiguo(); // año mas antiguo 
                int anhoMax = 2026;

                if (anhoFab >= anhoMin && anhoFab <= anhoMax) {
                this.vehiculo.setAnhoFab(anhoFab);
                
                } else {
                    JOptionPane.showMessageDialog(vistaVehic, "El año 1886 valor base historico del primer Auto fabricado.");
                    datosValidos = false;
                }
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(vistaVehic, "Debe ingresar el año en numeros NumberFormatException.");
                datosValidos = false;
            }

            // Solo se agrega si todo es valido
            if (datosValidos) {
                this.listadoAutos.add(vehiculo);
                JOptionPane.showMessageDialog(vistaVehic, "Vehiculo agregado correctamente.");
            }
        }
    }

}

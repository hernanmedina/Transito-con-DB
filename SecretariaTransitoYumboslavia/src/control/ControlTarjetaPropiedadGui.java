/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.TarjetaPropiedad;
import modelo.Propietario;
import modelo.Vehiculo;
import vista.VistaTarjetaPropiedad;

/**
 *
 * @author UNIVALLE
 */
public class ControlTarjetaPropiedadGui implements ActionListener{

    private VistaTarjetaPropiedad vistaTarjePro;
    private TarjetaPropiedad unaTarjetaProp;
    
    //Listas de objetos de las clases modelo
    private List<Vehiculo> listadoAutos;       
    private List<Propietario> listaPropietarios;
    private List<TarjetaPropiedad> listaTarjetasPropiedad;
    
    public ControlTarjetaPropiedadGui(List<Vehiculo> listaAuts, List<Propietario> listaPropitars, List<TarjetaPropiedad> listaTarjProp){
        
        this.vistaTarjePro = new VistaTarjetaPropiedad();
        this.vistaTarjePro.setVisible(true);
        this.vistaTarjePro.jbtn_aceptar.addActionListener(this);
        
        //Listado de Autos *****************************************************
        this.listadoAutos= listaAuts;
        
        //Se remueve el contenido por defecto del JComboBox jComb_vehiculo
        this.vistaTarjePro.jComb_vehiculo.removeAllItems();
        
        //Se agrega el listado de vehículos al JComboBox jComb_vehiculo
        for(Vehiculo v : this.listadoAutos){
            this.vistaTarjePro.jComb_vehiculo.addItem(v);
        }
        
        //Listado de Propietarios **********************************************
        this.listaPropietarios= listaPropitars;
        
        //Se remueve el contenido por defecto del JComboBox jComb_propietarios
        this.vistaTarjePro.jComb_propietarios.removeAllItems();
        
        //Se agrega el listado de vehiculos al JComboBox jComb_propietarios
        for(Propietario p: this.listaPropietarios){
            this.vistaTarjePro.jComb_propietarios.addItem(p);
        }
        
        //Listado de Tarjetas de Propiedad *************************************
        this.listaTarjetasPropiedad= listaTarjProp;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == this.vistaTarjePro.jbtn_aceptar){
            
            this.unaTarjetaProp= new TarjetaPropiedad();
            
            this.unaTarjetaProp.setVehiculo((Vehiculo) this.vistaTarjePro.jComb_vehiculo.getSelectedItem());
            this.unaTarjetaProp.setPropietario((Propietario) this.vistaTarjePro.jComb_propietarios.getSelectedItem());            
            
            try{
                this.unaTarjetaProp.setCodigo(Integer.parseInt(this.vistaTarjePro.jtf_codigo.getText()));
            }catch(NumberFormatException exc){
                JOptionPane.showMessageDialog(this.vistaTarjePro, "Debe ingresar el codigo en numeros");
            }
            
            String fecha= this.vistaTarjePro.jtf_fecha.getText();
            
            if(fecha.matches("^[0-9]{2,}/[0-9]{2,}/[0-9]{4,}$")){
                this.unaTarjetaProp.setFechaExp(fecha);
            }else{
                JOptionPane.showMessageDialog(this.vistaTarjePro, "Formato de placa\nNo valido\n dd/mm/aaaa");
            }
            
            this.listaTarjetasPropiedad.add(unaTarjetaProp);
            JOptionPane.showMessageDialog(vistaTarjePro, "Tarjeta de propiedad creada correctamente.");
            
        }
    }
    
}

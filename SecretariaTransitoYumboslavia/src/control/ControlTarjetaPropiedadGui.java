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
        this.vistaTarjePro.jbtn_consultar.addActionListener(this);
        this.vistaTarjePro.jbtn_eliminar.addActionListener(this);
        this.vistaTarjePro.jbtn_modificar.addActionListener(this);
        this.vistaTarjePro.jbtn_listar.addActionListener(this);
        
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
            
    //Funciones de tarjetas    
    //Listar Tarjetas de Propiedad
    public void listarTargetaPropietarios(){
        
        String tarjetasPro="";
        if(this.listaTarjetasPropiedad.size()>0){                
            for(int i=0; i<this.listaPropietarios.size(); i++){
                tarjetasPro += "Vehiculo: "+this.listaTarjetasPropiedad.get(i).getVehiculo()+"\n";
                tarjetasPro += "Propietario: "+this.listaTarjetasPropiedad.get(i).getPropietario()+"\n";
                tarjetasPro += "Codigo: "+this.listaTarjetasPropiedad.get(i).getCodigo()+"\n";
                tarjetasPro += "Fecha de exp: "+this.listaTarjetasPropiedad.get(i).getFechaExp()+"\n";
                tarjetasPro += "-----------------------------\n";
            }
            JOptionPane.showMessageDialog(vistaTarjePro, tarjetasPro);
        }else{            
            System.out.println("No se han registrado tarjetas de propiedad");
            JOptionPane.showMessageDialog(vistaTarjePro,"No se han registrado tarjetas de propiedad");
        }

    }
            
    //Modificar tarjetas
    public void modificarTarjetaPorCodigo() {
        try {
            String codigoStr = this.vistaTarjePro.jtf_codigo.getText() ;

            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un codigo.");
            }

            int codigo = Integer.parseInt(codigoStr.trim());
            TarjetaPropiedad tarjeta = buscarTarjetaPorCodigo(codigo);

            if (tarjeta == null) {
                throw new NullPointerException("Tarjeta no encontrada. (NullPointerException)");
            }

            String nuevaFecha = this.vistaTarjePro.jtf_fecha.getText() ;

            if (nuevaFecha == null || nuevaFecha.trim().isEmpty()) {
                throw new IllegalArgumentException("La fecha no puede estar vacia.");
            }

            // Validacion opcional: patron simple dd/mm/aaaa
            if (!nuevaFecha.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                throw new IllegalArgumentException("Formato de fecha invalido. Use dd/mm/aaaa.");
            }

            tarjeta.setFechaExp(nuevaFecha);

            JOptionPane.showMessageDialog(null, "Tarjeta modificada correctamente.");

        } catch (IllegalArgumentException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    //Consultar tarjeta por codigo
    public void consultarTarjetaPorCodigo() {
        try {
            String codigoStr = this.vistaTarjePro.jtf_codigo.getText() ;

            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un codigo.");
            }

            int codigo = Integer.parseInt(codigoStr.trim());

            TarjetaPropiedad tarjeta = buscarTarjetaPorCodigo(codigo);

            if (tarjeta == null) {
                throw new NullPointerException("Tarjeta no encontrada. (NullPointerException)");
            }

            JOptionPane.showMessageDialog(null,
                "Tarjeta encontrada:\n" +
                "Codigo: " + tarjeta.getCodigo() + "\n" +
                "Fecha de expedicion: " + tarjeta.getFechaExp() + "\n" +
                "Vehiculo: " + tarjeta.getVehiculo().getPlaca() + " - " + tarjeta.getVehiculo().getMarca() + "\n" +
                "Propietario: " + tarjeta.getPropietario().getNombres() + " " + tarjeta.getPropietario().getApellidos()
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El codigo debe ser un numero entero. (NumberFormatException)");
        } catch (IllegalArgumentException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    
    //Buscar tarjetas por codigo
    private TarjetaPropiedad buscarTarjetaPorCodigo(int codigo) {
        for (TarjetaPropiedad t : listaTarjetasPropiedad) {
            if (t.getCodigo()== codigo) {
                return t;
            }
        }
        return null;
    }

    // Limpiar campos
    private void limpiarCampos() {
        this.vistaTarjePro.jComb_vehiculo.setSelectedIndex(-1); // dejar sin selección
        this.vistaTarjePro.jComb_vehiculo.setSelectedIndex(-1); // dejar sin selección
        this.vistaTarjePro.jtf_codigo.setText("");
        this.vistaTarjePro.jtf_fecha.setText("");
    }
    
    //Eliminar tarjeta
    public void eliminarTarjetaPorCodigo() {
        try {
            String codigoStr = this.vistaTarjePro.jtf_codigo.getText() ;

            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un codigo.");
            }

            int codigo = Integer.parseInt(codigoStr.trim());

            TarjetaPropiedad tarjeta = buscarTarjetaPorCodigo(codigo);

            if (tarjeta == null) {
                throw new NullPointerException("Tarjeta no encontrada. (NullPointerException)");
            }

            // Obtener vehículo y propietario directamente de la tarjeta
            Vehiculo vehiculo = tarjeta.getVehiculo();
            Propietario propietario = tarjeta.getPropietario();

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Esta seguro que desea eliminar la tarjeta con codigo " + codigo +
                    "?\nTambien se eliminara el vehiculo con placa " + vehiculo.getPlaca() +
                    " y el propietario " + propietario.getNombres() + " " + propietario.getApellidos() + ".",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                listaTarjetasPropiedad.remove(tarjeta);
                listadoAutos.remove(vehiculo);
                listaPropietarios.remove(propietario);
                JOptionPane.showMessageDialog(null, "Tarjeta, vehiculo y propietario eliminados correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion cancelada.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Codigo invalido. Debe ser numerico. (NumberFormatException)");
        } catch (IllegalArgumentException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void agregarTargeta(){
    
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
    
    @Override
    public void actionPerformed(ActionEvent e) {

        //Boton Agregar Tarjeta de Propiedad
        if(e.getSource() == this.vistaTarjePro.jbtn_aceptar){
            agregarTargeta();
        }
        //Boton Listar Tarjetas de Propiedad
        if(e.getSource() == this.vistaTarjePro.jbtn_listar){
            listarTargetaPropietarios();
        }
        
        //Boton Modificar targetas por codigo
        if (e.getSource()== this.vistaTarjePro.jbtn_modificar) {
            modificarTarjetaPorCodigo();
        }
        
        //Boton de consultar tarjeta
        if (e.getSource()== this.vistaTarjePro.jbtn_consultar) {
            consultarTarjetaPorCodigo();
        }
        
        //Boton Eliminar tarjetas de propiedad
        if (e.getSource()== this.vistaTarjePro.jbtn_eliminar) {
            eliminarTarjetaPorCodigo();
            limpiarCampos();
        }
    }
}

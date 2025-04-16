/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import vista.VistaMenuSTY;
import control.*;
import javax.swing.JOptionPane;
import modelo.*;
import vista.VistaPropietario;

/*
 *
 * @author UNIVALLE
 *
 */
public class ControlMenuSTYGui implements ActionListener {
    
    private VistaMenuSTY vistaMenSTY;
    
    //Listas de objetos de las clases modelo
    private List<Vehiculo> listaVehiculos= new ArrayList<>();
    private List<Propietario> listaPropietarios= new ArrayList<>();
    private List<TarjetaPropiedad> listaTarjetasPropiedad= new ArrayList<>();
    
    
    public ControlMenuSTYGui(){
                
        this.vistaMenSTY= new VistaMenuSTY();
        
        this.vistaMenSTY.setVisible(true);
        
        //Vehiculos
        this.vistaMenSTY.jbtn_vehiculo.addActionListener(this);
        this.vistaMenSTY.jbtn_listarVehiculos.addActionListener(this); 
        this.vistaMenSTY.jbtn_consultarVehiculo.addActionListener(this);
        this.vistaMenSTY.jbtn_modificarVehiculo.addActionListener(this);
        this.vistaMenSTY.jbtn_eliminarVehiculo.addActionListener(this);
        
        //Propietarios
        this.vistaMenSTY.jbtn_propietario.addActionListener(this);
        this.vistaMenSTY.jbtn_listarPropietarios.addActionListener(this);
        this.vistaMenSTY.jbtn_consultarPropietario.addActionListener(this);
        this.vistaMenSTY.jbtn_modificarPropietario.addActionListener(this);
        this.vistaMenSTY.jbtn_eliminarPropietario.addActionListener(this);
        
        //Tarjetas
        this.vistaMenSTY.jbtn_tarjetaPropiedad.addActionListener(this);
        this.vistaMenSTY.jbtn_listarTarjetas.addActionListener(this);
        this.vistaMenSTY.jbtn_modificarTarjeta.addActionListener(this);
        this.vistaMenSTY.jbtn_consultarTarjeta.addActionListener(this);
        this.vistaMenSTY.jbtn_elimnarTrajeta.addActionListener(this);
    }
    //Funciones de Vehiculos
    //Modificar vehiculos
    public void modificarVehiculoPorPlaca() {
        String placaMod = JOptionPane.showInputDialog("Ingrese la placa del vehiculo a modificar (formato ABC123):");

        // Validar formato
        if (placaMod == null || !placaMod.matches("^[A-Z]{3}[0-9]{3}$")) {
            JOptionPane.showMessageDialog(null, "Formato de placa no valido.");
            return;
        }

        Vehiculo v = buscarVehiculoPorPlaca(placaMod);

        if (v == null) {
            JOptionPane.showMessageDialog(null, "Vehiculo no encontrado.");
            return;
        }

        // Pedir nuevos datos
        String nuevaMarca = JOptionPane.showInputDialog("Ingrese la nueva marca:", v.getMarca());
        if (nuevaMarca == null || nuevaMarca.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La marca no puede estar vacia.");
            return;
        }

        String nuevoAnhoStr = JOptionPane.showInputDialog("Ingrese el nuevo año de fabricacion:", v.getAnhoFab());
        int nuevoAnho;
        try {
            nuevoAnho = Integer.parseInt(nuevoAnhoStr);
            int anhoMin = 1886;
            int anhoMax = 2026;
            if (nuevoAnho < anhoMin || nuevoAnho > anhoMax) {
                JOptionPane.showMessageDialog(null, "El año debe estar entre 1886 y 2026.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido para el año (NumberFormatException).");
            return;
        }

        // modificar el objeto
        v.setMarca(nuevaMarca);
        v.setAnhoFab(nuevoAnho);

        JOptionPane.showMessageDialog(null, "Vehiculo actualizado correctamente.");
    }
    
    //Consultar un vehiculo
    public void consultarVehiculos(){
        try {
            String placaConsulta = JOptionPane.showInputDialog("Ingrese la placa del vehiculo:");

            // Validar que el usuario no cancelo el input (porque eso tambien da null)
            if (placaConsulta == null || placaConsulta.isEmpty()) {
                throw new NullPointerException("No se ingreso una placa (NullPointerException).");
            }

            Vehiculo vehiculoEncontrado = buscarVehiculoPorPlaca(placaConsulta);

            // Si no lo encuentra
            if (vehiculoEncontrado == null) {
                throw new NullPointerException("Vehiculo no encontrado. (NullPointerException)");
            }

            // Si se encontro, mostramos los datos
            JOptionPane.showMessageDialog(null,
                "Vehiculo encontrado:\nMarca: " + vehiculoEncontrado.getMarca() +
                "\nAño: " + vehiculoEncontrado.getAnhoFab());

        } catch (NullPointerException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }


    //Buscar vehiculos 
    private Vehiculo buscarVehiculoPorPlaca(String placaBuscada) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placaBuscada)) {
                return v;
            }
        }
        return null; // No se encontro el vehiculo
    }
    
    //Eliminar Vehiculos
    public void eliminarVehiculoPorPlaca() {
        String placaEliminar = JOptionPane.showInputDialog("Ingrese la placa del vehiculo a eliminar (formato ABC123):");

        if (placaEliminar == null || !placaEliminar.matches("^[A-Z]{3}[0-9]{3}$")) {
            JOptionPane.showMessageDialog(null, "Formato de placa no valido.");
            return;
        }

        Vehiculo v = buscarVehiculoPorPlaca(placaEliminar);

        if (v == null) {
            JOptionPane.showMessageDialog(null, "Vehiculo no encontrado.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, 
            "¿Esta seguro que desea eliminar el vehiculo con placa " + v.getPlaca() + "?", 
            "Confirmar eliminacion", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            listaVehiculos.remove(v);
            JOptionPane.showMessageDialog(null, "Vehiculo eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Eliminacion cancelada.");
        }
    }

    
    //Listar Vehiculos
    public void listarVehiculos(){  
        String vehiculos="";
        if(this.listaVehiculos.size()>0){                
            for(int i=0; i<this.listaVehiculos.size(); i++){
                vehiculos += "Placa: "+this.listaVehiculos.get(i).getPlaca()+"\n";
                vehiculos += "Marca: "+this.listaVehiculos.get(i).getMarca()+"\n";
                vehiculos += "AñoFab: "+this.listaVehiculos.get(i).getAnhoFab()+"\n";
                vehiculos += "-----------------------------\n";
            }
            JOptionPane.showMessageDialog(vistaMenSTY, vehiculos);
        }else{ 
            System.out.println("No se han ingresado vehiculos");
            JOptionPane.showMessageDialog(vistaMenSTY,"No se han ingresado vehiculos");
        }
    }
    
    //funciones de Propietarios   
    //Listar Propietarios
    public void listarPropietarios(){
        String propietarios="";
        if(this.listaPropietarios.size()>0){                
            for(int i=0; i<this.listaPropietarios.size(); i++){
                propietarios += "DNI: "+this.listaPropietarios.get(i).getDni()+"\n";
                propietarios += "Nobmres: "+this.listaPropietarios.get(i).getNombres()+"\n";
                propietarios += "Apellidos: "+this.listaPropietarios.get(i).getApellidos()+"\n";
                propietarios += "Direccion: "+this.listaPropietarios.get(i).getDireccion()+"\n";
                propietarios += "-----------------------------\n";
            }
            JOptionPane.showMessageDialog(vistaMenSTY, propietarios);
        }else{            
            System.out.println("No se han ingresado Propietarios");
            JOptionPane.showMessageDialog(vistaMenSTY,"\"No se han ingresado Propietarios\"");
        }
    }
    
    //Eliminar propietarios
    public void eliminarPropietarioPorDni() {
        try {
            String dniStr = JOptionPane.showInputDialog("Ingrese el DNI del propietario a eliminar:");

            if (dniStr == null || dniStr.trim().isEmpty()) {
                throw new IllegalArgumentException("No se ingreso un DNI.");
            }

            int dni = Integer.parseInt(dniStr.trim());

            Propietario propietario = buscarPropietarioPorDni(dni);

            // Si no se encuentra
            if (propietario == null) {
                throw new NullPointerException("Propietario no encontrado. (NullPointerException)");
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Esta seguro que desea eliminar al propietario:\n" +
                    propietario.getNombres() + " " + propietario.getApellidos() + " (DNI: " + dni + ")?",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                listaPropietarios.remove(propietario);
                JOptionPane.showMessageDialog(null, "Propietario eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion cancelada.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un numero valido para el DNI. (NumberFormatException)");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    //Buscar propietarios
    private Propietario buscarPropietarioPorDni(int dni){
        for (Propietario p: listaPropietarios) {
            if (p.getDni()==dni) {
                return p;
            }
        }
        return null;
    }
    
    //Consultar propietarios
    public void consultarPropietarios() {
        try {
            String dniStr = JOptionPane.showInputDialog("Ingrese el DNI del propietario:");

            // Validar si se cancelo o se dejo vacio por que esto tambien da null.
            if (dniStr == null || dniStr.trim().isEmpty()) {
                throw new IllegalArgumentException("No se ingreso un DNI.");
            }

            // Convertir a entero (puede lanzar NumberFormatException)
            int dni = Integer.parseInt(dniStr.trim());

            Propietario propietarioEncontrado = buscarPropietarioPorDni(dni);

            // Si no lo encuentra, lanzamos NullPointerException como ejercicio
            if (propietarioEncontrado == null) {
                throw new NullPointerException("Propietario no encontrado. (NullPointerException)");
            }

            // Si se encontro, mostrar datos
            JOptionPane.showMessageDialog(null,
                "Propietario encontrado:\nNombre: " + propietarioEncontrado.getNombres() +
                "\nApellido: " + propietarioEncontrado.getApellidos() +
                "\nDireccion: " + propietarioEncontrado.getDireccion());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "DNI invalido. Debe ser un numero. (NumberFormatException)");
        } catch (IllegalArgumentException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    //Modificar un propietario
    public void modificarPropietarioPorDni() {
        try {
            String dniStr = JOptionPane.showInputDialog("Ingrese el DNI del propietario a modificar:");

            if (dniStr == null || dniStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un DNI.");
            }

            int dni = Integer.parseInt(dniStr.trim());

            Propietario propietario = buscarPropietarioPorDni(dni);

            if (propietario == null) {
                throw new NullPointerException("Propietario no encontrado. (NullPointerException)");
            }

            // Solicitar nuevos datos
            String nuevosNombres = JOptionPane.showInputDialog("Nuevo nombre:", propietario.getNombres());
            if (nuevosNombres == null || nuevosNombres.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacio.");
            }

            String nuevosApellidos = JOptionPane.showInputDialog("Nuevos apellidos:", propietario.getApellidos());
            if (nuevosApellidos == null || nuevosApellidos.trim().isEmpty()) {
                throw new IllegalArgumentException("Los apellidos no pueden estar vacios.");
            }

            String nuevaDireccion = JOptionPane.showInputDialog("Nueva direccion:", propietario.getDireccion());
            if (nuevaDireccion == null || nuevaDireccion.trim().isEmpty()) {
                throw new IllegalArgumentException("La direccion no puede estar vacia.");
            }

            // Modificar datos si todo es valido
            propietario.setNombres(nuevosNombres);
            propietario.setApellidos(nuevosApellidos);
            propietario.setDireccion(nuevaDireccion);

            JOptionPane.showMessageDialog(null, "Propietario modificado correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "DNI invalido. Debe ser numerico. (NumberFormatException)");
        } catch (IllegalArgumentException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
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
            JOptionPane.showMessageDialog(vistaMenSTY, tarjetasPro);
        }else{            
            System.out.println("No se han registrado tarjetas de propiedad");
            JOptionPane.showMessageDialog(vistaMenSTY,"No se han registrado tarjetas de propiedad");
        }

    }
    
        
    //Modificar tarjetas
    public void modificarTarjetaPorCodigo() {
        try {
            String codigoStr = JOptionPane.showInputDialog("Ingrese el codigo de la tarjeta de propiedad a modificar:");

            if (codigoStr == null || codigoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un codigo.");
            }

            int codigo = Integer.parseInt(codigoStr.trim());
            TarjetaPropiedad tarjeta = buscarTarjetaPorCodigo(codigo);

            if (tarjeta == null) {
                throw new NullPointerException("Tarjeta no encontrada. (NullPointerException)");
            }

            String nuevaFecha = JOptionPane.showInputDialog("Ingrese nueva fecha (dd/mm/aaaa):", tarjeta.getFechaExp());

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
            String codigoStr = JOptionPane.showInputDialog("Ingrese el codigo de la tarjeta de propiedad:");

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

    
    //Eliminar tarjeta
public void eliminarTarjetaPorCodigo() {
    try {
        String codigoStr = JOptionPane.showInputDialog("Ingrese el codigo de la tarjeta de propiedad a eliminar:");

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
            listaVehiculos.remove(vehiculo);
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




    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Boton Ingresar Vehiculo
        if(e.getSource() == this.vistaMenSTY.jbtn_vehiculo){
            ControlVehiculoGui unControlVehiGui= new ControlVehiculoGui(this.listaVehiculos);            
        }
        
        //Boton Listar Vehiculos
        if(e.getSource() == this.vistaMenSTY.jbtn_listarVehiculos){
            listarVehiculos();
        }
        
        //Boton consultar un vehiculo
        if (e.getSource() == this.vistaMenSTY.jbtn_consultarVehiculo) {
            consultarVehiculos();
        }
        
        //Modificar Vehiculos
        if (e.getSource()== this.vistaMenSTY.jbtn_modificarVehiculo) {
            modificarVehiculoPorPlaca();
        }
        
        //Boton Elininar Vehiculos
        if (e.getSource() == this.vistaMenSTY.jbtn_eliminarVehiculo) {
            eliminarVehiculoPorPlaca();
        }

        //Boton Ingresar Propietario
        if(e.getSource() == this.vistaMenSTY.jbtn_propietario){
            ControlPropietarioGui unControlPropietarioGui= new ControlPropietarioGui(this.listaPropietarios);
        }
        
        //Boton Listar Propietarios
        if(e.getSource() == this.vistaMenSTY.jbtn_listarPropietarios){
            listarPropietarios();
        }
        
        //Boton Consultar Propietario
        if (e.getSource()== this.vistaMenSTY.jbtn_consultarPropietario) {
            consultarPropietarios();
        }
        
        //Boton Modificar propietarios
        if (e.getSource()== this.vistaMenSTY.jbtn_modificarPropietario) {
            modificarPropietarioPorDni();
        }
        
        //Boton de Eliminar Propietarios
        if (e.getSource() == this.vistaMenSTY.jbtn_eliminarPropietario) {
            eliminarPropietarioPorDni();
        }

  
        //Boton Ingresar Tarjeta Propiedad
        if(e.getSource() == this.vistaMenSTY.jbtn_tarjetaPropiedad){
            ControlTarjetaPropiedadGui unControlPropietarioGui= new ControlTarjetaPropiedadGui(this.listaVehiculos, this.listaPropietarios, this.listaTarjetasPropiedad);
        }
        
        //Boton Listar Tarjetas de Propiedad
        if(e.getSource() == this.vistaMenSTY.jbtn_listarTarjetas){
            listarTargetaPropietarios();
        }
        
        //Boton Modificar targetas por codigo
        if (e.getSource()== this.vistaMenSTY.jbtn_modificarTarjeta) {
            modificarTarjetaPorCodigo();
        }
        
        //Boton de consultar tarjeta
        if (e.getSource()== this.vistaMenSTY.jbtn_consultarTarjeta) {
            consultarTarjetaPorCodigo();
        }
        
        //Boton Eliminar tarjetas de propiedad
        if (e.getSource()== this.vistaMenSTY.jbtn_elimnarTrajeta) {
            eliminarTarjetaPorCodigo();
        }

    }
    
}

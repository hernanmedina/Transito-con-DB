/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

import modelo.Propietario;
import vista.VistaPropietario;



/**
 *
 * @author UNIVALLE
 */
public class ControlPropietarioGui implements ActionListener{

    private VistaPropietario vistaProp;
    private Propietario unPropietario;    
    private List<Propietario> listaPropietarios;
    
    public ControlPropietarioGui(List<Propietario> listaPropitars){
        
        this.vistaProp= new VistaPropietario();
        this.vistaProp.setVisible(true);
        this.vistaProp.jbtn_aceptar.addActionListener(this);
        this.vistaProp.jbtn_Atualizar.addActionListener(this);
        this.vistaProp.jbtn_Eliminar.addActionListener(this);
        this.vistaProp.jbtn_Consultar.addActionListener(this);
        this.vistaProp.jbtn_Listar.addActionListener(this);
        this.listaPropietarios= listaPropitars; // LISTA COMPARTIDA
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
            JOptionPane.showMessageDialog(null, propietarios);
        }else{            
            System.out.println("No se han ingresado Propietarios");
            JOptionPane.showMessageDialog(null,"\"No se han ingresado Propietarios\"");
        }
    }
    
     // Limpiar campos
    private void limpiarCampos() {
        this.vistaProp.jtf_apellidos.setText("");
        this.vistaProp.jtf_nombres.setText("");
        this.vistaProp.jtf_direccion.setText("");
        this.vistaProp.jtf_dni.setText("");
    }
    
    //Eliminar propietarios
    public void eliminarPropietarioPorDni() {
        try {
            String dniStr = this.vistaProp.jtf_dni.getText() ;

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
            String dniStr = this.vistaProp.jtf_dni.getText() ;

            // Validar si se cancelo o se dejo vacio por que esto tambien da null.
            if (dniStr == null || dniStr.trim().isEmpty()) {
                throw new IllegalArgumentException("No se ingreso un DNI.");
            }

            // Convertir a entero (puede lanzar NumberFormatException)
            int dni = Integer.parseInt(dniStr.trim());

            Propietario propietarioEncontrado = buscarPropietarioPorDni(dni);

            // Si no lo encuentra, lanzamos NullPointerException 
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
    
    // Validar si el propietario existe
    private boolean existePropietarioPorDni(int dni) {
        for (Propietario p : listaPropietarios) {
            if (p.getDni() == dni) {
                return true; 
            }
        }
        return false; 
    }
    
    //Modificar un propietario
    public void modificarPropietarioPorDni() {
        try {
            String dniStr = this.vistaProp.jtf_dni.getText();

            if (dniStr == null || dniStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Debe ingresar un DNI.");
            }

            int dni = Integer.parseInt(dniStr.trim());

            Propietario propietario = buscarPropietarioPorDni(dni);

            if (propietario == null ) {
                throw new NullPointerException("Propietario no encontrado. (NullPointerException)");
            }

            // Confirmar modificacion
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro que desea modificar al propietario con DNI " + propietario.getDni() + "?",
                    "Confirmar modificación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                // Solicitar nuevos datos
                String nuevosNombres = this.vistaProp.jtf_nombres.getText();
                if (nuevosNombres == null || nuevosNombres.trim().isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }

                String nuevosApellidos = this.vistaProp.jtf_apellidos.getText();
                if (nuevosApellidos == null || nuevosApellidos.trim().isEmpty()) {
                    throw new IllegalArgumentException("Los apellidos no pueden estar vacíos.");
                }

                String nuevaDireccion = this.vistaProp.jtf_direccion.getText();
                if (nuevaDireccion == null || nuevaDireccion.trim().isEmpty()) {
                    throw new IllegalArgumentException("La dirección no puede estar vacía.");
                }

                // Modificar datos
                propietario.setNombres(nuevosNombres);
                propietario.setApellidos(nuevosApellidos);
                propietario.setDireccion(nuevaDireccion);

                JOptionPane.showMessageDialog(null, "Propietario modificado correctamente.");

            } else {
                JOptionPane.showMessageDialog(null, "Modificación cancelada.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "DNI inválido. Debe ser numérico. (NumberFormatException)");
        } catch (IllegalArgumentException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    
    //Agregar Propietario
    public void agregarPropietario(){
         
        boolean datosValidos = true;
        this.unPropietario = new Propietario();
        int dni = Integer.parseInt(this.vistaProp.jtf_dni.getText());

        // Validación de DNI (numérico)
        try {
            if (existePropietarioPorDni(dni)) {
                JOptionPane.showMessageDialog(null, "Ya existe un propietario con ese DNI.");
                return; 
            }
            this.unPropietario.setDni(dni);
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(this.vistaProp, "Ingrese valores numéricos para el DNI.");
            datosValidos = false;
        }

        // Validación de nombres
        String nombres = this.vistaProp.jtf_nombres.getText();
        if (!nombres.trim().isEmpty()) {
            this.unPropietario.setNombres(nombres);
        } else {
            JOptionPane.showMessageDialog(this.vistaProp, "El campo 'Nombres' no puede estar vacio.");
            datosValidos = false;
        }

        // Validación de apellidos
        String apellidos = this.vistaProp.jtf_apellidos.getText();
        if (!apellidos.trim().isEmpty()) {
            this.unPropietario.setApellidos(apellidos);
        } else {
            JOptionPane.showMessageDialog(this.vistaProp, "El campo 'Apellidos' no puede estar vacio.");
            datosValidos = false;
        }

        // Validación de dirección
        String direccion = this.vistaProp.jtf_direccion.getText();
        if (!direccion.trim().isEmpty()) {
            this.unPropietario.setDireccion(direccion);
        } else {
            JOptionPane.showMessageDialog(this.vistaProp, "El campo 'Dirección' no puede estar vacio.");
            datosValidos = false;
        }

        // Agregar propietario solo si todo es válido
        if (datosValidos) {
            this.listaPropietarios.add(unPropietario);
            JOptionPane.showMessageDialog(vistaProp, "Propietario creado correctamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.vistaProp.jbtn_aceptar) {
            agregarPropietario();
            //Actualizar Propietarios
            if (e.getSource()== this.vistaProp.jbtn_Consultar) {
                consultarPropietarios();
            }
        }
       
        if (e.getSource()== this.vistaProp.jbtn_Atualizar) {
            modificarPropietarioPorDni();
        }
        
        if (e.getSource() == this.vistaProp.jbtn_Consultar) {
            consultarPropietarios();
        }
        
        if (e.getSource() == this.vistaProp.jbtn_Eliminar) {
            eliminarPropietarioPorDni();
            limpiarCampos();
           
        }
        
        if (e.getSource() == this.vistaProp.jbtn_Listar) {
            listarPropietarios();
        }
    }

    
}

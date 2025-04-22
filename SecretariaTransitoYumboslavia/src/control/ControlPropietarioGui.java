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
        this.listaPropietarios= listaPropitars; // LISTA COMPARTIDA
    }
    
   @Override
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == this.vistaProp.jbtn_aceptar) {
        boolean datosValidos = true;
        this.unPropietario = new Propietario();

        // Validación de DNI (numérico)
        try {
            this.unPropietario.setDni(Integer.parseInt(this.vistaProp.jtf_dni.getText()));
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
}

    
}

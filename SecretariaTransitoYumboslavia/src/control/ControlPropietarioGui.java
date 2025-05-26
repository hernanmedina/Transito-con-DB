/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import modelo.Propietario;
import modelo.PropietarioDAO;
import vista.VistaPropietario;

/**
 *
 * @author UNIVALLE
 */
public class ControlPropietarioGui implements ActionListener{

    private VistaPropietario vistaPropietario;
    private Propietario unPropietario; 
    private PropietarioDAO unPropietarioDAO;
   
    
    public ControlPropietarioGui(){
        this.unPropietarioDAO = new PropietarioDAO();
        this.unPropietario = new Propietario();
        
        this.vistaPropietario= new VistaPropietario();
        this.vistaPropietario.setVisible(true);
        
        this.vistaPropietario.jbtn_aceptar.addActionListener(this);
        this.vistaPropietario.jbtn_Atualizar.addActionListener(this);
        this.vistaPropietario.jbtn_Eliminar.addActionListener(this);
        this.vistaPropietario.jbtn_Consultar.addActionListener(this);
        this.vistaPropietario.jbtn_Listar.addActionListener(this);
       
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton agregar
        if (e.getSource() == this.vistaPropietario.jbtn_aceptar) {
            String textoDni = vistaPropietario.jtf_dni.getText();
           
            try {
                if (textoDni.isEmpty() || !textoDni.matches("^[0-9]+$")) {
                    JOptionPane.showMessageDialog(null, "El DNI solo debe tener formato de números y no estar vacío.");
                    return;
                }
                
                this.unPropietario.setDni(Integer.parseInt(this.vistaPropietario.jtf_dni.getText()));
                this.unPropietario.setNombres(this.vistaPropietario.jtf_nombres.getText());
                this.unPropietario.setApellidos(this.vistaPropietario.jtf_apellidos.getText());
                this.unPropietario.setDireccion(this.vistaPropietario.jtf_direccion.getText());
 
                if (!this.unPropietario.getNombres().isEmpty() 
                        && !this.unPropietario.getApellidos().isEmpty() && !this.unPropietario.getDireccion().isEmpty()) { 
                    
                    if (this.unPropietarioDAO.ingresarPropietario(unPropietario)){
                        JOptionPane.showMessageDialog(this.vistaPropietario, "Datos ingresados con éxito!!!");
                        limpiarCampos();
                    }
                }else{
                    JOptionPane.showMessageDialog(this.vistaPropietario,"Todos los campos son obligatorios\nY ninguno debe ir en blanco");
                }
            
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(vistaPropietario, "El DNI solo deve terner formato de numeros. "+ex);
                
            }catch(Exception exc){
                JOptionPane.showMessageDialog(vistaPropietario, "El DNI, nombres, apellidos, direccion no deben estar vacíos "+exc); 
            }
        }
       
        //Boton Modificar
        if (e.getSource()== this.vistaPropietario.jbtn_Atualizar) {
            try{
                this.unPropietario.setDni(Integer.parseInt(this.vistaPropietario.jtf_dni.getText()));
                this.unPropietario.setNombres(this.vistaPropietario.jtf_nombres.getText()); 
                this.unPropietario.setApellidos(this.vistaPropietario.jtf_apellidos.getText()); 
                this.unPropietario.setDireccion(this.vistaPropietario.jtf_direccion.getText());

                if(this.unPropietario.getDni()!= 0 && !this.unPropietario.getNombres().isEmpty()
                        && !this.unPropietario.getApellidos().isEmpty()
                        && !this.unPropietario.getNombres().isEmpty() ){
                    
                    if(this.unPropietarioDAO.actualizarPropietario(unPropietario)){
                        JOptionPane.showMessageDialog(this.vistaPropietario, "Datos actualizados con éxito!!!");
                        limpiarCampos();
                    }else{
                        JOptionPane.showMessageDialog(this.vistaPropietario, "Datos no actualizados!!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(this.vistaPropietario,"Todos los campos son obligatorios\nY ninguno debe ir en blanco.");
                }                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vistaPropietario,"El campo DNI es obligatorio\nY deben ser en formato de numeros.");
            }
        }
        
        //Boton Consultar
        if (e.getSource() == this.vistaPropietario.jbtn_Consultar) {
            try{
                this.unPropietario= this.unPropietarioDAO.consultarQuery(Integer.parseInt(this.vistaPropietario.jtf_dni.getText()));                
                this.vistaPropietario.jtf_nombres.setText(this.unPropietario.getNombres());
                this.vistaPropietario.jtf_apellidos.setText(this.unPropietario.getApellidos());
                this.vistaPropietario.jtf_direccion.setText(this.unPropietario.getDireccion()+"");

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vistaPropietario,"El campo DNI es obligatorio\nY deben ser en formato de numero");
            }
        }
        
        //Boton Eliminar
        if (e.getSource() == this.vistaPropietario.jbtn_Eliminar) {
            try{
                int cedula= Integer.parseInt(this.vistaPropietario.jtf_dni.getText());
            
                if(this.unPropietarioDAO.eliminarPropietario(cedula)){
                    JOptionPane.showMessageDialog(this.vistaPropietario, "Datos Eliminados!!!");
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(this.vistaPropietario, "Datos No Eliminados!!!");
                }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this.vistaPropietario,"El campo DNI es obligatorio\nY deben ser en formato de numero.");
            }
        }
        
        //Boton Listar
        if (e.getSource() == this.vistaPropietario.jbtn_Listar) {
           this.unPropietarioDAO.mostrarLista(this.vistaPropietario.jTable_propietarios);
        }
    }
    public void limpiarCampos(){
        this.vistaPropietario.jtf_dni.setText("");
        this.vistaPropietario.jtf_nombres.setText("");
        this.vistaPropietario.jtf_apellidos.setText("");
        this.vistaPropietario.jtf_direccion.setText("");
        
    }

    
}

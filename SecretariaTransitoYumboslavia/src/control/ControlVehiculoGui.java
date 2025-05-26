package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Vehiculo;
import modelo.VehiculoDAO;
import vista.VistaVehiculo;

public class ControlVehiculoGui implements ActionListener{

    private VistaVehiculo vistaVehiculo;
    private VehiculoDAO UnVehiculoDAO;
    private Vehiculo UnVehiculo;

    public ControlVehiculoGui() {
        this.UnVehiculoDAO = new VehiculoDAO();
        this.UnVehiculo = new Vehiculo();

        this.vistaVehiculo = new VistaVehiculo();
        this.vistaVehiculo.setVisible(true);
        
        this.vistaVehiculo.jbtn_aceptar.addActionListener(this);
        this.vistaVehiculo.jbtn_consultar.addActionListener(this);
        this.vistaVehiculo.jbtn_modificar.addActionListener(this);
        this.vistaVehiculo.jbtn_eliminar.addActionListener(this);
        this.vistaVehiculo.jbtn_listar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //Boton agregar
        if (e.getSource()== this.vistaVehiculo.jbtn_aceptar) {
            try {
                
                this.UnVehiculo.setPlaca(this.vistaVehiculo.jtf_placa.getText());            
                this.UnVehiculo.setMarca(this.vistaVehiculo.jtf_marca.getText());        
                this.UnVehiculo.setAnhoFab(Integer.parseInt(this.vistaVehiculo.jtf_anhoFab.getText())); 
                
                if (!this.UnVehiculo.getPlaca().equals("") && !this.UnVehiculo.getMarca().equals("") && this.UnVehiculo.getAnhoFab() != 0) {
                    
                    if (this.UnVehiculoDAO.insertarVehiculo(UnVehiculo)){
                        JOptionPane.showMessageDialog(this.vistaVehiculo, "Datos ingresados con éxito!!!");
                        limpiarCampos();
                    }
                }else{
                    JOptionPane.showMessageDialog(this.vistaVehiculo,"Todos los campos son obligatorios\nY ninguno debe ir en blanco");
                }
            
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this.vistaVehiculo,"Los campos placa, marca y año de fabricacion son obligatorios");
            }
        }
        
        //Boton consultar
        if (e.getSource()== this.vistaVehiculo.jbtn_consultar) {
            try{
                this.UnVehiculo= this.UnVehiculoDAO.consultarQuery(this.vistaVehiculo.jtf_placa.getText());                
                this.vistaVehiculo.jtf_placa.setText(this.UnVehiculo.getPlaca());
                this.vistaVehiculo.jtf_marca.setText(this.UnVehiculo.getMarca());
                this.vistaVehiculo.jtf_anhoFab.setText(this.UnVehiculo.getAnhoFab()+"");
                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vistaVehiculo,"El campo placa es obligatorio\nY deben ser en formato ABC123");
            }
        }
        
        //Boton Modificar
        if (e.getSource()== this.vistaVehiculo.jbtn_modificar) {
            try{
                this.UnVehiculo.setPlaca(this.vistaVehiculo.jtf_placa.getText());
                this.UnVehiculo.setMarca(this.vistaVehiculo.jtf_marca.getText()); 
                this.UnVehiculo.setAnhoFab(Integer.parseInt(this.vistaVehiculo.jtf_anhoFab.getText()));

                if(!this.UnVehiculo.getPlaca().equals("") && !this.UnVehiculo.getMarca().equals("")&& this.UnVehiculo.getAnhoFab() != 0){
                    if(this.UnVehiculoDAO.actualizarVehiculo(UnVehiculo)){
                        JOptionPane.showMessageDialog(this.vistaVehiculo, "Datos actualizados con éxito!!!");
                        limpiarCampos();
                    }else{
                        JOptionPane.showMessageDialog(this.vistaVehiculo, "Datos no actualizados!!!");
                    }
                }else{
                    JOptionPane.showMessageDialog(this.vistaVehiculo,"Todos los campos son obligatorios\nY ninguno debe ir en blanco");
                }                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this.vistaVehiculo,"El campo placa es obligatorio\nY deben ser en formato ABC123");
            }
        }
        
        //Boton Eliminar
        if(e.getSource() == this.vistaVehiculo.jbtn_eliminar){
            try{
                String placa= this.vistaVehiculo.jtf_placa.getText();
            
                if(this.UnVehiculoDAO.eliminarVehiculo(placa)){
                    JOptionPane.showMessageDialog(this.vistaVehiculo, "Datos Eliminados!!!");
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(this.vistaVehiculo, "Datos No Eliminados!!!");
                }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this.vistaVehiculo,"El campo placa es obligatorio\nY deben ser en formato ABC123");
            }            
        } 
        
        //Boton Listar Todos 
        if(e.getSource() == this.vistaVehiculo.jbtn_listar){
            this.UnVehiculoDAO.mostrarLista(this.vistaVehiculo.jTable_vehiculos);
        }
    }
    
    public void limpiarCampos(){
        this.vistaVehiculo.jtf_placa.setText("");
        this.vistaVehiculo.jtf_marca.setText("");
        this.vistaVehiculo.jtf_anhoFab.setText("");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package modelo;

/**
 *
 * @author UNIVALLE
 */
public class Propietario {

    private int dni;
    private String nombres;
    private String apellidos;
    private String direccion;
    
    public Propietario(){}

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    @Override
    public String toString(){
        return "DNI: "+this.dni+" Nombres: "+this.nombres+" Apellidos: "+this.apellidos+" Dir: "+this.direccion;
    }
}

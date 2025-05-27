/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UNIVALLE
 */
public class TarjetaPropiedad {
    
    private Propietario propietario;
    private Vehiculo vehiculo;
    private int codigo;
    private String fechaExp;
    
    public TarjetaPropiedad(Propietario prop, Vehiculo veh, int codigo1, String fecha){
        this.propietario = prop;
        this.vehiculo = veh;
        this.codigo = codigo1;
        this.fechaExp = fecha;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(String fechaExp) {
        this.fechaExp = fechaExp;
    }
    
    
    
}

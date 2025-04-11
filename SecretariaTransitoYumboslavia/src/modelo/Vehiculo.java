/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package modelo;

/**
 *
 * @author UNIVALLE
 */
public class Vehiculo {

    private String placa;
    private String marca;
    private int anhoFab;
    
    public Vehiculo(){}

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnhoFab() {
        return anhoFab;
    }

    public void setAnhoFab(int anhoFab) {
        this.anhoFab = anhoFab;
    }
    
    @Override
    public String toString(){
        return "Placa: "+this.placa+" Marca: "+this.marca+" AñoFab: "+this.anhoFab;
    }
    
}

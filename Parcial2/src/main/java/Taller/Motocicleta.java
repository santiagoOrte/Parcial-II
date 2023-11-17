/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Taller;

/**
 *
 * @author IngSis
 */
public class Motocicleta extends Vehiculo {
    private int cilindrada;

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
    

    public Motocicleta(String marca, String modelo, String placa,int cilindrada,int horaEntrada) {
        super(marca, modelo, placa, horaEntrada);
        this.cilindrada = cilindrada;
    }
    
}
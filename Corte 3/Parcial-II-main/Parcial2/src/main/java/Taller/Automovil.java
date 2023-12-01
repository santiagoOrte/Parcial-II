/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Taller;


/**
 *@santiago vidal martinez
 *@David Santiago Ortega Albán
 */
public class Automovil extends Vehiculo{
    private int numeroPuertas;

    public Automovil(int numeroPuertas, String marca, String modelo, String placa, int horaEntrada) {
        super(marca, modelo, placa,horaEntrada);
        this.numeroPuertas = numeroPuertas;
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
}

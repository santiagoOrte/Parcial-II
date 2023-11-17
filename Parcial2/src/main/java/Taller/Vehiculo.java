package Taller;

/**
 *
 * @author IngSis
 */
public class Vehiculo {
    private String marca;
    private String modelo;
    private String placa;
    private int horaSalida;
    private int horaEntrada;

    // Constructores, getters y setters

    // Otros métodos específicos si es necesario

    public Vehiculo(String marca, String modelo, String placa, int horaEntrada) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        horaSalida = 0;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(int horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    
    
}
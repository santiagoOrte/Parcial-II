package Taller;

import static spark.Spark.*;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.LinkedList;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
/**
 *@David Santiago Ortega Albán
 */
public class main {

    public static void main(String[] args) {
       
        Gson gson = new Gson();
        LinkedList<Vehiculo> automoviles = new LinkedList<>();
        LinkedList<Vehiculo> motocicletas = new LinkedList<>();
        LocalDateTime hora = LocalDateTime.now();
        if (leerArchivo("parqueadero motos.txt") != null) {
            motocicletas.addAll(leerArchivo("parqueadero motos.txt"));
        }
        if (leerArchivo("parqueadero autos.txt") != null) {
         automoviles.addAll(leerArchivo("parqueadero autos.txt"));    
        }
       
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motocicletas);
        });

        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada", (req, res) -> {
            res.type("application/json");  
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
          
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));
            int horaEntrada = hora.getHour();

            for (Vehiculo vehiculo : motocicletas) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    return gson.toJson("ya existe un veiculo con esa Placa");
                }
            }

            Motocicleta nuevaMoto = new Motocicleta(marca, modelo, placa, cilindrada, horaEntrada);
            motocicletas.add(nuevaMoto);
            return gson.toJson(nuevaMoto);

        });
        
        get("/motosActuales", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> actuales = new LinkedList<>();
            
            for(Vehiculo e : motocicletas ){
                if(e.getHoraSalida() == 0){
                    actuales.add(e);
                }
            }
        return gson.toJson(actuales);
        });
        
        //*******
        
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {
            res.type("application/json");

            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
         
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));
            int horaEntrada =  hora.getHour();

            for (Vehiculo vehiculo : automoviles) {
                if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                    return gson.toJson("ya existe un veiculo con esa Placa");
                }
            }
            
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo, placa, horaEntrada);
            automoviles.add(nuevoAuto);
            return gson.toJson(nuevoAuto);
            
        });
        
        get("/automovilesAcuales", (req, res) -> {
            res.type("application/json");
            LinkedList<Vehiculo> actuales = new LinkedList<>();

            for (Vehiculo e : automoviles) {
                if (e.getHoraSalida() == 0) {
                    actuales.add(e);
                }
            }
            return gson.toJson(actuales);
        });
        
        
        
        get("/sacarVeiculo/:placa", (req, res) -> {
            res.type("application/json");
            
            String placa = req.params(":placa");
            int horaSalida =  hora.getHour();
            
            boolean flag=false;
            for(Vehiculo m : motocicletas){
                if (m.getPlaca().equals(placa)) {
                    m.setHoraSalida(horaSalida);
                    flag = true;
                    return gson.toJson(m);
                }
            }
            if (flag==false) {
                for(Vehiculo a : automoviles){
                if (a.getPlaca().equals(placa)) {
                    a.setHoraSalida(horaSalida);
                    flag = true;
                    return gson.toJson(a);
                }
            }
            }
            return null;
        });
        

        
        
        
        get("/motosReporte", (req, res) -> {
            res.type("application/json");
            int a = 0;
            int Ganancias = 0;
            for(Vehiculo e : motocicletas){
                if (e.getHoraSalida() != 0) {
                    Ganancias += e.CalcularCosto(5000);
                    a++;
                }
            }
            guardarArchivo("parqueadero motos.txt", motocicletas);
            return gson.toJson("Total de motos Retiradas:"+ a 
                    + "|||  Ganancias del Dia:"+ Ganancias); 
        });
        
        get("/AutomovilesReporte", (req, res) -> {
            res.type("application/json");
            int a = 0;
            int Ganancias = 0;
            for(Vehiculo e : automoviles){
                if (e.getHoraSalida() != 0) {
                    Ganancias += e.CalcularCosto(10000);
                    a++;
                }
            }
            guardarArchivo("parqueadero autos.txt", automoviles);
            return gson.toJson("Total de Autos Retirados:"+ a 
                    + "|||   Ganancias Del dia:"+ Ganancias); 
        });
        
    }
    
    public static LinkedList<Vehiculo> leerArchivo (String nombreArchivo){
        LinkedList<Vehiculo> listaLeida = null;
        try {
            FileInputStream parqueadero = new FileInputStream(nombreArchivo);
            ObjectInputStream entrada= new ObjectInputStream(parqueadero);
            
            listaLeida = (LinkedList<Vehiculo>)entrada.readObject();
            
            entrada.close();
            parqueadero.close();
        } catch (Exception e) {
            e.getMessage();
        }
    return listaLeida;
    }
    
    public static void guardarArchivo (String nombreArchivo, LinkedList<Vehiculo> listaGuardar){
        try {
            FileOutputStream parqueadero = new FileOutputStream(nombreArchivo);
            ObjectOutputStream entrada= new ObjectOutputStream(parqueadero);
            
            entrada.writeObject(listaGuardar);
            
            entrada.close();
            parqueadero.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}

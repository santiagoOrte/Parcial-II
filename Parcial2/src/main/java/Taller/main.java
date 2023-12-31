package Taller;

import static spark.Spark.*;
import com.google.gson.Gson;

import java.util.LinkedList;

/**
 *@santiago vidal martinez
 *@David Santiago Ortega Albán
 */
public class Main {

    public static void main(String[] args) {
        
        /*
        * Esto nos sirve para generar un formato json para retornar la data del array
        * sin tener que acomodarla bonita de manera manual
        */
        Gson gson = new Gson();
        
        LinkedList <Vehiculo> automoviles = new LinkedList<>();
        
        // Automovil creado por defecto
        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987",1);
        automoviles.add(auto);
        
        LinkedList <Vehiculo> motocicletas = new LinkedList<>();
        // moto creado por defecto
        Motocicleta moto = new Motocicleta("Honda", "CBR600", "XYZ789",600,12);
        motocicletas.add(moto);
        
        
        // Definir endpoints
        
        // lista de motos
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motocicletas);
        });
        // Guardar motocicleta
        // endpoint GET para agregar una moto
        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada/:horaEntrada", (req, res) -> {
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            
            // No olvidar convertir en integer los string numericos que llegan por url
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));
            int horaEntrada = Integer.parseInt(req.params(":horaEntrada"));
            // Crear un nuevo automóvil y agregarlo al parqueadero
            Motocicleta nuevaMoto = new Motocicleta( marca, modelo, placa, cilindrada,horaEntrada);
            motocicletas.add(nuevaMoto);

            return gson.toJson(nuevaMoto);
        });
        
        
        // Listado de automoviles
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        
        // Guardar automovil
        // endpoint GET para agregar un automóvil
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas/:horaEntrada", (req, res) -> {
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            
            // No olvidar convertir en integer los string numericos que llegan por url
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));
            int horaEntrada = Integer.parseInt(req.params(":horaEntrada"));
            // Crear un nuevo automóvil y agregarlo al parqueadero
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo,placa,horaEntrada);
            automoviles.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });
        //retirar un veiculo
        get("/sacarVeiculo/:placa/:hora", (req, res) -> {
            
            // Añadimos esto para retornar en formato Json
            res.type("application/json");
            
            String placa = req.params(":placa");
            int horaSalida = Integer.parseInt(req.params(":hora"));
            
            // buscaos el vaiculo por placa
            boolean flag=false;
            for(Vehiculo m : motocicletas){
                if (m.getPlaca() == null ? placa == null : m.getPlaca().equals(placa)) {
                    m.setHoraSalida(horaSalida);
                    flag = true;
                    return gson.toJson("Moto retirado");
                }
            }
            if (flag==false) {
                for(Vehiculo a : automoviles){
                if (a.getPlaca() == null ? placa == null : a.getPlaca().equals(placa)) {
                    a.setHoraSalida(horaSalida);
                    motocicletas.remove(a);
                    flag = true;
                    return gson.toJson("Auto retirado");
                }
            }
            }
            return null;
        });
        
        // Listado de automovileActuales
        get("/automovilesAcuales", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> actuales = new LinkedList<>();
            res.type("application/json");
            for(Vehiculo e : automoviles ){
                if(e.getHoraSalida() == 0){
                actuales.add(e);
                }
            }
        return gson.toJson(actuales);
        });
        // lista de motos acuales
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
        
        //reporte de motos
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
            return gson.toJson("Total de motos Retiradas:"+ a 
                    + "|||  Ganancias del Dia:"+ Ganancias); 
        });
        //reporte de autos
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
            return gson.toJson("Total de Autos Retirados:"+ a 
                    + "|||   Ganancias del Dia:"+ Ganancias); 
        });
        
    }
}

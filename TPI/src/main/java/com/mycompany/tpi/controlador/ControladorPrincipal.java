package com.mycompany.tpi.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.tpi.Modelos.Carrera;
import com.mycompany.tpi.Modelos.Competidor;
import com.mycompany.tpi.Modelos.Juez;

import com.mycompany.tpi.Modelos.Resultado;
import com.mycompany.tpi.vista.VistaPrincipal;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
=======
import java.io.File;
>>>>>>> 6aaf8cadeac30df596e6ae432d9f4168a675906b
import java.util.*;

public class ControladorPrincipal {

    private VistaPrincipal vista = new VistaPrincipal();
    private List<Competidor> competidores = new ArrayList<>();
    private List<Juez> jueces = new ArrayList<>();
    private List<Carrera> carreras = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
<<<<<<< HEAD

=======
    private ObjectMapper mapper = new ObjectMapper();
    private String urlCompetidores="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\competidores.json";
    private String urlJueces="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\jueces.json";
    private String urlCarreras="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\carreras.json";
    private String urlResultados="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\resultados.json";
    
    
    
>>>>>>> 6aaf8cadeac30df596e6ae432d9f4168a675906b
    public void menu() {
        int opcion = 0;
        do {
            opcion = vista.menu();

            switch (opcion) {

                case 1 ->
                    registrarCompetidor();
                case 2 ->
                    registrarJuez();
                case 3 ->
                    registrarCarrera();
                case 4 ->
                    registrarTiempoCompetidor();
                case 5 ->
                    rankingCategoria();
                case 6 ->
                    rankingGeneral();
                case 7 ->
                    listaAbandonos();
                case 8 ->
                    infoCompetencia();
                case 9 ->
                    infoCompetidor();

            }
        } while (opcion != 0);
    }
<<<<<<< HEAD

    public void baseDatos(){
        try {
            // Cargamos el driver y conectamos con la base de dato invetario
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_competencia", "root", "Caramelo24:)");
            Statement stmt = con.createStatement();
        }catch (Exception e) {
            vista.mensaje("Error de lectura" + e.getMessage());
        }
    }
    
    public void registrarCompetidor() {
        int idCompetidor = Integer.parseInt(vista.pedirDato("Ingrese ID Competidor: "));
        int faltas = Integer.parseInt(vista.pedirDato("Ingrese numero de faltas: "));
        int numeroCorredor = Integer.parseInt(vista.pedirDato("Ingrese numero de competidor: "));
        String nombre = vista.pedirDato("Ingrese nombre: ");
        String apellido = vista.pedirDato("Ingrese apellido: ");
        String mail = vista.pedirDato("Ingrese mail: ");
        String categoria = vista.pedirDato("Ingrese categoria (A-B-C): ");
        Competidor c = new Competidor(idCompetidor, faltas, numeroCorredor, nombre, apellido, mail, categoria);
        competidores.add(c);
        vista.mensaje("Competidor registrado");
    }

    public void registrarJuez() {
        int idJuez = Integer.parseInt(vista.pedirDato("Ingrese numero de juez: "));
        String nombre = vista.pedirDato("Ingrese nombre: ");
        String apellido = vista.pedirDato("Ingrese apellido: ");
        String mail = vista.pedirDato("Ingrese mail: ");
        String categoria = vista.pedirDato("Ingrese categoria (A-B-C): ");
        Juez j = new Juez(idJuez, nombre, apellido, mail, categoria);
        jueces.add(j);
        vista.mensaje("Juez registrado");
    }

    public void registrarCarrera() {
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese ID Carrera: "));
        String categoria = vista.pedirDato("Ingrese categoria de carrera: ");
        String horaInicio = vista.pedirDato("Ingrese hora de inicio: ");
        String horaFin = vista.pedirDato("Ingrese hora fin: ");
        String ubicacion = vista.pedirDato("Ingrese ubicacion: ");
        String detalle = vista.pedirDato("Ingrese detalle: ");
        Carrera ca = new Carrera(idCarrera, categoria, horaInicio, horaFin, ubicacion, detalle);
        carreras.add(ca);
        vista.mensaje("Carrera registrada");
    }

    public void registrarTiempoCompetidor() {
        int idCompetidor = Integer.parseInt(vista.pedirDato("Ingrese ID Competidor: "));
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese ID Carrera: "));
        String tiempo = vista.pedirDato("Ingrese el tiempo de la carrera: ");
        String estado = vista.pedirDato("Ingrese estado carrera: ");
        Resultado r = new Resultado(idCompetidor, idCarrera, tiempo, estado);
        resultados.add(r);
    }

    public void rankingCategoria() {
        int idCategoria = Integer.parseInt(vista.pedirDato("Ingrese categoria carrera: "));
        //Carrera carrerasOrdenadas = new Arraylist

    }

    public void rankingGeneral() {
    }

    public void listaAbandonos() {
    }

    public void infoCompetencia() {
    }

    public void infoCompetidor() {
    }

    public void cargarDatos() {
        // conexion a bd y cargar datos en listas
    }

    public void guardarDatos() {
        // conexion a bd y guardar datos en cada tabla
=======
    
    public void registrarCompetidor(){}
    public void registrarJuez(){}
    public void registrarCarrera(){}
    public void registrarTiempoCompetidor(){}
    public void rankingCategoria(){}
    public void rankingGeneral(){}
    public void listaAbandonos(){}
    public void infoCompetencia(){}
    public void infoCompetidor(){}
    
    public void cargarDatos(){
       try {
            competidores = mapper.readValue(new File(urlCompetidores), new TypeReference<>() {
            });
            jueces = mapper.readValue(new File(urlJueces), new TypeReference<>() {
            });
            carreras = mapper.readValue(new File(urlCarreras), new TypeReference<>() {
            });
            resultados = mapper.readValue(new File(urlResultados), new TypeReference<>() {
            });
        } catch (Exception e) {
            System.out.println("Error al cargar archivos: " + e.getMessage());
        } // conexion a bd y cargar datos en listas
    }
    
    public void guardarDatos(){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(urlCompetidores), competidores);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(urlJueces), jueces);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(urlCarreras), carreras);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(urlResultados), resultados);
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }// conexion a bd y guardar datos en cada tabla
>>>>>>> 6aaf8cadeac30df596e6ae432d9f4168a675906b
    }
}



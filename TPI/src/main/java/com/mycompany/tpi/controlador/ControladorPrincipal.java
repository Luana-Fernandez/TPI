package com.mycompany.tpi.controlador;

import com.mycompany.tpi.Modelos.Carrera;
import com.mycompany.tpi.Modelos.CarreraDAO;
import com.mycompany.tpi.Modelos.Competidor;
import com.mycompany.tpi.Modelos.CompetidorDAO;
import com.mycompany.tpi.Modelos.Juez;
import com.mycompany.tpi.Modelos.JuezDAO;

import com.mycompany.tpi.Modelos.Resultado;
import com.mycompany.tpi.Modelos.ResultadoDAO;
import com.mycompany.tpi.vista.VistaPrincipal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ControladorPrincipal {

    private VistaPrincipal vista = new VistaPrincipal();
    private List<Competidor> competidores = new ArrayList<>();
    private List<Juez> jueces = new ArrayList<>();
    private List<Carrera> carreras = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
    private Connection con;

    public void menu() {
        baseDatos(); //Establecemos la conexión antes de usarla
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
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            vista.mensaje("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public void baseDatos() {
        try {
            // Cargamos el driver y conectamos con la base de dato
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_competencia", "root", "Caramelo24:)");
            //Statement stmt = con.createStatement(); 
            //No lo estamos usando xq vamos a usar PreparedStatement
        } catch (Exception e) {
            vista.mensaje("Error de lectura" + e.getMessage());
        }
    } 

    public void registrarCompetidor() {
        //nombre,apellido,mail,telefono
        String nombre = vista.pedirDato("Ingrese nombre: ");
        String apellido = vista.pedirDato("Ingrese apellido: ");
        String telefono = vista.pedirDato("Ingrese telefono: ");
        String mail = vista.pedirDato("Ingrese mail: ");
        // Creamos el competidor
        Competidor c = new Competidor(nombre, apellido, mail, telefono);
        competidores.add(c);
        // Usamos el DAO
        CompetidorDAO dao = new CompetidorDAO(con);
        //try-catch es obligatorio en estos casos con mysql
        try {
            dao.insertarCompetidor(c);
        } catch (SQLException e) {
            vista.mensaje("Error al insertar competidor: " + e.getMessage());
        }
    }

    public void registrarJuez() {
        String nombre = vista.pedirDato("Ingrese nombre: ");
        String apellido = vista.pedirDato("Ingrese apellido: ");
        String mail = vista.pedirDato("Ingrese mail: ");
        String telefono = vista.pedirDato("Ingrese telefono: ");
        Juez j = new Juez(nombre, apellido, mail, telefono);
        jueces.add(j);
        JuezDAO dao = new JuezDAO(con);
        try {
            dao.insertarJuez(j);
        } catch (SQLException e) {
            vista.mensaje("Error al insertar juez: " + e.getMessage());
        }
    }

    public void registrarCarrera() {
        String categoria = vista.pedirDato("Ingrese categoria de carrera: ");
        String horaInicio = vista.pedirDato("Ingrese hora de inicio: ");
        String horaFin = vista.pedirDato("Ingrese hora fin: ");
        String ubicacion = vista.pedirDato("Ingrese ubicacion: ");
        String detalle = vista.pedirDato("Ingrese detalle: ");
        int idJuez = Integer.parseInt(vista.pedirDato("Ingrese ID Juez: "));
        Carrera ca = new Carrera(categoria, horaInicio, horaFin, ubicacion, detalle, idJuez);
        carreras.add(ca);
        CarreraDAO dao = new CarreraDAO(con);
        try {
            dao.insertarCarrera(ca);
        } catch (SQLException e) {
            vista.mensaje("Error al insertar carrera: " + e.getMessage());
        }
    }

    public void registrarTiempoCompetidor() {
        int idCompetidor = Integer.parseInt(vista.pedirDato("Ingrese ID Competidor: "));
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese ID Carrera: "));
        String tiempo = vista.pedirDato("Ingrese el tiempo competidor: ");
        String estado = vista.pedirDato("Ingrese estado carrera: ");
        int numCorredor = Integer.parseInt(vista.pedirDato("Ingrese numero de corredor: "));
        int faltas = Integer.parseInt(vista.pedirDato("Ingrese numero de faltas: "));
        Resultado r = new Resultado(idCompetidor, idCarrera, tiempo, estado, numCorredor, faltas);
        resultados.add(r);
        ResultadoDAO dao = new ResultadoDAO(con);
        dao.insertarResultado(r);
        vista.mensaje("Registro exitoso");
    }

    public void rankingCategoria() {


    }

    public void rankingGeneral() {
    }

    public void listaAbandonos() {
         List<Resultado> abandonos = new ArrayList<>();

    for (Resultado r : resultados) {
        if ("abandono".equalsIgnoreCase(r.getEstado())) {
            abandonos.add(r);
            }

        }
    
    if (abandonos.isEmpty()) {
    vista.mensaje("No hay abandonos registrados.");
    }       
    else {
    vista.mensaje("Listado de competidores que abandonaron:");
    for (Resultado r : abandonos) {
        vista.mensaje(r.toString());
        }
    }

    } 

    public void infoCompetencia() {
    }

    public void infoCompetidor() {
    }

    public void cargarDatos() {
        // conexion a bd y cargar datos en listas
    }

    public void guardarDatos() {
    }
    // conexion a bd y guardar datos en cada tabla

}

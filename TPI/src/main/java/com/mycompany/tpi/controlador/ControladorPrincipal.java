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
import java.time.LocalTime;
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
        cargarListas();
        int opcion = 0;
        do {
            opcion = vista.menu();

            switch (opcion) {

                case 1 ->
                    registrarCompetidor(); //listo
                case 2 ->
                    registrarJuez();    // listo
                case 3 ->
                    registrarCarrera(); //listo
                case 4 ->
                    registrarCompetidorEnCarrera();
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_competencia", "root", "33411494");
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
            int id = dao.insertarCompetidor(c);
            c.setIdPersona(id);
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
            j.setIdPersona(dao.insertarJuez(j));
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
            ca.setIdCarrera(dao.insertarCarrera(ca));
        } catch (SQLException e) {
            vista.mensaje("Error al insertar carrera: " + e.getMessage());
        }
    }

    public void registrarCompetidorEnCarrera() {
        int idCompetidor = Integer.parseInt(vista.pedirDato("Ingrese ID Competidor: "));
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese ID Carrera: "));
        String tiempo = vista.pedirDato("Ingrese el tiempo competidor: ");
        String estado = vista.pedirDato("Ingrese estado carrera: ");
        int numCorredor = Integer.parseInt(vista.pedirDato("Ingrese numero de corredor: "));
        int faltas = Integer.parseInt(vista.pedirDato("Ingrese numero de faltas: "));
        Resultado r = new Resultado(idCompetidor, idCarrera, tiempo, estado, numCorredor, faltas);
        resultados.add(r);
        ResultadoDAO dao = new ResultadoDAO(con);
        r.setIdResultado(dao.insertarResultado(r));
        vista.mensaje("Registro exitoso");
    }

    public void rankingCategoria() {
        //mapa para agrupar resultados por categoria
        Map<String, List<Resultado>> rankingPorCategoria = new HashMap<>();
        //recorremos todas las carreras
        for (Carrera carrera : carreras) {
            String categoria = carrera.getCategoria();//sacamos la categoria de la carrera
            int idCarrera = carrera.getIdCarrera();//sacamos el id de la carrera
            //se filtra los resultados con tiempo valido
            List<Resultado> resultadosDeCarrera = resultados.stream()
                    .filter(r -> r.getCarrera() == idCarrera & r.getTiempoCompetidor() != null && !r.getTiempoCompetidor().isEmpty())
                    .sorted(Comparator.comparing(r -> LocalTime.parse(r.getTiempoCompetidor())))//ordenamos por tiempo (de menor a mayor )
                    .toList();//resultado a lista
            //resultados agregados al mapa por categoria
            rankingPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).addAll(resultadosDeCarrera);
        }

        if (rankingPorCategoria.isEmpty()) {
            vista.mensaje("No hay datos cargados");
            return;
        }
        //Printeamos el ranking por categoria
        for (Map.Entry<String, List<Resultado>> entry : rankingPorCategoria.entrySet()) {
            vista.mensaje("Categoría: " + entry.getKey());//nombre de la categoria
            int puesto = 1;//contador de puestos
            for (Resultado r : entry.getValue()) {
                //datos de competidor por orden
                vista.mensaje(puesto++ + "° - Competidor ID: " + r.getIdCompetidor() + " | Tiempo: " + r.getTiempoCompetidor());
            }
        }

    }

    public void rankingGeneral() {
        //agrupamos por categoria con el MAP
        Map<String, List<Resultado>> top3PorCategoria = new HashMap<>();
        //recorremos con el for las carreras agrupadas
        for (Carrera carrera : carreras) {
            String categoria = carrera.getCategoria(); //Nombre de carrera
            int idCarrera = carrera.getIdCarrera(); //Id de carrera
            //Filtro de resultados con tiempo valido
            List<Resultado> resultadosDeCarrera = resultados.stream()
                    .filter(r -> r.getCarrera() == idCarrera && r.getTiempoCompetidor() != null && !r.getTiempoCompetidor().isEmpty())
                    .sorted(Comparator.comparing(r -> LocalTime.parse(r.getTiempoCompetidor())))//Ordenamos por tiempo ascendente
                    .limit(3)//dejamos los 3 mejores con menor tiempo
                    .toList();//convertimos el stream a una lista
            //Si hay resultados los agregamos al mapa por categoria
            if (!resultadosDeCarrera.isEmpty()) {
                top3PorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).addAll(resultadosDeCarrera);
            }
        }
        if (top3PorCategoria.isEmpty()) {
            vista.mensaje("No hay datos cargados");
            return;
        }
        //mostramos los 3 mejores resultados por cada categoria
        for (Map.Entry<String, List<Resultado>> entry : top3PorCategoria.entrySet()) {
            vista.mensaje("Categoria: " + entry.getKey()); //nombre de la categoria
            int puesto = 1; //Primer puesto
            for (Resultado r : entry.getValue()) {
                vista.mensaje("° - Competidor ID: " + r.getIdCompetidor() + "| Tiempo: " + r.getTiempoCompetidor());
            }
        }
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
        } else {
            vista.mensaje("Listado de competidores que abandonaron:");
            for (Resultado r : abandonos) {
                vista.mensaje(r.toString());
            }
        }

    }

    public void infoCompetencia() {
        if (carreras.isEmpty()) {
            vista.mensaje("No hay carreras registradas.");
        } else {
            vista.mensaje("Información de todas las carreras registradas:");
            for (Carrera c : carreras) {
                vista.mensaje(c.toString());
            }
        }

    }

    public void infoCompetidor() {
        //verificamos que la lista no este vacia
        if (competidores == null || competidores.isEmpty()) {
            vista.mensaje("No hay competidores registrados.");
            return;
        }
        int idPersonaBuscado;
        try {
            idPersonaBuscado = Integer.parseInt(vista.pedirDato("Ingrese Id del competidor: "));
        } catch (NumberFormatException e) {
            vista.mensaje("El ID no es valido.");
            return;
        }
        //Buscamos al competidor
        Competidor encontrado = null;
        for (Competidor c : competidores) {
            if (c.getIdPersona() == idPersonaBuscado) {
                encontrado = c;
                break;
            }
        }
        //resultados
        if (encontrado == null) {
            vista.mensaje("No se encontro ningun competidor con ID: " + idPersonaBuscado);
            return;
        }
        //Mostramos detalles del competidor
        vista.mensaje("Datos del Competidor");
        vista.mensaje("Nombre: " + encontrado.getNombre());
        vista.mensaje("Apellido: " + encontrado.getApellido());
        vista.mensaje("Email: " + encontrado.getMail());
        vista.mensaje("Telefono: " + encontrado.getTelefono());
    }

    public void cargarListas() {
        try {
            CompetidorDAO competidorDAO = new CompetidorDAO(con);
            competidores = competidorDAO.listaCompetidores();
            CarreraDAO carreraDAO = new CarreraDAO(con);
            carreras = carreraDAO.listaCarreras();
            JuezDAO juezDAO = new JuezDAO(con);
            jueces = juezDAO.listaJueces();
            ResultadoDAO resultadoDAO = new ResultadoDAO(con);
            resultados= resultadoDAO.listaResultados();
            /*
                VER CONTENIDO DE LAS LISTAS
            vista.mensaje(competidores.toString());
            vista.mensaje(carreras.toString());
            vista.mensaje(jueces.toString());
            vista.mensaje(resultados.toString());*/

        } catch (SQLException e) {

            vista.mensaje("error:" + e.getMessage());
        }
    }
}

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
                    registrarCompetidorEnCarrera(); //listo
                case 5 ->
                    rankingCategoria(); // listo
                case 6 ->
                    rankingGeneral();   // listo
                case 7 ->
                    listaAbandonos();   // listo
                case 8 ->
                    infoCompetencia();  // listo
                case 9 ->
                    infoCompetidor();   // listo
                case 10 ->
                    listarJueces();     // listo
                case 11 ->
                    registrarTiempoCorredor();  // listo
                case 12 ->
                    registrarHoraFinCarrera();  // listo
                case 13 ->
                    registrarFaltas();  // listo
                case 14 ->
                    modificarEstado();

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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_competencia", "root", "sou322no");
            //Statement stmt = con.createStatement(); 
            //No lo estamos usando xq vamos a usar PreparedStatement
        } catch (Exception e) {
            vista.mensaje("Error de lectura" + e.getMessage());
        }
    }

    public void registrarCompetidor() {
        //nombre,apellido,mail,telefono
        vista.mensaje("\n--- REGISTRO DE COMPETIDOR ---");
        String nombre = vista.pedirDato("Ingrese nombre: ");
        String apellido = vista.pedirDato("Ingrese apellido: ");
        String telefono = vista.pedirDato("Ingrese telefono: ");
        String mail = vista.pedirDato("Ingrese mail: ");
        int dni = Integer.parseInt(vista.pedirDato("Ingrese DNI: "));
        // Creamos el competidor
        Competidor c = new Competidor(nombre, apellido, mail, telefono, dni);
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

        vista.mensaje("\n--- REGISTRO DE JUEZ ---");
        String nombre = vista.pedirDato("Ingrese nombre: ");
        String apellido = vista.pedirDato("Ingrese apellido: ");
        String mail = vista.pedirDato("Ingrese mail: ");
        String telefono = vista.pedirDato("Ingrese telefono: ");
        int dni = Integer.parseInt(vista.pedirDato("Ingresar DNI: "));
        Juez j = new Juez(nombre, apellido, mail, telefono, dni);
        jueces.add(j);
        JuezDAO dao = new JuezDAO(con);
        try {
            j.setIdPersona(dao.insertarJuez(j));
        } catch (SQLException e) {
            vista.mensaje("Error al insertar juez: " + e.getMessage());
        }
    }

    public void registrarCarrera() {

        vista.mensaje("\n--- REGISTRO DE CARRERA ---");
        String categoria = vista.pedirDato("Ingrese Nombre de carrera: ");
        String horaInicio = vista.pedirDato("Ingrese Dia y Hora de Carrera (dd/mm/aaaa hh:mm): ");
        String horaFin = "";
        String ubicacion = vista.pedirDato("Ingrese ubicacion: ");
        String detalle = vista.pedirDato("Ingrese Comentario: ");
        listarJueces();
        int idJuez = Integer.parseInt(vista.pedirDato("\nIngrese ID Juez: "));
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

        vista.mensaje("\n--- REGISTRO DE COMPETIDOR EN CARRERA ---");
        int idCompetidor = Integer.parseInt(vista.pedirDato("Ingrese ID Competidor: "));
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese ID Carrera: "));
        String tiempo = "--:--:--";
        String estado = "espera";
        int numCorredor = Integer.parseInt(vista.pedirDato("Ingrese numero de corredor: "));
        int faltas = 0;
        Resultado r = new Resultado(idCompetidor, idCarrera, tiempo, estado, numCorredor, faltas);
        resultados.add(r);
        ResultadoDAO dao = new ResultadoDAO(con);
        r.setIdResultado(dao.insertarResultado(r));
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
                    .filter(r -> r.getIdCarrera() == idCarrera & r.getTiempoCompetidor() != null && !r.getTiempoCompetidor().isEmpty())
                    .sorted(Comparator.comparing(Resultado::getTiempoCompetidor))//ordenamos por tiempo (de menor a mayor )
                    .toList();//resultado a lista
            //resultados agregados al mapa por categoria
            if (!resultadosDeCarrera.isEmpty()) {
                rankingPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).addAll(resultadosDeCarrera);
            }
        }
        if (rankingPorCategoria.isEmpty()) {
            vista.mensaje("No hay datos cargados");
            return;
        }
        //Categorias disponibles
        vista.mensaje("\n--- CARRERAS --- ");
        List<String> categorias = new ArrayList<>(rankingPorCategoria.keySet());
        for (int i = 0; i < categorias.size(); i++) {
            vista.mensaje((i + 1) + "." + categorias.get(i));
        }

        vista.mensaje("----------------\n");
        //Seleccion de categoria
        int opcion;
        try {
            opcion = Integer.parseInt(vista.pedirDato("Seleccione el numero de la carrera: ")) - 1;
            if (opcion < 0 || opcion >= categorias.size()) {
                vista.mensaje("Opcion invalida.");
                return;
            }
        } catch (NumberFormatException e) {
            vista.mensaje("Entrada no valida.");
            return;
        }
        String categoriaSeleccionada = categorias.get(opcion);
        vista.mensaje("\n--- RESULTADOS DE CARRERA - " + categoriaSeleccionada + " ---");
        //Mostrar rankind de la categoria
        List<Resultado> resultados = rankingPorCategoria.get(categoriaSeleccionada);
        int puesto = 1;//contador de puestos
        for (Resultado r : resultados) {
            //datos de competidor por orden
            vista.mensaje(puesto++ + " - Competidor ID: " + r.getIdCompetidor() + " | Tiempo: " + r.getTiempoCompetidor());
        }
    }

    public void rankingGeneral() {

        vista.mensaje("\n--- TOP 3 POR CARRERA --- ");
        //agrupamos por categoria con el MAP
        Map<String, List<Resultado>> top3PorCategoria = new HashMap<>();
        //recorremos con el for las carreras agrupadas
        for (Carrera carrera : carreras) {
            String categoria = carrera.getCategoria(); //Nombre de carrera
            int idCarrera = carrera.getIdCarrera(); //Id de carrera
            //Filtro de resultados con tiempo valido
            List<Resultado> resultadosDeCarrera = resultados.stream()
                    .filter(r -> r.getIdCarrera() == idCarrera && r.getTiempoCompetidor() != null && !r.getTiempoCompetidor().isEmpty())
                    .sorted(Comparator.comparing(Resultado::getTiempoCompetidor))//Ordenamos por tiempo ascendente
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
            vista.mensaje("CARRERA: " + entry.getKey()); //nombre de la categoria
            int puesto = 1; //Primer puesto
            for (Resultado r : entry.getValue()) {
                vista.mensaje(puesto + " - Competidor ID: " + r.getIdCompetidor() + "| Tiempo: " + r.getTiempoCompetidor());
                puesto++;
            }
        }
        vista.mensaje("------------------------- ");
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
            vista.mensaje("\nListado de competidores que abandonaron:");
            for (Resultado r : abandonos) {
                vista.mensaje("\nCarrera: " + r.getIdCarrera() + "\nID Competidor: " + r.getIdCompetidor() + "\nTiempo del competidor: " + r.getTiempoCompetidor() + "\nEstado del competidor: " + r.getEstado() + "\nNumero del corredor: " + r.getNumCorredor() + "\nFaltas: " + r.getFaltas());
            }
        }
        //Resultado{idResultado=4, idCompetidor=4, carrera=3, tiempoCompetidor=--:--:--, estado=abandono, numCorredor=36, faltas=1}
    }

    public void listarJueces() {
        vista.mensaje("\n Listado de jueces:");
        for (Juez j : jueces) {
            vista.mensaje(j.toString());
        }
    }

    public void infoCompetencia() {
        if (carreras.isEmpty()) {
            vista.mensaje("No hay carreras registradas.");
        } else {
            vista.mensaje("\nInformacion de todas las carreras registradas:");
            for (Carrera c : carreras) {
                vista.mensaje("\nCarrera: " + c.getIdCarrera() + "\nCategoria: " + c.getCategoria() + "\nHorario de inicio: " + c.getHoraInicio() + "\nHora de finalizacion: " + c.getHoraFin() + "\nUbicacion: " + c.getUbicacion() + "\nDetalles de la carrera: " + c.getDetalle());
            }
        }
    }
    //Carrera{idCarrera=1, categoria=5K, horaInicio=12/05/2025 10:00, horaFin=null, ubicacion=VILLA MARIA - CORDOBA, detalle=nullidJuez=3}

    public void infoCompetidor() {
        //verificamos que la lista no este vacia
        if (competidores == null || competidores.isEmpty()) {
            vista.mensaje("No hay competidores registrados.");
            return;
        }
        int idPersonaBuscado;
        try {
            idPersonaBuscado = Integer.parseInt(vista.pedirDato("\nIngrese Id del competidor: "));
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
        vista.mensaje("Dni: " + encontrado.getDni());
        vista.mensaje("Email: " + encontrado.getMail());
        vista.mensaje("Telefono: " + encontrado.getTelefono());
    }

    public void registrarTiempoCorredor() {
        int dni = Integer.parseInt(vista.pedirDato("ingrese DNI de Corredor: "));
        String tiempoCorredor = vista.pedirDato("Ingresar tiempo de Corredor (formato 00:00:00): ");
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese Id de carrera: "));
        for (Competidor competidor : competidores) {
            if (competidor.getDni() == dni) {
                for (Resultado resultado : resultados) {
                    if (resultado.getIdCompetidor() == competidor.getIdPersona() && resultado.getIdCarrera() == idCarrera) {
                        resultado.setTiempoCompetidor(tiempoCorredor);
                        ResultadoDAO dao = new ResultadoDAO(con);
                        int idCorredor = competidor.getIdPersona();
                        dao.registrarTiempoCorredor(idCorredor, tiempoCorredor, idCarrera);

                    }
                }

            }
        }

    }

    public void registrarHoraFinCarrera() {
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese id de Carrera: "));
        String tiempoCarrera = vista.pedirDato("Ingresar hora de finalizacion de la carrera (formato DD/MM/AAAA 00:00:00): ");
        for (Carrera carrera : carreras) {
            if (carrera.getIdCarrera() == idCarrera) {
                carrera.setHoraFin(tiempoCarrera);
                CarreraDAO dao = new CarreraDAO(con);
                dao.registrarHoraFinCarrera(idCarrera, tiempoCarrera);
            }
        }

    }

    public void registrarFaltas() {
        int dni = Integer.parseInt(vista.pedirDato("Ingrese DNI del Corredor: "));
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese id Carrera: "));
        int faltas = Integer.parseInt(vista.pedirDato("Ingrese faltas del Corredor: "));

        for (Competidor competidor : competidores) {
            if (competidor.getDni() == dni) {
                for (Resultado resultado : resultados) {
                    if (resultado.getIdCompetidor() == competidor.getIdPersona() && resultado.getIdCarrera() == idCarrera) {
                        resultado.setFaltas(faltas);
                        ResultadoDAO dao = new ResultadoDAO(con);
                        int idCompetidor = competidor.getIdPersona();
                        dao.registrarFaltas(idCompetidor, faltas, idCarrera);
                    }
                }
            }
        }
    }

    //espera abandono completado
    public void modificarEstado() {
        int dni = Integer.parseInt(vista.pedirDato("Ingrese DNI del Corredor: "));
        int idCarrera = Integer.parseInt(vista.pedirDato("Ingrese ID de la carrera: "));

        for (Competidor competidor : competidores) {
            if (competidor.getDni() == dni) {
                for (Resultado resultado : resultados) {
                    if (resultado.getIdCompetidor() == competidor.getIdPersona() && resultado.getIdCarrera() == idCarrera) {
                        vista.mensaje("\nEstado actual del competidor: " + resultado.getEstado());
                        vista.mensaje("\nSeleccione el nuevo estado: ");
                        vista.mensaje("1. Espera");
                        vista.mensaje("2. Abandono");
                        vista.mensaje("3. Completado");

                        int opcion;
                        try {
                            opcion = Integer.parseInt(vista.pedirDato("Opcion: "));
                        } catch (NumberFormatException e) {
                            vista.mensaje("Opcion invalida.");
                            return;
                        }
                        String nuevoEstado;
                        if (opcion == 1) {
                            nuevoEstado = "espera";
                        } else if (opcion == 2) {
                            nuevoEstado = "abandono";
                        } else if (opcion == 3) {
                            nuevoEstado = "completado";
                        } else {
                            vista.mensaje("Ingrese una opcion valida.");
                            return;
                        }

                        resultado.setEstado(nuevoEstado);
                        ResultadoDAO dao = new ResultadoDAO(con);
                        int idCompetidor = competidor.getIdPersona();
                        dao.modificarEstado(idCompetidor, nuevoEstado, idCarrera);
                        vista.mensaje("Estado actualizado a: " + nuevoEstado);
                        return;
                    }
                }
            }
        }
        vista.mensaje("No se encontro un resultado con ese DNI y Carrera.");
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
            resultados = resultadoDAO.listaResultados();
            /*
                //VER CONTENIDO DE LAS LISTAS
            vista.mensaje(competidores.toString());
            vista.mensaje(carreras.toString());
            vista.mensaje(jueces.toString());
            vista.mensaje(resultados.toString());
             */
        } catch (SQLException e) {

            vista.mensaje("error:" + e.getMessage());
        }
    }
}

package com.mycompany.tpi.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.tpi.Modelos.Carrera;
import com.mycompany.tpi.Modelos.Competidor;
import com.mycompany.tpi.Modelos.Juez;

import com.mycompany.tpi.Modelos.Resultado;
import com.mycompany.tpi.vista.VistaPrincipal;
import java.io.File;
import java.util.*;

public class ControladorPrincipal {

    private VistaPrincipal vista = new VistaPrincipal();
    private List<Competidor> competidores = new ArrayList<>();
    private List<Juez> jueces = new ArrayList<>();
    private List<Carrera> carreras = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    private String urlCompetidores="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\competidores.json";
    private String urlJueces="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\jueces.json";
    private String urlCarreras="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\carreras.json";
    private String urlResultados="C:\\Users\\alvar\\OneDrive\\Documentos\\Programacion II\\TPI\\TPI\\TPI\\src\\main\\java\\com\\mycompany\\tpi\\archivos\\resultados.json";
    
    
    
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
    }
}



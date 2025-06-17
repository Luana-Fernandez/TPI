package com.mycompany.tpi.controlador;

import com.mycompany.tpi.Modelos.Carrera;
import com.mycompany.tpi.Modelos.Competidor;
import com.mycompany.tpi.Modelos.Juez;

import com.mycompany.tpi.Modelos.Resultado;
import com.mycompany.tpi.vista.VistaPrincipal;
import java.util.*;

public class ControladorPrincipal {

    private VistaPrincipal vista = new VistaPrincipal();
    private List<Competidor> competidores = new ArrayList<>();
    private List<Juez> jueces = new ArrayList<>();
    private List<Carrera> carreras = new ArrayList<>();
    private List<Resultado> resultados = new ArrayList<>();
    
    
    
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
        // conexion a bd y cargar datos en listas
    }
    
    public void guardarDatos(){
        // conexion a bd y guardar datos en cada tabla
    }
}

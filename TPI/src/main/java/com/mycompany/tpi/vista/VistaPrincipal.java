
package com.mycompany.tpi.vista;

import java.util.*;

public class VistaPrincipal {

    private Scanner sc = new Scanner(System.in);
    
    public int menu(){
        
        mensaje("--- MENU PRINCIPAL ---");
        mensaje("1. Registrar Competidor");
        mensaje("2. Registrar Juez");
        mensaje("3. Registrar Carrera");
        mensaje("4. Registrar Tiempo de Competidor");
        mensaje("5. Ranking de Tiempos de Categoria");   // por categoria
        mensaje("6. Ranking de Tiempos Generales");      // de toda la competencia
        mensaje("7. Lista de Abandonos");
        mensaje("8. Informacion de Competencia");        // datos de la competencia + cantidad de competidores
        mensaje("9. Informacion de Competidor");         // datos de competidor + carrera + categoria + tiempo
        mensaje("0. Salir");
        System.out.print("Opcion: ");
        return Integer.parseInt(sc.nextLine());
    }
    
    public String pedirDato(String msj){
        System.out.print(msj);
        return sc.nextLine();
    }
    
    public void mensaje(String msj){
        System.out.println(msj);
    }
}


package com.mycompany.tpi.vista;

import java.util.*;

public class VistaPrincipal {

    private Scanner sc = new Scanner(System.in);
    
    public int menu(){
        
        mensaje("\n--- MENU PRINCIPAL ---");
        mensaje("1. Registrar Competidor");
        mensaje("2. Registrar Juez");
        mensaje("3. Registrar Carrera");
        mensaje("4. Registrar Competidor en Carrera");
        mensaje("5. Ranking de Competidores de Carrera");   
        mensaje("6. TOP 3 Competidores por Carreras");
        mensaje("7. Lista de Abandonos");
        mensaje("8. Informacion de Competencia");
        mensaje("9. Informacion de Competidor");
        mensaje("10. Informacion de Jueces");
        mensaje("11. Registrar Tiempo de Corredor");
        mensaje("12. Registrar Hora fin de Carrera");
        mensaje("13. Actualizar Faltas");
        mensaje("14. Modificar Estado");
        mensaje("0. Salir");
        mensaje("----------------------");
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

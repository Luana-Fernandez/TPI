package com.mycompany.tpi;

import com.mycompany.tpi.controlador.ControladorPrincipal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;


public class TPI {

    public static void main(String[] args) {
        ControladorPrincipal controlador = new ControladorPrincipal();
        controlador.menu();
       
    }
}

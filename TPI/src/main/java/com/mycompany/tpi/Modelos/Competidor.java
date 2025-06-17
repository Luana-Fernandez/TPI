package com.mycompany.tpi.Modelos;

import java.util.List;


public class Competidor extends Persona{
    
    private int idCorredor;
    private int faltas;

    public Competidor(int idCorredor, int faltas, int idPersona, String nombre, String apellido, String mail, String categoria) {
        super(idPersona, nombre, apellido, mail, categoria);
        this.idCorredor = idCorredor;
        this.faltas = faltas;
    }

    public int getIdCorredor() {
        return idCorredor;
    }

    public void setIdCorredor(int idCorredor) {
        this.idCorredor = idCorredor;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    @Override
    public String toString() {
        return "Competidor{" + "idCorredor=" + idCorredor + ", faltas=" + faltas + '}';
    }
    
    public void regsitrarTiempo(){}
    
    public void abandonarCarrera(){}
    
   
}

package com.mycompany.tpi.Modelos;

public class Competidor extends Persona{

    public Competidor(String nombre,String apellido,String mail,String telefono, int dni){
        super(nombre,apellido,mail,telefono,dni);
    }
    
    @Override
    public String toString() {
        return "Competidor: "+this.getNombre()+" "+this.getApellido()+" dni:"+this.getDni();
    }
}

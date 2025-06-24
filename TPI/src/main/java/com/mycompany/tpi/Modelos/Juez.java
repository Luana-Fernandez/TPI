package com.mycompany.tpi.Modelos;

public class Juez extends Persona {

    
    public Juez(String nombre, String apellido, String mail, String telefono, int dni) {
        super(nombre, apellido, mail, telefono, dni);
    }
       @Override
    public String toString() {
        return "Id: "+this.getIdPersona()+" "+this.getNombre()+" "+this.getApellido();
    }
}

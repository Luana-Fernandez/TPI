package com.mycompany.tpi.Modelos;

import java.util.List;


public abstract class Persona {
    private int idPersona;
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;



    public Persona(int idPersona, String nombre, String apellido, String mail, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
    }

   

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + "telefono= "+ telefono +'}';
    }
}

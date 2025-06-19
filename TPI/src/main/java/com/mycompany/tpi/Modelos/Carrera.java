package com.mycompany.tpi.Modelos;

import java.util.*;

public class Carrera {

    private int idCarrera;
    private String categoria;
    private String horaInicio;
    private String horaFin;
    private String ubicacion;
    private String detalle;
    private int idJuez;

    public Carrera() {
    }

    public Carrera(int idCarrera, String categoria, String horaInicio, String horaFin, String ubicacion, String detalle, int idJuez) {

        this.idCarrera = idCarrera;
        this.categoria = categoria;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
        this.detalle = detalle;
        this.idJuez = idJuez;
    }

    public int getIdJuez() {
        return idJuez;
    }

    public void stIdJuez(int idJuez) {
        this.idJuez = idJuez;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Carrera{" + "idCarrera=" + idCarrera + ", categoria=" + categoria + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", ubicacion=" + ubicacion + ", detalle=" + detalle +  "idJuez=" + idJuez +'}';
    }

}

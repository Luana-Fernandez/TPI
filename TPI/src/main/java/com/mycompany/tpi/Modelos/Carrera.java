package com.mycompany.tpi.Modelos;

import java.util.List;


class Carrera {
    private int idCarrera;
    private String categoria;
    private String horaInicio;
    private String horaFin;
    private String ubicacion;
    private String detalle;
    private List<Competidor> competidores;

    public Carrera(int idCarrera, String categoria, String horaInicio, String horaFin, String ubicacion, String detalle) {
        this.idCarrera = idCarrera;
        this.categoria = categoria;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ubicacion = ubicacion;
        this.detalle = detalle;
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

    public List<Competidor> getCompetidores() {
        return competidores;
    }

    public void setCompetidores(Competidor competidores) {
        this.competidores.add(competidores);
    }

    @Override
    public String toString() {
        return "Carrera{" + "idCarrera=" + idCarrera + ", categoria=" + categoria + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", ubicacion=" + ubicacion + ", detalle=" + detalle + ", competidores=" + competidores + '}';
    }
    
    
}

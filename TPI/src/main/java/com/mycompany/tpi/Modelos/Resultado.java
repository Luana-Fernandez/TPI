package com.mycompany.tpi.Modelos;

import java.time.LocalTime;

public class Resultado {
    private int idCompetidor;
    private int carrera;
    private LocalTime tiempo;
    private String estado;

    public Resultado(int idCompetidor, int carrera, LocalTime tiempo, String estado) {
        this.idCompetidor = idCompetidor;
        this.carrera = carrera;
        this.tiempo = tiempo;
        this.estado = estado;
    }

    public int getIdCompetidor() {
        return idCompetidor;
    }

    public void setIdCompetidor(int idCompetidor) {
        this.idCompetidor = idCompetidor;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public LocalTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalTime tiempo) {
        this.tiempo = tiempo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Resultado{" + "idCompetidor=" + idCompetidor + ", carrera=" + carrera + ", tiempo=" + tiempo + ", estado=" + estado + '}';
    }
    
    public void calcularRankingCategoria(){}
    public void calcularRankingGeneral(){}
    public void registrarTiempo(){}
    public void modificarEstado(){}
    
}

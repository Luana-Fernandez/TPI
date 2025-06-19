package com.mycompany.tpi.Modelos;


public class Resultado {
    private int idCompetidor;
    private int carrera;
    private String tiempo;
    private String estado;
    private int numCorredor;
    private int faltas;

    public Resultado(int idCompetidor, int carrera, String tiempo, String estado, int numCorredor, int faltas) {
        this.idCompetidor = idCompetidor;
        this.carrera = carrera;
        this.tiempo = tiempo;
        this.estado = estado;
        this.numCorredor = numCorredor;
        this.faltas = faltas;
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

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumCorredor() {
        return numCorredor;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setNumCorredor(int numCorredor) {
        this.numCorredor = numCorredor;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }
    

    @Override
    public String toString() {
        return "Resultado{" + "idCompetidor=" + idCompetidor + ", carrera=" + carrera + ", tiempo=" + tiempo + ", estado=" + estado + "numCorredor=" + numCorredor + "faltas=" + faltas + '}';
    }
    
    public void calcularRankingCategoria(){}
    public void calcularRankingGeneral(){}
    public void registrarTiempo(){}
    public void modificarEstado(){}
    
}

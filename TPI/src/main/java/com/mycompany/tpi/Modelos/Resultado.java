package com.mycompany.tpi.Modelos;


public class Resultado {
    private int idResultado;
    private int idCompetidor;
    private int carrera;
    private String tiempoCompetidor;
    private String estado;
    private int numCorredor;
    private int faltas;

    public Resultado(int idCompetidor, int carrera, int numCorredor) {
        this.idCompetidor = idCompetidor;
        this.carrera = carrera;
        this.numCorredor = numCorredor;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
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

    public String getTiempoCompetidor() {
        return tiempoCompetidor;
    }

    public void setTiempoCompetidor(String tiempo) {
        this.tiempoCompetidor = tiempo;
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
}

package com.example.myapplication;

public class listausuarios {
    String numeropuesto,estado,idpuesto;



    public listausuarios(String numeropuesto, String estado, String idpuesto) {
        this.numeropuesto = numeropuesto;
        this.estado = estado;
        this.idpuesto=idpuesto;
    }

    public String getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(String idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getNumeropuesto() {
        return numeropuesto;
    }

    public void setNumeropuesto(String numeropuesto) {
        this.numeropuesto = numeropuesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

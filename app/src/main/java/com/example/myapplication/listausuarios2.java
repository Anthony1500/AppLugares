package com.example.myapplication;

public class listausuarios2 {
    String nombre,id_mercado;;

    public listausuarios2(String nombre, String id_mercado) {
        this.nombre = nombre;
        this.id_mercado = id_mercado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_mercado() {
        return id_mercado;
    }

    public void setId_mercado(String id_mercado) {
        this.id_mercado = id_mercado;
    }
}

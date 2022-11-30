package com.example.myapplication;

public class listausuarios3 {
    String nombre,id_restaurante;;


    public listausuarios3(String nombre, String id_restaurante) {
        this.nombre = nombre;
        this.id_restaurante = id_restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(String id_restaurante) {
        this.id_restaurante = id_restaurante;
    }
}

package com.example.myapplication;

public class listausuarios {
    String nombre,id_parques;;


    public listausuarios(String nombre, String id_parques) {
        this.nombre = nombre;
        this.id_parques = id_parques;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_parques() {
        return id_parques;
    }

    public void setId_parques(String id_parques) {
        this.id_parques = id_parques;
    }
}

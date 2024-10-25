package com.example.seniorchat2;
public class ItemTuSalud {

    private String nombre;
    private String fecha;
    private String hora;

    public ItemTuSalud(String nombre, String fecha, String hora) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}

package com.example.seniorchat2;

import android.graphics.Bitmap;

public class ItemActividad {
    private String nombre;
    private String fecha;
    private String hora;
    private boolean asistira;
    private Bitmap imagen;

    public ItemActividad(String nombre, String fecha, String hora, boolean asistira, Bitmap imagen) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.asistira = asistira;
        this.imagen = imagen;
    }




    public String getNombreActividad() {
        return nombre;
    }

    public String getDia() {
        return hora;
    }

    public String getMes() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public boolean isAsistir() {
        return asistira;
    }

    public void setAsistir(boolean asistir) {
        this.asistira = asistir;
    }

    public Bitmap getImagen() {
        return imagen;
    }
}

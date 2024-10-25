package com.example.seniorchat2;

import android.graphics.Bitmap;

public class ItemContacto {
    private String nombre;
    private String telefono;
    private Bitmap imagen;

    public ItemContacto(String nombre, String telefono, Bitmap imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Bitmap getImagen() {
        return imagen;
    }
}

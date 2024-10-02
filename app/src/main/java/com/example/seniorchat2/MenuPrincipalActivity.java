package com.example.seniorchat2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
    }
    public void actividades(View v){
        Intent i = new Intent(this, actividades.class);
        startActivity(i);
    }
    public void contactos(View v){
        Intent i = new Intent(this, contactos.class);
        startActivity(i);
    }
    public void tu_salud(View v){
        Intent i = new Intent(this, tu_salud.class);
        startActivity(i);
    }
    public void ajustes(View v){
        Intent i = new Intent(this, ajustes.class);
    }
    public void logout(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void ubicacion(View v){
        Intent i = new Intent(this, ubicacion.class);
        startActivity(i);
    }
    public void emergencias(View v){
        Intent i = new Intent(this, emergencias.class);
        startActivity(i);
    }
    public void chat(View v){
        Intent i = new Intent(this, chat.class);
        startActivity(i);
    }

}
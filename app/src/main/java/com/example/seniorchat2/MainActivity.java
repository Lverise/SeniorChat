package com.example.seniorchat2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void menu_principal(View v){

        EditText campo1 = this.findViewById(R.id.nombre_usuario);
        String nombre = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.telefono);
        String telefono = campo2.getText().toString();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
        } else if (nombre.equals("admin") && telefono.equals("1234")) {
            Intent i = new Intent(this, MenuPrincipalActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }


    }
}
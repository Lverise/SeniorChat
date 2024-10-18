package com.example.seniorchat2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class actividades extends AppCompatActivity implements AgregarActividadModal.OnNuevaActividadListener {

    private ArrayList<ItemActividad> listaActividades;
    private AdapterActividades adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        Button botonVolver = findViewById(R.id.atras_actividades);
        botonVolver.setOnClickListener(view -> onBackPressed());

        listaActividades = new ArrayList<>();
        listaActividades.add(new ItemActividad("Reunión", "12 Octubre", "15:00", false, null));
        listaActividades.add(new ItemActividad("Lota", "13 Octubre", "10:30", true, null));
        listaActividades.add(new ItemActividad("Clase Yoga", "14 Octubre", "18:00", false, null));
        listaActividades.add(new ItemActividad("Taller de Manualidades", "15 Octubre", "16:00", true, null));

        ListView listView = findViewById(R.id.listview_actividades);
        adapter = new AdapterActividades(this, listaActividades);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.boton_flotante);
        fab.setOnClickListener(v -> {
            AgregarActividadModal dialog = new AgregarActividadModal(this);
            dialog.show(getSupportFragmentManager(), "AgregarActividadModal");
        });
    }

    @Override
    public void onNuevaActividad(ItemActividad actividad) {
        listaActividades.add(actividad);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

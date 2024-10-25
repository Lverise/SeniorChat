package com.example.seniorchat2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class tu_salud extends AppCompatActivity implements AgregarTuSaludModal.OnNuevaSaludListener {

    private ArrayList<ItemTuSalud> listaSalud;
    private AdapterTuSalud adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_salud);

        Button botonVolver = findViewById(R.id.atras_tu_salud);
        botonVolver.setOnClickListener(view -> onBackPressed());

        listaSalud = new ArrayList<>();
        ListView listView = findViewById(R.id.listview_tu_salud);
        adapter = new AdapterTuSalud(listaSalud, this);
        listView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.boton_flotante_tu_salud);
        fab.setOnClickListener(v -> {
            DialogFragment agregarSaludModal = new AgregarTuSaludModal();
            agregarSaludModal.show(getSupportFragmentManager(), "AgregarTuSaludModal");
        });
    }

    @Override
    public void onNuevaSalud(ItemTuSalud nuevaSalud) {
        listaSalud.add(nuevaSalud);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

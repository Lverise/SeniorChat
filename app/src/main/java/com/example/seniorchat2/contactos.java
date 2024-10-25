package com.example.seniorchat2;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.List;


public class contactos extends AppCompatActivity implements AgregarContactoModal.OnNuevoContactoListener {
    private List<ItemContacto> listaContactos;
    private AdapterContactos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        listaContactos = new ArrayList<>();
        adapter = new AdapterContactos(this, listaContactos);

        ListView listViewContactos = findViewById(R.id.listview_contactos);
        listViewContactos.setAdapter(adapter);

        Button botonVolver = findViewById(R.id.atras_contactos);
        botonVolver.setOnClickListener(view -> onBackPressed());

        Button agregarContactoBtn = findViewById(R.id.agregar_nuevo_contacto);
        agregarContactoBtn.setOnClickListener(v -> {
            DialogFragment agregarContactoModal = new AgregarContactoModal(this);
            agregarContactoModal.show(getSupportFragmentManager(), "AgregarContactoModal");
        });
    }

    @Override
    public void onNuevoContacto(ItemContacto contacto) {
        listaContactos.add(contacto);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
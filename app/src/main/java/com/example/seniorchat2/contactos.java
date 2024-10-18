package com.example.seniorchat2;

import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

public class contactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contactos);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button atrasBtn = findViewById(R.id.atras_contactos);
        atrasBtn.setOnClickListener(v -> onBackPressed());  // Vuelve a la actividad anterior

        Button agregarContactoBtn = findViewById(R.id.agregar_nuevo_contacto);

        agregarContactoBtn.setOnClickListener(v -> {
            DialogFragment agregarContactoModal = new AgregarContactoModal();

            agregarContactoModal.show(getSupportFragmentManager(), "AgregarContactoModal");
        });
    }
}

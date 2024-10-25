package com.example.seniorchat2;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;

public class AgregarContactoModal extends BottomSheetDialogFragment {

    private static final int REQUEST_CODE_GALLERY = 1;
    private OnNuevoContactoListener listener;
    private Bitmap imagenSeleccionada = null;
    private ImageView previewFoto;

    // Interfaz para pasar el nuevo contacto a la actividad principal
    public interface OnNuevoContactoListener {
        void onNuevoContacto(ItemContacto contacto);
    }

    public AgregarContactoModal(OnNuevoContactoListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_agregar_contacto, container, false);

        EditText inputNombre = view.findViewById(R.id.et_nombre_contacto);
        EditText inputTelefono = view.findViewById(R.id.et_telefono_contacto);
        previewFoto = view.findViewById(R.id.iv_contacto_imagen);
        Button btnSeleccionarImagen = view.findViewById(R.id.btn_seleccionar_imagen);
        Button guardarContactoBtn = view.findViewById(R.id.btn_agregar_contacto);

        btnSeleccionarImagen.setOnClickListener(v -> {
            Intent intentGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentGaleria, REQUEST_CODE_GALLERY);
        });

        guardarContactoBtn.setOnClickListener(v -> {
            String nombre = inputNombre.getText().toString();
            String telefono = inputTelefono.getText().toString();

            if (!nombre.isEmpty() && !telefono.isEmpty()) {
                if (imagenSeleccionada == null) {
                    imagenSeleccionada = BitmapFactory.decodeResource(getResources(), R.drawable.icono_contactos_350);
                }

                ItemContacto nuevoContacto = new ItemContacto(nombre, telefono, imagenSeleccionada);
                listener.onNuevoContacto(nuevoContacto);
                dismiss();
            } else {
                Toast.makeText(getActivity(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                imagenSeleccionada = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                previewFoto.setImageBitmap(imagenSeleccionada);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

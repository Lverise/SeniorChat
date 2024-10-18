package com.example.seniorchat2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import java.util.Calendar;

public class AgregarActividadModal extends BottomSheetDialogFragment {

    private static final int REQUEST_CODE_GALLERY = 1;
    private EditText inputFecha;
    private EditText inputHora;
    private ImageView previewFoto;
    private OnNuevaActividadListener listener;
    private String fechaSeleccionada, horaSeleccionada;
    private Bitmap imagenSeleccionada;

    public interface OnNuevaActividadListener {
        void onNuevaActividad(ItemActividad actividad);
    }


    public AgregarActividadModal(OnNuevaActividadListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_agregar_actividad, container, false);

        EditText inputNombre = view.findViewById(R.id.input_nombre);
        inputFecha = view.findViewById(R.id.input_fecha);
        inputHora = view.findViewById(R.id.input_hora);
        previewFoto = view.findViewById(R.id.imagen_seleccionada);
        Button btnAgregarFoto = view.findViewById(R.id.btn_agregar_foto_actividad);
        Button btnAgregar = view.findViewById(R.id.btn_agregar_actividad);

        inputFecha.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view1, year, month, dayOfMonth) -> {
                        fechaSeleccionada = dayOfMonth + " " + obtenerMes(month) + " " + year;
                        inputFecha.setText(fechaSeleccionada);
                    }, anio, mes, dia);
            datePickerDialog.show();
        });

        inputHora.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hora = calendar.get(Calendar.HOUR_OF_DAY);
            int minuto = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    (view12, hourOfDay, minute) -> {
                        horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute);
                        inputHora.setText(horaSeleccionada);
                    }, hora, minuto, true);
            timePickerDialog.show();
        });

        btnAgregarFoto.setOnClickListener(v -> {
            Intent intentGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentGaleria, REQUEST_CODE_GALLERY);
        });

        btnAgregar.setOnClickListener(v -> {
            String nombre = inputNombre.getText().toString();
            if (listener != null && fechaSeleccionada != null && horaSeleccionada != null) {
                if (imagenSeleccionada != null) {
                    ItemActividad nuevaActividad = new ItemActividad(nombre, fechaSeleccionada, horaSeleccionada, false, imagenSeleccionada);
                    listener.onNuevaActividad(nuevaActividad);
                } else {
                    Toast.makeText(getActivity(), "Por favor, selecciona una imagen", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            dismiss();
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && data != null) {
            Uri imageUri = data.getData();
            try {
                imagenSeleccionada = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                previewFoto.setImageBitmap(imagenSeleccionada);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private String obtenerMes(int mes) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses[mes];
    }
}

package com.example.seniorchat2;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgregarTuSaludModal extends BottomSheetDialogFragment {

    private OnNuevaSaludListener listener;
    private EditText inputFecha;

    public interface OnNuevaSaludListener {
        void onNuevaSalud(ItemTuSalud nuevaSalud);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnNuevaSaludListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnNuevaSaludListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_tu_salud, container, false);

        EditText inputNombre = view.findViewById(R.id.input_nombre_salud);
        inputFecha = view.findViewById(R.id.input_fecha_salud);
        EditText inputHora = view.findViewById(R.id.input_hora_salud);
        Button btnAgregar = view.findViewById(R.id.btn_agregar_tu_salud);

        inputFecha.setOnClickListener(v -> showDatePickerDialog(inputFecha));

        inputHora.setOnClickListener(v -> showTimePickerDialog(inputHora));

        btnAgregar.setOnClickListener(v -> {
            String nombre = inputNombre.getText().toString();
            String fecha = inputFecha.getText().toString();
            String hora = inputHora.getText().toString();

            ItemTuSalud nuevaSalud = new ItemTuSalud(nombre, fecha, hora);
            listener.onNuevaSalud(nuevaSalud);
            dismiss();
        });

        return view;
    }

    private void showDatePickerDialog(final EditText inputFecha) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year1, month1, dayOfMonth) -> inputFecha.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1),
                year, month, day);

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private void showTimePickerDialog(final EditText inputHora) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minute1) -> inputHora.setText(hourOfDay + ":" + String.format("%02d", minute1)),
                hour, minute, true);

        try {
            String fechaSeleccionada = inputFecha.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaSeleccionadaDate = sdf.parse(fechaSeleccionada);
            Calendar selectedDateCal = Calendar.getInstance();
            selectedDateCal.setTime(fechaSeleccionadaDate);

            if (selectedDateCal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                    selectedDateCal.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                timePickerDialog.updateTime(hour, minute);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        timePickerDialog.show();
    }
}

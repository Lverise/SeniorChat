package com.example.seniorchat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterActividades extends ArrayAdapter<ItemActividad> {
    private final Context context;
    private final List<ItemActividad> actividades;

    public AdapterActividades(Context context, List<ItemActividad> actividades) {
        super(context, R.layout.item_actividad, actividades);
        this.context = context;
        this.actividades = actividades;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemActividad actividad = actividades.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_actividad, parent, false);
        }

        TextView textActividad = convertView.findViewById(R.id.texto_actividad);
        Button botonEstado = convertView.findViewById(R.id.btn_asistir_cancelar);
        ImageView imagenActividad = convertView.findViewById(R.id.imagen_actividad);

        textActividad.setText("Tienes " + actividad.getNombreActividad() + " el " + actividad.getDia() + " de " + actividad.getMes() + " a las " + actividad.getHora() + " hrs.");

        botonEstado.setText(actividad.isAsistir() ? "Cancelar" : "Asistir");
        botonEstado.setOnClickListener(v -> {
            actividad.setAsistir(!actividad.isAsistir());
            notifyDataSetChanged();
        });

        if (actividad.getImagen() != null) {
            imagenActividad.setImageBitmap(actividad.getImagen());
        } else {
            imagenActividad.setImageResource(R.drawable.icono_ajuste_350); // Imagen predeterminada si no hay imagen
        }

        return convertView;
    }
}

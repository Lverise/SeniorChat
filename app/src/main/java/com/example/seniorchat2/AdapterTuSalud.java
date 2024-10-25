package com.example.seniorchat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdapterTuSalud extends BaseAdapter {

    private List<ItemTuSalud> itemList;
    private Context context;

    public AdapterTuSalud(List<ItemTuSalud> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tu_salud, parent, false);
            holder = new ViewHolder();
            holder.indicadorSalud = convertView.findViewById(R.id.indicador_salud);
            holder.diaMesTuSalud = convertView.findViewById(R.id.dia_mes_tu_salud);
            holder.nombreTuSaludHora = convertView.findViewById(R.id.nombre_tu_salud_hora);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemTuSalud item = itemList.get(position);

        String fechaFormateada = formatoFechaCompleta(item.getFecha());
        String indicador = calcularIndicador(item.getFecha());

        holder.indicadorSalud.setText(indicador);
        holder.diaMesTuSalud.setText(fechaFormateada);
        holder.nombreTuSaludHora.setText("Tienes " + item.getNombre() + " a las " + item.getHora() + " hrs.");

        return convertView;
    }

    private static class ViewHolder {
        TextView indicadorSalud, diaMesTuSalud, nombreTuSaludHora;
    }

    public String calcularIndicador(String fechaActividad) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar hoy = Calendar.getInstance();
        Calendar fechaActividadCal = Calendar.getInstance();

        try {
            Date fecha = sdf.parse(fechaActividad);
            fechaActividadCal.setTime(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        long diferenciaMilisegundos = fechaActividadCal.getTimeInMillis() - hoy.getTimeInMillis();
        int diferenciaDias = (int) (diferenciaMilisegundos / (1000 * 60 * 60 * 24));

        if (diferenciaDias == 0) {
            return "HOY";
        } else if (diferenciaDias == 1) {
            return "MAÑANA";
        } else if (diferenciaDias > 1) {
            return "En " + diferenciaDias + " días";
        } else {
            return "Fecha pasada";
        }
    }

    public String formatoFechaCompleta(String fechaOriginal) {
        SimpleDateFormat sdfOriginal = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat sdfDestino = new SimpleDateFormat("EEEE d 'de' MMMM", Locale.getDefault());

        try {
            Date fecha = sdfOriginal.parse(fechaOriginal);
            return sdfDestino.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return fechaOriginal;
        }
    }
}

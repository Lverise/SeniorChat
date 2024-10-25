package com.example.seniorchat2;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterContactos extends ArrayAdapter<ItemContacto> {
    private final Context context;
    private final List<ItemContacto> contactos;

    public AdapterContactos(Context context, List<ItemContacto> contactos) {
        super(context, R.layout.item_contacto, contactos);
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemContacto contacto = contactos.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false);
        }

        TextView nombreContacto = convertView.findViewById(R.id.nombre_contacto);
        TextView numeroContacto = convertView.findViewById(R.id.numero_contacto);
        ImageView imagenContacto = convertView.findViewById(R.id.imagen_contacto);

        nombreContacto.setText(contacto.getNombre());
        numeroContacto.setText(contacto.getTelefono());

        Bitmap imagen = contacto.getImagen();
        if (imagen != null) {
            imagenContacto.setImageBitmap(imagen);
        }

        return convertView;
    }
}

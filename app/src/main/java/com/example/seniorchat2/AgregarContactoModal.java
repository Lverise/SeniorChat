package com.example.seniorchat2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AgregarContactoModal extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.modal_agregar_contacto, container, false);

        Button guardarContactoBtn = view.findViewById(R.id.btn_agregar_contacto);
        guardarContactoBtn.setOnClickListener(v -> {
            dismiss();
        });

        return view;
    }
}

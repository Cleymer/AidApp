package com.teamponytta.aidapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.Hospital;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.hosHolder>{

    ArrayList<Hospital> hList;

    public HospitalAdapter(ArrayList<Hospital> hList) {
        this.hList = hList;
    }

    @NonNull
    @Override
    public hosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_card, parent, false);
        return new hosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hosHolder holder, int position) {
        Hospital ho = hList.get(position);
        holder.setDetails(ho);
    }

    @Override
    public int getItemCount() {
        return hList.size();
    }


    public class hosHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtDepartamento, txtMunicipio, txtServicio, txtTelefono, txtDireccion;

        public hosHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.tv_nameH);
            txtDepartamento = itemView.findViewById(R.id.tv_departamentoH);
            txtMunicipio = itemView.findViewById(R.id.tv_municipio);
            txtServicio = itemView.findViewById(R.id.tv_servicio);
            txtTelefono = itemView.findViewById(R.id.tv_telefono);
            txtDireccion = itemView.findViewById(R.id.tv_direccion);
        }

        public void setDetails(Hospital ho){
            txtNombre.setText(ho.getNombre());
            txtDepartamento.setText(ho.getDepartamento());
            txtMunicipio.setText(ho.getMunicipio());
            txtServicio.setText(ho.getServicio());
            txtTelefono.setText(ho.getTelefono());
            txtDireccion.setText(ho.getDireccion());
        }

    }

}

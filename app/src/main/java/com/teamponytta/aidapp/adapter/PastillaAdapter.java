package com.teamponytta.aidapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.Pastilla;

import java.util.ArrayList;

public class PastillaAdapter extends RecyclerView.Adapter <PastillaAdapter.bHolder>{

    private ArrayList<Pastilla> pList;

    public PastillaAdapter(ArrayList<Pastilla> pList) {
        this.pList = pList;
    }

    @NonNull
    @Override
    public bHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastilla_card, parent, false);
        return new PastillaAdapter.bHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bHolder holder, int position) {
        Pastilla pas = pList.get(position);
        holder.setDetails(pas);
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }

    public class bHolder extends RecyclerView.ViewHolder{

        TextView txtNombre, txtDesc, txtCategoria;

        public bHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.tv_nameP);
            txtDesc = itemView.findViewById(R.id.tv_descP);
            txtCategoria = itemView.findViewById(R.id.tv_catP);
        }

        public void setDetails(Pastilla pas){
            txtNombre.setText("Nombre: " + pas.getNombre());
            txtDesc.setText("Descripción: " + pas.getDesc());
            txtCategoria.setText("Categoría: " + pas.getCategory());
        }
    }

}

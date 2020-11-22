package com.teamponytta.aidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.PrimerosAuxilios;

import java.util.ArrayList;

public class PrimerosAuxiliosAdapter extends RecyclerView.Adapter<PrimerosAuxiliosAdapter.paHolder> {

    private ArrayList<PrimerosAuxilios> paList;

    public PrimerosAuxiliosAdapter(ArrayList<PrimerosAuxilios> paList) {
        this.paList = paList;
    }

    @NonNull
    @Override
    public paHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pa_card, parent, false);
        return new paHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull paHolder holder, int position) {
        PrimerosAuxilios pa = paList.get(position);
        holder.setDetails(pa);
    }

    @Override
    public int getItemCount() {
        return paList.size();
    }

    public class paHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtDesc;

        public paHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_name);
            txtDesc = itemView.findViewById(R.id.tv_desc);
        }

        public void setDetails(PrimerosAuxilios pa)
        {
            txtName.setText(pa.getNombre());
            txtDesc.setText(pa.getDescripcion());
        }

    }

}

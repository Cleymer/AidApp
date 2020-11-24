package com.teamponytta.aidapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.internal.ContextUtils;
import com.teamponytta.aidapp.InfoActivity;
import com.teamponytta.aidapp.MainActivity;
import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.PrimerosAuxilios;

import java.util.ArrayList;

public class PrimerosAuxiliosAdapter extends RecyclerView.Adapter<PrimerosAuxiliosAdapter.paHolder> implements View.OnClickListener{

    private ArrayList<PrimerosAuxilios> paList;

    private View.OnClickListener listener;

    public PrimerosAuxiliosAdapter(ArrayList<PrimerosAuxilios> paList) {
        this.paList = paList;
    }

    @NonNull
    @Override
    public paHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pa_card, parent, false);
        view.setOnClickListener(this);
        return new paHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull paHolder holder, int position) {
        PrimerosAuxilios pa = paList.get(position);
        holder.setDetails(pa);

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return paList.size();
    }

    @Override
    public void onClick(View view) {
        if (listener!=null)
        {
            listener.onClick(view);
        }
    }

    public class paHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtDesc;
        private ImageView imgPA;

        public paHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_name);
            txtDesc = itemView.findViewById(R.id.tv_desc);
            imgPA = itemView.findViewById(R.id.iv_pa);
        }

        public void setDetails(PrimerosAuxilios pa)
        {
            txtName.setText(pa.getNombre());
            txtDesc.setText(pa.getDescripcion());
            imgPA.setImageResource(pa.getPaPhoto());
        }

    }

}

package com.teamponytta.aidapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.Departamento;

import java.util.ArrayList;

public class DepartamentoAdapter extends RecyclerView.Adapter<DepartamentoAdapter.dHolder> implements View.OnClickListener{

    ArrayList<Departamento> dList;
    private View.OnClickListener listener;

    public DepartamentoAdapter(ArrayList<Departamento> dList) {
        this.dList = dList;
    }

    @NonNull
    @Override
    public dHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.departamento_card, parent, false);
        view.setOnClickListener(this);
        return new dHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dHolder holder, int position) {
        Departamento dep = dList.get(position);
        holder.setDetails(dep);
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }


    public class dHolder extends RecyclerView.ViewHolder{

        TextView txtDepartamento;

        public dHolder(@NonNull View itemView) {
            super(itemView);
            txtDepartamento = itemView.findViewById(R.id.tv_departamento);
        }

        public void setDetails(Departamento dep){
            txtDepartamento.setText(dep.getNombre());
        }

    }

}

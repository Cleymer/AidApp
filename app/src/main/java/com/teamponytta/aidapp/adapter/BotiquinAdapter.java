package com.teamponytta.aidapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teamponytta.aidapp.R;
import com.teamponytta.aidapp.model.Botiquin;

import java.util.ArrayList;

public class BotiquinAdapter extends RecyclerView.Adapter<BotiquinAdapter.bHolder> implements View.OnClickListener{


    private ArrayList<Botiquin> bList;

    private View.OnClickListener listener;

    public BotiquinAdapter(ArrayList<Botiquin> bList) {
        this.bList = bList;
    }

    @NonNull
    @Override
    public bHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.botiquin_card, parent, false);
        view.setOnClickListener(this);
        return new bHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bHolder holder, int position) {
        Botiquin botiquin = bList.get(position);
        holder.setDetails(botiquin);
    }

    @Override
    public int getItemCount() {
        return bList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null)
        {
            listener.onClick(view);
        }
    }


    public class bHolder extends RecyclerView.ViewHolder{

        private TextView txtNombre, txtDesc;
        private ImageView imgB;

        public bHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.tv_nameB);
            txtDesc = itemView.findViewById(R.id.tv_descB);
            imgB = itemView.findViewById(R.id.iv_botiquin);
        }


        public void setDetails(Botiquin botiquin){
            imgB.setImageResource(botiquin.getbPhoto());
            txtNombre.setText(botiquin.getNombre());
            txtDesc.setText(botiquin.getDesc());
        }

    }

}

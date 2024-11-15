package com.example.datt.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datt.R;
import com.example.datt.Screen_User.Detail_Book;
import com.example.datt.model.FootballField;
import com.example.datt.model.SoccerTime;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SoccerTimeAdapter  extends RecyclerView.Adapter<SoccerTimeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SoccerTime> Soccertime;
    FirebaseFirestore database;
    public SoccerTimeAdapter(Context context, ArrayList<SoccerTime> Soccertime,FirebaseFirestore database) {
        this.context = context;
        this.Soccertime = Soccertime;
        this.database = database;
    }
    @NonNull
    @Override
    public SoccerTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_soccertime,parent,false);
        return new SoccerTimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoccerTimeAdapter.ViewHolder holder, int position) {
        holder.ca.setText("Tên sân : "+Soccertime.get(position).getTenca());
        holder.solg.setText("Số lượng sân : "+String.valueOf(Soccertime.get(position).getSoluong()));
        holder.thoigian.setText("Tên sân : "+Soccertime.get(position).getThoigianca());
        holder.giaca.setText("Số lượng sân : "+Soccertime.get(position).getGiaca());


        holder.btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), Detail_Book.class);
                intent.putExtra("ca",Soccertime.get(position).getTenca());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Soccertime.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ca,giaca,solg,thoigian;
        Button btnselect;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnselect=itemView.findViewById(R.id.btnselect);
            ca=itemView.findViewById(R.id.tv_ca);
            giaca=itemView.findViewById(R.id.tv_solg);
            solg=itemView.findViewById(R.id.tv_giaca);
            thoigian=itemView.findViewById(R.id.tv_thoigian);
        }
    }
}

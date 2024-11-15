package com.example.datt.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SoccerTimeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView san, solgca;
        Button btnview, btndelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

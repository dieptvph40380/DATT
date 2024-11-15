package com.example.datt.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.datt.R;
import com.example.datt.Screen_User.Detail_Book;
import com.example.datt.model.FootballField;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FootballFieldAdapter extends RecyclerView.Adapter<FootballFieldAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FootballField> Football;
    FirebaseFirestore database;
    public FootballFieldAdapter(Context context, ArrayList<FootballField> Football,FirebaseFirestore database) {
        this.context = context;
        this.Football = Football;
        this.database = database;
    }
    public void updateList(ArrayList<FootballField> newList) {
        this.Football = newList; // Cập nhật danh sách khách hàng
        notifyDataSetChanged(); // Thông báo adapter để cập nhật
    }
    @NonNull
    @Override
    public FootballFieldAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanbong,parent,false);
        return new FootballFieldAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FootballFieldAdapter.ViewHolder holder, int position) {
        holder.san.setText("Tên sân : "+Football.get(position).getTensan());
        holder.solgca.setText("Số lượng sân : "+String.valueOf(Football.get(position).getSoluongca()));
        Glide.with(context).load(Football.get(position).getAnh()).into(holder.image);

        holder.lin_football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), Detail_Book.class);
                intent.putExtra("tensan",Football.get(position).getTensan());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Football.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView san, solgca;
        Button btnview, btndelete;
        LinearLayout lin_football;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.img_anhsan);
            san=itemView.findViewById(R.id.tv_tensan);
            solgca=itemView.findViewById(R.id.tv_solgca);
            lin_football=itemView.findViewById(R.id.lin_football);
        }
    }
}

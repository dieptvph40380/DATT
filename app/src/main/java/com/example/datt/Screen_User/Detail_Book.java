package com.example.datt.Screen_User;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datt.R;
import com.example.datt.adapter.FootballFieldAdapter;
import com.example.datt.adapter.SoccerTimeAdapter;
import com.example.datt.model.FootballField;
import com.example.datt.model.SoccerTime;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Detail_Book extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView rcv_soccer, rcv_grocery;
    Context context;
    ArrayList<SoccerTime> scoccertime = new ArrayList<>();
    SoccerTimeAdapter adapter_soccertime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_book);

        rcv_soccer =findViewById(R.id.rcv_soccertime);
        db = FirebaseFirestore.getInstance();

        adapter_soccertime = new SoccerTimeAdapter(this, scoccertime, db);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rcv_soccer.setLayoutManager(gridLayoutManager);
        rcv_soccer.setAdapter(adapter_soccertime);

        ListenFirebaseFirestore_Soccertime();

    }

    private void ListenFirebaseFirestore_Soccertime() {
        db.collection("SoccerTime")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("TAG", "fail", error);
                            return;
                        }
                        if (value != null) {
                            for (DocumentChange dc : value.getDocumentChanges()) {
                                switch (dc.getType()) {
                                    case ADDED: {
                                        SoccerTime newU = dc.getDocument().toObject(SoccerTime.class);
                                        scoccertime.add(newU);
                                        adapter_soccertime.notifyItemInserted(scoccertime.size() - 1);
                                        break;
                                    }
                                    case MODIFIED: {
                                        SoccerTime update = dc.getDocument().toObject(SoccerTime.class);
                                        if (dc.getOldIndex() == dc.getNewIndex()) {
                                            scoccertime.set(dc.getOldIndex(), update);
                                            adapter_soccertime.notifyItemChanged(dc.getOldIndex());

                                        } else {
                                            scoccertime.remove(dc.getOldIndex());
                                            scoccertime.add(update);
                                            adapter_soccertime.notifyItemMoved(dc.getOldIndex(), dc.getNewIndex());

                                        }
                                        break;
                                    }
                                    case REMOVED: {
                                        dc.getDocument().toObject(SoccerTime.class);
                                        scoccertime.remove(dc.getOldIndex());
                                        adapter_soccertime.notifyItemRemoved(dc.getOldIndex());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });

    }
}
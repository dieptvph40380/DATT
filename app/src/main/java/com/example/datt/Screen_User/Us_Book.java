package com.example.datt.Screen_User;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.datt.R;
import com.example.datt.adapter.FootballFieldAdapter;
import com.example.datt.model.FootballField;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Us_Book extends Fragment {
    FirebaseFirestore db;
    RecyclerView rcv_client, rcv_grocery;
    SearchView searchCustomer;
    Context context;
    ArrayList<FootballField> football = new ArrayList<>();
    FootballFieldAdapter adapter_football;
    ArrayList<FootballField> filteredFootball = new ArrayList<>();
    public Us_Book() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_us__book, container, false);
        rcv_client = v.findViewById(R.id.rcv_client);
        searchCustomer = v.findViewById(R.id.search_Customer);
        db = FirebaseFirestore.getInstance();

        adapter_football = new FootballFieldAdapter(getContext(), football, db);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rcv_client.setLayoutManager(gridLayoutManager);
        rcv_client.setAdapter(adapter_football);

        ListenFirebaseFirestore_Football();

        return v;
    }
    private void ListenFirebaseFirestore_Football() {
        db.collection("FootballField")
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
                                        FootballField newU = dc.getDocument().toObject(FootballField.class);
                                        football.add(newU);
                                        adapter_football.notifyItemInserted(football.size() - 1);
                                        break;
                                    }
                                    case MODIFIED: {
                                        FootballField update = dc.getDocument().toObject(FootballField.class);
                                        if (dc.getOldIndex() == dc.getNewIndex()) {
                                            football.set(dc.getOldIndex(), update);
                                            adapter_football.notifyItemChanged(dc.getOldIndex());

                                        } else {
                                            football.remove(dc.getOldIndex());
                                            football.add(update);
                                            adapter_football.notifyItemMoved(dc.getOldIndex(), dc.getNewIndex());

                                        }
                                        break;
                                    }
                                    case REMOVED: {
                                        dc.getDocument().toObject(FootballField.class);
                                        football.remove(dc.getOldIndex());
                                        adapter_football.notifyItemRemoved(dc.getOldIndex());
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });

        searchCustomer.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText); // Gọi hàm lọc
                return true;
            }
        });
    }

    private void filterList(String text) {
        filteredFootball.clear(); // Xóa danh sách lọc trước đó
        if (text.isEmpty()) {
            filteredFootball.addAll(football); // Nếu ô tìm kiếm trống, thêm lại tất cả khách hàng
        } else {
            for (FootballField client : football) {
                if (client.getTensan().toLowerCase().contains(text.toLowerCase())) { // Kiểm tra nếu tên chứa chuỗi tìm kiếm
                    filteredFootball.add(client);
                }
            }
        }
        adapter_football.updateList(filteredFootball); // Cập nhật danh sách trong adapter
    }

}
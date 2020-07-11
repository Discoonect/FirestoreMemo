package com.test.firestorememo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.test.firestorememo.adapter.RecyclerViewAdapter;
import com.test.firestorememo.model.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_add;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Data> list = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddMemo.class);
                startActivity(i);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        db.collection("DB")
        .get()
        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot snapshot : queryDocumentSnapshots){
                    String id = snapshot.getId();

                    Data data = snapshot.toObject(Data.class);
                    data.setId(id);

                    list.add(data);
                }
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,
                    list);
            recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }
}
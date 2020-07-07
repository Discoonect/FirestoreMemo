package com.test.firestorememo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;
import com.test.firestorememo.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateMemo extends AppCompatActivity {

    EditText editTitle;
    AutoCompleteTextView editMemo;
    Button btn_update;
    Button btn_cancel;

    ArrayList<Data> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_memo);

        editMemo = findViewById(R.id.editMemo);
        editTitle = findViewById(R.id.editTitle);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_update = findViewById(R.id.btn_update);

        Intent i = getIntent();
        Data data = (Data) i.getSerializableExtra("data");

        editTitle.setText(data.getTitle());
        editMemo.setText(data.getMemo());


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String up_title = editTitle.getText().toString().trim();
                String up_memo = editMemo.getText().toString().trim();

                Map<String, Object> update = new HashMap<>();
                update.put("title",up_title);
                update.put("memo",up_memo);

//                db.

            }
        });


    }
}
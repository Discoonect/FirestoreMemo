package com.test.firestorememo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.test.firestorememo.model.Data;

import java.util.ArrayList;

public class UpdateMemo extends AppCompatActivity {

    EditText up_title;
    AutoCompleteTextView up_memo;
    Button btn_update;
    Button btn_cancel;

    String id;

    ArrayList<Data> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Intent i;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_memo);

        up_memo = findViewById(R.id.editMemo1);
        up_title = findViewById(R.id.editTitle1);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_update = findViewById(R.id.btn_update);

        i = getIntent();
        data = (Data) i.getSerializableExtra("data");



        up_title.setText(data.getTitle());
        up_memo.setText(data.getMemo());
        Log.i("AAA",up_memo.getText().toString().trim());

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = up_title.getText().toString().trim();
                String memo = up_memo.getText().toString().trim();

                Data dataS = new Data(title,memo);
                db.collection("DB")
                .document(data.getId())
                .set(dataS)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("AAA","1");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("AAA",e.toString());
                    }
                });
            }
        });
    }
}
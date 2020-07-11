package com.test.firestorememo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.test.firestorememo.R;
import com.test.firestorememo.UpdateMemo;
import com.test.firestorememo.model.Data;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Data> dataList;

    public RecyclerViewAdapter(Context context, ArrayList<Data> dataList){
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.data_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Data data  = dataList.get(position);
        String title = data.getTitle();
        String memo = data.getMemo();

        holder.txtTitle.setText(title);
        holder.txtMemo.setText(memo);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle;
        public TextView txtMemo;
        CardView cardView;


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        public ViewHolder(@Nullable View itemView){
            super(itemView);

            txtMemo = itemView.findViewById(R.id.txtMemo);
            txtTitle = itemView.findViewById(R.id.editTitle);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, UpdateMemo.class);
                    int index = getAdapterPosition();

                    Data data = dataList.get(index);
                    i.putExtra("data",data);

                    context.startActivity(i);
                }
            });

        }
    }
}
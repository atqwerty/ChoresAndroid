package com.example.chores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.classes.Board;
import com.example.chores.classes.Notification;
import com.example.chores.classes.Test;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MainViewHolder> {
    private ArrayList<Notification> data;

    private ItemClickListener itemClickListener;

    public RecyclerViewAdapter(ArrayList<Notification> data, ItemClickListener itemClickListener) {
        super();
        this.data = data;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_row_data, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, final int position) {
        holder.textViewA.setText(data.get(position).toString());
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout feed;
        private TextView textViewA;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            feed= itemView.findViewById(R.id.postId);
            textViewA = itemView.findViewById(R.id.textViewA);
        }

        public void setItemClick(final Board item) {
            this.feed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener != null) {
                        itemClickListener.onItemClick(item, getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface ItemClickListener {

        void onItemClick(Board board, int position);
    }
}

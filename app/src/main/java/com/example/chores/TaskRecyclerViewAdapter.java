package com.example.chores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.classes.Board;
import com.example.chores.classes.Task;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.MainViewHolder>{

    private ArrayList<Task> data;
    private TaskRecyclerViewAdapter.ItemClickListener itemClickListener;

    public TaskRecyclerViewAdapter(ArrayList<Task> data, TaskRecyclerViewAdapter.ItemClickListener itemClickListener) {
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
    public TaskRecyclerViewAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.task_row, parent, false);
        return new TaskRecyclerViewAdapter.MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecyclerViewAdapter.MainViewHolder holder, final int position) {
        holder.textViewA.setText(data.get(position).getName());

        holder.setItemClick(data.get(position));
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout feed;
        private TextView textViewA;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            feed= itemView.findViewById(R.id.task);
            textViewA = itemView.findViewById(R.id.task_name);
        }

        public void setItemClick(final Task item) {
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

        void onItemClick(Task task, int position);
    }
}

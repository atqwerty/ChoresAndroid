package com.example.chores;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.classes.Board;
import com.example.chores.classes.MyDragShadowBuilder;
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
        if (data == null) {
            return 0;
        }
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

        private View feed;
        private TextView textViewA;
        private Button shareButton;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            feed = itemView.findViewById(R.id.task);
            textViewA = itemView.findViewById(R.id.task_name);
            shareButton = itemView.findViewById(R.id.buttonShareInList);

            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareBody = "body";
                    String shareSub = "sub";
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    v.getContext().startActivity(Intent.createChooser(shareIntent, "Share using"));
                }
            });
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

            this.feed.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public boolean onLongClick(View view) {
                    view.setTag("asdf");
                    ClipData.Item item = new ClipData.Item(view.getTag().toString());

                    ClipData dragData = new ClipData(
                            view.getTag().toString(),
                            new String[] {ClipDescription.MIMETYPE_TEXT_INTENT},
                            item
                    );

                    View.DragShadowBuilder myShadow = new MyDragShadowBuilder(view);

                    view.startDragAndDrop(dragData, myShadow, null, 0);
                    return true;
                }
            });

        }
    }

    public interface ItemClickListener {

        void onItemClick(Task task, int position);
    }
}

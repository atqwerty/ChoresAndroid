package com.example.chores.ui.feed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.R;
import com.example.chores.RecyclerViewAdapter;
import com.example.chores.ativities.BoardActivity;
import com.example.chores.classes.Board;
import com.example.chores.classes.Test;
import com.example.chores.classes.User;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private FeedViewModel feedViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    View root;

    private RecyclerViewAdapter.ItemClickListener itemClickListener = new RecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Board item, int position) {
            Intent intent = new Intent(getActivity(), BoardActivity.class);
            intent.putExtra(item.getName(), item);
            startActivity(intent);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        feedViewModel =
//                ViewModelProviders.of(this).get(FeedViewModel.class);
        root = inflater.inflate(R.layout.fragment_feed, container, false);
//        feedViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        User a = ViewModelProviders.of(getActivity()).get(FeedViewModel.class).getUser();

        recyclerView = root.findViewById(R.id.recycledView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Log.d("notifs", "onActivityCreated: " + a.getNotifications());

        adapter = new RecyclerViewAdapter(a.getNotifications(), itemClickListener);
        recyclerView.setAdapter(adapter);

//        recyclerView = root.findViewById(R.id.recycledView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        adapter = new RecyclerViewAdapter(feedViewModel.getNotifications(), itemClickListener);
//        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
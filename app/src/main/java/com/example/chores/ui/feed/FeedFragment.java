package com.example.chores.ui.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.R;
import com.example.chores.NotificationRecyclerViewAdapter;
import com.example.chores.ativities.NotificationActivity;
import com.example.chores.classes.Notification;
import com.example.chores.classes.User;

public class FeedFragment extends Fragment {

    private FeedViewModel feedViewModel;
    private RecyclerView recyclerView;
    private NotificationRecyclerViewAdapter adapter;
    View root;

    private NotificationRecyclerViewAdapter.ItemClickListener itemClickListener = new NotificationRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Notification notification, int position) {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            intent.putExtra("notif", notification.toString());
            startActivity(intent);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_feed, container, false);

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        User a = ViewModelProviders.of(getActivity()).get(FeedViewModel.class).getUser();

        recyclerView = root.findViewById(R.id.recycledViewFeed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new NotificationRecyclerViewAdapter(a.getNotifications(), itemClickListener);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
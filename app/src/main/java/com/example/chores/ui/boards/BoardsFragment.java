package com.example.chores.ui.boards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.BoardRecyclerViewAdapter;
import com.example.chores.R;
import com.example.chores.ativities.BoardActivity;
import com.example.chores.classes.Board;

import java.util.ArrayList;

public class BoardsFragment extends Fragment {

    private BoardsViewModel boardsViewModel;
    private RecyclerView recyclerView;
    private BoardRecyclerViewAdapter adapter;
    View root;

    private BoardRecyclerViewAdapter.ItemClickListener itemClickListener = new BoardRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Board board, int position) {
            Intent intent = new Intent(getActivity(), BoardActivity.class);
            intent.putExtra("board", board);
            startActivity(intent);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) { boardsViewModel =
                ViewModelProviders.of(this).get(BoardsViewModel.class);
        root = inflater.inflate(R.layout.fragment_board, container, false);

        boardsViewModel = ViewModelProviders.of(this).get(BoardsViewModel.class);
//        User a = ViewModelProviders.of(getActivity()).get(FeedViewModel.class).getUser();
        ArrayList<Board> a = ViewModelProviders.of(getActivity()).get(BoardsViewModel.class).getBoards();

        recyclerView = root.findViewById(R.id.recycledViewBoard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new BoardRecyclerViewAdapter(a, itemClickListener);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}

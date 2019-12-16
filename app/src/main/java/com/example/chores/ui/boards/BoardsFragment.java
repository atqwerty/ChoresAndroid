package com.example.chores.ui.boards;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chores.BoardRecyclerViewAdapter;
import com.example.chores.R;
import com.example.chores.ativities.BoardActivity;
import com.example.chores.ativities.TestActivity;
import com.example.chores.classes.Board;
import com.example.chores.classes.User;
import com.example.chores.ui.forms.NewBoardFormActivity;

import java.util.ArrayList;

public class BoardsFragment extends Fragment {

    private BoardsViewModel boardsViewModel;
    private RecyclerView recyclerView;
    private BoardRecyclerViewAdapter adapter;
    private Button newBoardButton;
    View root;

    private BoardRecyclerViewAdapter.ItemClickListener itemClickListener = new BoardRecyclerViewAdapter.ItemClickListener() {
        @Override
        public void onItemClick(Board board, int position) {
            Intent intent = new Intent(getActivity(), TestActivity.class);
            intent.putExtra("targetBoard", board);
            startActivity(intent);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) { boardsViewModel =
                ViewModelProviders.of(this).get(BoardsViewModel.class);
        root = inflater.inflate(R.layout.fragment_board, container, false);
        newBoardButton = (Button) root.findViewById(R.id.new_board_button);
        final User currentUser = ViewModelProviders.of(getActivity()).get(BoardsViewModel.class).getCurrentUser();
        newBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewBoardFormActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });

        boardsViewModel = ViewModelProviders.of(this).get(BoardsViewModel.class);
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

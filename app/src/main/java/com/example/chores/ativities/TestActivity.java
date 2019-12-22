package com.example.chores.ativities;

import android.content.Intent;
import android.os.Bundle;

import com.example.chores.R;
import com.example.chores.ativities.ui.main.PlaceholderFragment;
import com.example.chores.classes.Board;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chores.ativities.ui.main.SectionsPagerAdapter;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Board currentBoard = (Board) getIntent().getSerializableExtra("targetBoard");

        setContentView(R.layout.activity_test);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), currentBoard);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewStatusActivity.class);
                PlaceholderFragment.setCounter(0);
                intent.putExtra("currentBoard", currentBoard);
                startActivity(intent);
            }
        });
    }
}
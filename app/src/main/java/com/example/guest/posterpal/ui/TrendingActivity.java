package com.example.guest.posterpal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.guest.posterpal.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingActivity extends AppCompatActivity {
    @Bind(R.id.categoriesRecyclerView) RecyclerView mCategoriesView;
    @Bind(R.id.trendingPostsRecyclerView) RecyclerView mPostsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        ButterKnife.bind(this);


    }
}

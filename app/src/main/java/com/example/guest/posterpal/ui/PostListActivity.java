package com.example.guest.posterpal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.posterpal.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.Bind;

public class PostListActivity extends AppCompatActivity {
    private DatabaseReference mPostReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.trendingPostsRecyclerView)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

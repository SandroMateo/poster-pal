package com.example.guest.posterpal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.posterpal.Constants;
import com.example.guest.posterpal.R;
import com.example.guest.posterpal.adapters.FirebasePostViewHolder;
import com.example.guest.posterpal.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingActivity extends AppCompatActivity {
    private DatabaseReference mPostReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

//    @Bind(R.id.categoriesRecyclerView) RecyclerView mCategoriesView;
    @Bind(R.id.trendingPostsRecyclerView) RecyclerView mPostsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        ButterKnife.bind(this);

        mPostReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POSTS);
        setupFirebaseAdapter();
    }

    private void setupFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolder>
                (Post.class, R.layout.post_list_item, FirebasePostViewHolder.class, mPostReference) {

            @Override
            protected  void populateViewHolder(FirebasePostViewHolder viewHolder, Post model, int position) {
                viewHolder.bindPost(model);
            }
        };
        mPostsView.setHasFixedSize(true);
        mPostsView.setLayoutManager(new LinearLayoutManager(this));
        mPostsView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}

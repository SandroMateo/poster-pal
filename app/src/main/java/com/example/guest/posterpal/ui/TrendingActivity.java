package com.example.guest.posterpal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.guest.posterpal.Constants;
import com.example.guest.posterpal.R;
import com.example.guest.posterpal.adapters.FirebaseCategoryViewHolder;
import com.example.guest.posterpal.adapters.FirebasePostViewHolder;
import com.example.guest.posterpal.models.Category;
import com.example.guest.posterpal.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingActivity extends AppCompatActivity {
    private DatabaseReference mPostReference;
    private FirebaseRecyclerAdapter mFirebasePostAdapter;
    private DatabaseReference mCategoryReference;
    private FirebaseRecyclerAdapter mFirebaseCategoryAdapter;

//    @Bind(R.id.categoriesRecyclerView) RecyclerView mCategoriesView;
    @Bind(R.id.trendingPostsRecyclerView) RecyclerView mPostsView;
    @Bind(R.id.trendingCategoryRecyclerView) RecyclerView mCategoriesView;
    @Bind(R.id.newPostButton) Button mNewPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        ButterKnife.bind(this);

        mPostReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POSTS);
        mCategoryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORIES);
        setupFirebaseAdapter();


        mNewPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrendingActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupFirebaseAdapter() {
        mFirebasePostAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolder>
                (Post.class, R.layout.post_list_item, FirebasePostViewHolder.class, mPostReference) {

            @Override
            protected  void populateViewHolder(FirebasePostViewHolder viewHolder, Post model, int position) {
                viewHolder.bindPost(model);
            }
        };
        mPostsView.setHasFixedSize(true);
        mPostsView.setLayoutManager(new LinearLayoutManager(this));
        mPostsView.setAdapter(mFirebasePostAdapter);

        mFirebaseCategoryAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_item, FirebaseCategoryViewHolder.class, mCategoryReference) {
            @Override
            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position) {
                viewHolder.bindCategory(model);
            }
        };
        mCategoriesView.setHasFixedSize(false);
        mCategoriesView.setLayoutManager(new LinearLayoutManager(this));
        mCategoriesView.setAdapter(mFirebaseCategoryAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebasePostAdapter.cleanup();
        mFirebaseCategoryAdapter.cleanup();
    }
}

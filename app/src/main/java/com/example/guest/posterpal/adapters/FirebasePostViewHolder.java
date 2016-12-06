package com.example.guest.posterpal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.posterpal.Constants;
import com.example.guest.posterpal.R;
import com.example.guest.posterpal.models.Post;
import com.example.guest.posterpal.ui.TrendingActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/6/16.
 */
public class FirebasePostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private Context mContext;
    private View mView;

    public FirebasePostViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPost(Post post) {
        ImageView postImageView = (ImageView) mView.findViewById(R.id.postImageView);
        TextView contentTextView = (TextView) mView.findViewById(R.id.contentTextView);
        TextView userTextView = (TextView) mView.findViewById(R.id.userTextView);
        TextView timestampTextView = (TextView) mView.findViewById(R.id.timestampTextView);

        Picasso.with(mContext)
                .load(post.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(postImageView);

        contentTextView.setText(post.getContent());
        userTextView.setText(post.getUser());
        timestampTextView.setText(post.getTimestamp());
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Post> posts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POSTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    posts.add(snapshot.getValue(Post.class));
                }
                int itemPostion = getLayoutPosition();

                Intent intent = new Intent(mContext, TrendingActivity.class);
                intent.putExtra("position", itemPostion + "");
                intent.putExtra("posts", Parcels.wrap(posts));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}

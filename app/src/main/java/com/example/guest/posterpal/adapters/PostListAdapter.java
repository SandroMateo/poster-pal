package com.example.guest.posterpal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.posterpal.R;
import com.example.guest.posterpal.models.Post;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/5/16.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Post> mPosts = new ArrayList<>();
    private Context mContext;

    public PostListAdapter(Context context, ArrayList<Post> posts) {
        mContext = context;
        mPosts = posts;
    }

    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostListAdapter.PostViewHolder holder, int position) {
        holder.bindPosts(mPosts.get(position));
    }

    @Override
    public int getItemCount() {return mPosts.size();}

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.postImageView) ImageView mPostImageView;
        @Bind(R.id.categoryTextView) TextView mCategoryView;
        @Bind(R.id.contentTextView) TextView mContentView;
        @Bind(R.id.timestampTextView) TextView mTimestampView;

        private Context mContext;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindPosts(Post post) {
            Picasso.with(mContext)
                    .load(Post.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mPostImageView);

            mCategoryView.setText(post.getCategory());
            mContentView.setText(Post.getContent());
            mTimestampView.setText(Post.getTimestamp());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PostDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("posts", Parcels.wrap(mPosts));
            mContext.startActivity(intent);
        }
    }

}

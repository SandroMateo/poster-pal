package com.example.guest.posterpal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.posterpal.Constants;
import com.example.guest.posterpal.R;
import com.example.guest.posterpal.models.Category;
import com.example.guest.posterpal.ui.CategoryActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 12/6/16.
 */
public class FirebaseCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Context mContext;
    View mView;

    public FirebaseCategoryViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mView = itemView;
        mView.setOnClickListener(this);
    }

    public void bindCategory(Category category) {
        TextView categoryTitle = (TextView) mView.findViewById(R.id.categoryTitle);
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Category> categories = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    categories.add(snapshot.getValue(Category.class));
                }
                int itemPostion = getLayoutPosition();

                Intent intent = new Intent(mContext, CategoryActivity.class);
                intent.putExtra("position", itemPostion + "");
                intent.putExtra("categories", Parcels.wrap(categories));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

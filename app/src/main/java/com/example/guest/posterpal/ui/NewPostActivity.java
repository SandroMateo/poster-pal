package com.example.guest.posterpal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.guest.posterpal.Constants;
import com.example.guest.posterpal.R;
import com.example.guest.posterpal.models.Category;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity {
    private DatabaseReference mCategoryReference;

    @Bind(R.id.usernameEditText) EditText mUserName;
    @Bind(R.id.contentEditText) EditText mContent;
    @Bind(R.id.imageUrlEditText) EditText mImageUrl;
    @Bind(R.id.categorySpinner) Spinner mCategorySpinner;
    @Bind(R.id.savePostButton) Button mSavePostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);

        mCategoryReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_CATEGORIES);

        mCategoryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> categories = new ArrayList<String>();
                for(DataSnapshot categorySnapshot: dataSnapshot.getChildren()) {
                    String title = categorySnapshot.child("title").getValue().toString();
                    categories.add(title);
                }

                ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(NewPostActivity.this, android.R.layout.simple_spinner_item, categories);
                mCategorySpinner.setAdapter(categoryAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

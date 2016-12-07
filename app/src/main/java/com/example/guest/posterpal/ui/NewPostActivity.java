package com.example.guest.posterpal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.guest.posterpal.Constants;
import com.example.guest.posterpal.R;
import com.example.guest.posterpal.models.Category;
import com.example.guest.posterpal.models.Post;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity {
    private DatabaseReference ref;


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

        ref = FirebaseDatabase.getInstance().getReference();

        ref.child(Constants.FIREBASE_CHILD_CATEGORIES).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> categories = new ArrayList<String>();
                for(DataSnapshot categorySnapshot: dataSnapshot.getChildren()) {
                    String title = categorySnapshot.getKey().toString();
                    categories.add(title);
                }

                ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(NewPostActivity.this, android.R.layout.simple_spinner_item, categories);
                mCategorySpinner.setAdapter(categoryAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mSavePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = mCategorySpinner.getSelectedItem().toString();
                String username = mUserName.getText().toString();
                String content = mContent.getText().toString();
                String imageUrl = mImageUrl.getText().toString();
                String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                Post newPost = new Post(content, username, timestamp, imageUrl);
                savePostToFirebase(newPost, category);
            }
        });
    }

    public void savePostToFirebase(Post post, String category) {
        ref.child(Constants.FIREBASE_CHILD_CATEGORIES).child(category).push().setValue(post);
    }
}

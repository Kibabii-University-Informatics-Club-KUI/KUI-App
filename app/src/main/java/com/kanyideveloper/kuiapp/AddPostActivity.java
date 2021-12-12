package com.kanyideveloper.kuiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanyideveloper.kuiapp.databinding.ActivityAddPostBinding;
import com.kanyideveloper.kuiapp.databinding.ActivityRegisterBinding;

public class AddPostActivity extends AppCompatActivity {

    ActivityAddPostBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String userId;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();

        fetchUserName();

        binding.buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.edtPostTitle.getText().toString();
                String description = binding.editPostDescription.getText().toString();

                if (title.isEmpty()){
                    binding.edtPostTitle.setError("Empty Title");
                }
                else if (description.isEmpty()){
                    binding.editPostDescription.setError("Empty Description");
                }else{

                    if (userName != null){

                        Post post = new Post(title, description, userName);
                        databaseReference.child("posts").push().setValue(post);
                        Toast.makeText(getApplicationContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void fetchUserName(){
        databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    userName = user.getUserName();
                }else{
                    Toast.makeText(getApplicationContext(), "Data Snapshot does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
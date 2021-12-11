package com.kanyideveloper.kuiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanyideveloper.kuiapp.databinding.ActivityAddPostBinding;
import com.kanyideveloper.kuiapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ArrayList<Post> postsList = new ArrayList();
    PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("posts");
        getPosts();

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getPosts(){

        postsList.clear();
        binding.postProgressBar.setVisibility(View.VISIBLE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    for (DataSnapshot i: snapshot.getChildren()){
                        Post post = i.getValue(Post.class);
                        postsList.add(post);
                    }

                    binding.postProgressBar.setVisibility(View.GONE);
                    postsAdapter = new PostsAdapter(postsList);
                    binding.postsRecyclerView.setAdapter(postsAdapter);

                }else{
                    binding.postProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No posts yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout){
            firebaseAuth.signOut();
            Intent intent = new Intent(MainActivity.this, AuthBoardActivity.class);
            startActivity(intent);
            finish();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}
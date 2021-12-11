package com.kanyideveloper.kuiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kanyideveloper.kuiapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    //A Firebase reference represents a particular location in your Database and can be used for reading or writing data to that Database location. This class is the starting point for all Database operations.
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editRegisterUserName.getText().toString();
                String regNo = binding.editRegisterRegNo.getText().toString();
                String phoneNo = binding.edtRegisterPhoneNum.getText().toString();
                String email = binding.editRegisterEmail.getText().toString();
                String password = binding.edtRegisterPassword.getText().toString();

                if (username.isEmpty()){
                    binding.editRegisterUserName.setError("Empty Username");
                }
                else if (regNo.isEmpty()){
                    binding.editRegisterRegNo.setError("Empty Reg No");
                }
                else if (phoneNo.isEmpty()){
                    binding.edtRegisterPhoneNum.setError("Empty Phone Number");
                }
                else if (email.isEmpty()){
                    binding.editRegisterEmail.setError("Empty Email Address");
                }
                else if (password.isEmpty()){
                    binding.edtRegisterPassword.setError("Empty Password");
                }
                else if (password.length() < 8){
                    binding.edtRegisterPassword.setError("Too weak password, use 8 characters");
                }else{

                    binding.RegisterProgressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                String userId = firebaseAuth.getCurrentUser().getUid();
                                User user = new User(userId, username, email, phoneNo, regNo);
                                databaseReference.child(userId).setValue(user);

                                Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                firebaseAuth.signOut();

                                binding.RegisterProgressBar.setVisibility(View.INVISIBLE);

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();

                            }else{
                                binding.RegisterProgressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        binding.textViewHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
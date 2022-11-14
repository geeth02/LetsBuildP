package com.example.letsbuild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {
    private TextView banner,registerUser;
    private EditText editTextFullName,editTextEmail,editTextPassword,editeTextRePassword;
    private FirebaseAuth mAuth;

 //   DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        banner=(TextView)findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser=(Button) findViewById(R.id.btnAddInfo);
        registerUser.setOnClickListener(this);

        editTextFullName=(EditText) findViewById(R.id.eTName);
        editTextEmail=(EditText) findViewById(R.id.eTPhoneNum);
        editTextPassword=(EditText) findViewById(R.id.eTAddress1);
        editeTextRePassword=(EditText) findViewById(R.id.eTAddress2);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this,Login.class));
                break;
            case R.id.btnAddInfo:
                userRegister();
                break;



        }
    }

    private void userRegister() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String rePassword = editeTextRePassword.getText().toString().trim();
        String name = editTextFullName.getText().toString().trim();

        if (name.isEmpty()) {
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;

        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if (rePassword.isEmpty()) {
            editeTextRePassword.setError("Password is required!");
            editeTextRePassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
        if (!password.equals(rePassword)) {
            editeTextRePassword.setError("Password not matched!");
            editeTextRePassword.requestFocus();
            return;
        } else {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(name, email);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                            FirebaseAuth.getInstance().signOut();
                                            finish();
                                            Intent intent = new Intent(Register.this, Login.class);
                                            startActivity(intent);
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

}

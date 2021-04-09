package com.example.semester_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText t1,t2;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=(EditText)findViewById(R.id.editTextTextEmailAddress);
        t2=(EditText)findViewById(R.id.editTextTextPassword);
        btn1=(Button)findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t1.getText().toString().trim();
                String password  = t2.getText().toString().trim();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //display some message here

                                    Toast.makeText(login.this,"Successfully Login",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(login.this,MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    //display some message here
                                    Toast.makeText(login.this,"Login Error",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


    }

    public void yes(View view) {
        Intent intent=new Intent(login.this,signup.class);
        startActivity(intent);
    }
}
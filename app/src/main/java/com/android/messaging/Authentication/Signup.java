package com.android.messaging.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.messaging.Database.Message;
import com.android.messaging.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
EditText email,pass,uname;
Button regi ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    email=findViewById(R.id.emailr);
    pass=findViewById(R.id.passr);
    regi=findViewById(R.id.register);
    uname=findViewById(R.id.uname);
    mAuth=FirebaseAuth.getInstance();
    regi.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            signup();
        }
    });

    }

    private void trans()
    {
        Intent intent = new Intent(Signup.this, Message.class);
        startActivity(intent);
        finishAffinity();
    }
    public void signup()
    {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(Signup.this,"Sign Up Succesful",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("Users").child(task.getResult().getUser().getUid()).setValue(uname.getText().toString());
                    trans();
                }
                else {
                    Toast.makeText(Signup.this,"Unable to sign up",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

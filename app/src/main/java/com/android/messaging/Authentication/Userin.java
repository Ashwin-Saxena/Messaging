package com.android.messaging.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.messaging.Database.Message;
import com.android.messaging.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Userin extends AppCompatActivity {
Button signi;
TextView t1;
private FirebaseAuth mAuth;
EditText email_id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userin);

        t1=findViewById(R.id.textView3);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Userin.this, Signup.class);
                startActivity(i);
                finishAffinity();
            }
        });
        signi=findViewById(R.id.buttonsignin);
        email_id=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        mAuth=FirebaseAuth.getInstance();
        signi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth=FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email_id.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Userin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Userin.this, "Authentication failed. \n "+task.getException(),Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });


    }

    public void updateUI(FirebaseUser user)
    {
        Intent intent=new Intent(Userin.this, Message.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            updateUI(currentUser);
        }
    }
}

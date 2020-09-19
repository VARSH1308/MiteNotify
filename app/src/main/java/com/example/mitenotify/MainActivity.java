package com.example.mitenotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText em;
    private EditText password;
    private Button login;
    private Button reg;
    private Intent intent_admin_page;
    private Intent intent_user_page;
    private Intent intent_signup_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        em = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        login = (Button) findViewById(R.id.login);
        intent_admin_page = new Intent(MainActivity.this,HomePage.class);
        intent_signup_page = new Intent(MainActivity.this, SignUpActivity.class);

       // intent_user_page = new Intent(MainActivity.this,ViewCircularMsg.class);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String email= em.getText().toString();
                final String pwd = password.getText().toString();
                if(email!=null)
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        String pwd1 = pwd;
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                Toast.makeText(MainActivity.this,pwd1,Toast.LENGTH_LONG).show();
                            if(pwd1.equals("admin123"))
                                startActivity(intent_admin_page);
                            else
                                startActivity(intent_user_page);
//comment
                        }
                    });
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(intent_signup_page);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
            ;//startActivity(intent);
    }
}
package com.it342.sugarsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogIn extends AppCompatActivity {
      TextView CreateAccount ;
      Button signIn;
      Intent intent;
      TextInputEditText email,password;
    private FirebaseAuth mAuth =FirebaseAuth.getInstance();
    final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        CreateAccount=findViewById(R.id.CreateAccount);
        signIn=findViewById(R.id.signIn);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        awesomeValidation.addValidation(this, R.id.email, RegexTemplate.NOT_EMPTY,R.string.Invalid_Required);
        awesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY,R.string.Invalid_Required);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                intent = new Intent(getBaseContext(), MainPage.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LogIn.this, getResources().getString(R.string.invalid_login), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                intent = new Intent(getBaseContext(), Sign_Up_Page.class);
                startActivity(intent);
            }
        });
    }
}
package com.it342.sugarsafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.it342.sugarsafe.Models.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Sign_Up_Page extends AppCompatActivity {
         TextView back;
         TextInputEditText userName,email,age,password,confirmPassword;
         CollectionReference firebaseFirestore=FirebaseFirestore.getInstance().collection("USER");;
         Button signUp;
    final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    private FirebaseAuth mAuth =FirebaseAuth.getInstance();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);
        back =findViewById(R.id.back) ;
        userName=findViewById(R.id.userName) ;
        email =findViewById(R.id.email);
        age =  findViewById(R.id.age);
        password =findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        signUp=findViewById(R.id.signUp);

        validation();

        signUp.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          if(awesomeValidation.validate()){
                                          mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                              @Override
                                              public void onComplete(@NonNull Task<AuthResult> task) {
                                                  if (task.isSuccessful()) {
                                                      // Sign in success, update UI with the signed-in user's information

                                                      FirebaseUser user = mAuth.getCurrentUser();
                                                      User userInfo = new User(user.getUid(), userName.getText().toString(), email.getText().toString(), age.getText().toString(), password.getText().toString());
                                                      addUserInfo(userInfo);


                                                  } else {
                                                      Toast.makeText(Sign_Up_Page.this, "happened Error Please check on Internet", Toast.LENGTH_SHORT).show();

                                                  }
                                              }
                                          });

                                      }}
                                  });
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onBackPressed();
                            }
                        });




    }
    private void addUserInfo(User userInfo){
        firebaseFirestore.document(userInfo.getId()).set(userInfo.getUserAfterMapping()).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Sign_Up_Page.this, getResources().getString(R.string.succesfully), Toast.LENGTH_SHORT).show();

                        Intent  intent = new Intent(getBaseContext(), LogIn.class);
                        startActivity(intent);
                    }
                });

    }
    private void validation(){
        String regexPassword="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
        awesomeValidation.addValidation(this, R.id.userName, RegexTemplate.NOT_EMPTY,R.string.Invalid_Required);
        awesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY,R.string.Invalid_Required);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS ,R.string.Invalid_Emil);
        awesomeValidation.addValidation(this, R.id.password, regexPassword, R.string.Invalid_Password);
        awesomeValidation.addValidation(this, R.id.confirmPassword, R.id.password, R.string.invalid_confirm_password);
    }
    }
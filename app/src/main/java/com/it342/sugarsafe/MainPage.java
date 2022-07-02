package com.it342.sugarsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainPage extends AppCompatActivity {
    TextView back;
    Button bloodSugarLevel , InsulinRatio;
            Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bloodSugarLevel =findViewById(R.id.bloodSugarLevel) ;
        InsulinRatio =findViewById(R.id.InsulinRatio) ;
        back =findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bloodSugarLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), BloodSugarLevel.class);
                startActivity(intent);
            }
        });

        InsulinRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), Insulin_Ratio.class);
                startActivity(intent);
            }
        });
    }
    
}
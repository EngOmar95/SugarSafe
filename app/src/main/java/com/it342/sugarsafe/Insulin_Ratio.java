package com.it342.sugarsafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import com.google.android.material.textfield.TextInputEditText;


public class Insulin_Ratio extends AppCompatActivity {
  Button calculate;
  TextInputEditText doesOne, doestwo,Weight;
    TextView textDoesOne, textDoesTwo ,back;
    final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin__ratio);
        calculate= findViewById(R.id.calculate);
        doesOne= findViewById(R.id.doesOne);
        doestwo= findViewById(R.id.doestwo);
        textDoesOne= findViewById(R.id.textDoesOne);
        textDoesTwo= findViewById(R.id.textDoesTwo);
        Weight= findViewById(R.id.Weight);
        back =findViewById(R.id.back) ;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        awesomeValidation.addValidation(this, R.id.Weight
                , RegexTemplate.NOT_EMPTY, R.string.Invalid_Required);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                doesOne.setVisibility(View.VISIBLE);
                doestwo.setVisibility(View.VISIBLE);
                double TDD=getTDD(Integer.parseInt(Weight.getText().toString()));
                doesOne.setText(getTakeDoseThreeTimes(TDD)+" UI");
                doestwo.setText(getTakeDose(TDD)+" UI");
            }else {
                    doesOne.setVisibility(View.GONE);
                    doestwo.setVisibility(View.GONE);
                    textDoesOne.setText("");
                    textDoesTwo.setText("");
                    Toast.makeText(Insulin_Ratio.this, "You Must Enter You Wight", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public double getTDD(int weight){
        //Total Daily Does ( TDD ) = 0.5 * patient weight kg = …. IU per day
        return 0.5* weight;
    }

    public String getTakeDoseThreeTimes(double TDD){
        /*  1- To calculate the dose to be taken 3 times a day before every meal:
            30 * 50% = 15 IU
    Then divide the result by 3:
            15 / 3 = 5 IU
    So the user will take 5 IU 3 times before meals.*/
        String doseNumber=String.format("%.2f",  (TDD*0.5)/ 3);
        textDoesOne.setText("You Should Take "+doseNumber+" IU times before meals.");
        return doseNumber;
    }

    public String getTakeDose(double TDD){
       /* 2- To calculate the dose to be taken one time a day before sleep:
        30 * 50% = 15 IU*/
        String doseNumber=String.format("%.2f",  TDD*0.5);
        textDoesTwo.setText("You Should Take "+doseNumber+" IU one time a day before sleep.");
        return doseNumber;
    }



}
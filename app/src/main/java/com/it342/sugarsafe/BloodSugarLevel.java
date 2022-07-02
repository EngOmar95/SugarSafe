package com.it342.sugarsafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import com.google.android.material.textfield.TextInputEditText;


public class BloodSugarLevel extends AppCompatActivity {

    String[] items={"Fasting Blood Glucose","2-h after meals", "Select"};
    AutoCompleteTextView select;
    String Item="Select";
    TextView info,back;
     Button calculate;
     TextInputEditText percentageSugar;
    ArrayAdapter<String> AdapterItems;
    AppCompatImageView image;
    final AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blood_sugar_level);
        select=findViewById(R.id.select);
        info=findViewById(R.id.info);
        calculate= findViewById(R.id.calculate);
        percentageSugar=findViewById(R.id.percentageSugar);
        AdapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        select.setAdapter(AdapterItems);
        image=   findViewById(R.id.image);
        info.setMovementMethod(new ScrollingMovementMethod());
        back =findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        awesomeValidation.addValidation(this, R.id.percentageSugar
                , RegexTemplate.NOT_EMPTY,R.string.Invalid_Required);
        select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item=  adapterView.getItemAtPosition(i).toString();

              //  Toast.makeText(BloodSugarLevel.this, Item, Toast.LENGTH_SHORT).show();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
              int valuepercentage=  Integer.parseInt(percentageSugar.getText().toString());
                if(Item.equals("Fasting Blood Glucose")){
                    getCalculateFastingBloodGlucose(valuepercentage);
                }else if(Item.equals("2-h after meals")){
                    getCalculate2hAfterMeals(valuepercentage);
                }else if(Item.equals("Select")){
                    Toast.makeText(BloodSugarLevel.this, "You Must Select Type", Toast.LENGTH_SHORT).show();

                }
            }}
        });

    }
    public  void getCalculateFastingBloodGlucose (int percentageSugar ){
        if (percentageSugar < 100) {
            info.setText(getResources().getString(R.string.sugarNormal));
            info.setTextColor(getResources().getColor(R.color.green));
            image.setImageDrawable(getDrawable(R.drawable.good));
        } else if ( percentageSugar >= 100 && percentageSugar <= 125) {
            info.setText(getResources().getString(R.string.preDiabetesStage));
            info.setTextColor(getResources().getColor(R.color.orange));
            image.setImageDrawable(getDrawable(R.drawable.sick));
        }else if ( percentageSugar >= 126){
            image.setImageDrawable(getDrawable(R.drawable.ambulance));
            info.setText(getResources().getString(R.string.sugarHigh));
            info.setTextColor(getResources().getColor(R.color.red));
        }

    }

    public  void getCalculate2hAfterMeals (int percentageSugar ){
        if (percentageSugar < 140) {

            info.setText(getResources().getString(R.string.sugarNormal));
            image.setImageDrawable(getDrawable(R.drawable.good));
            info.setTextColor(getResources().getColor(R.color.green));
        } else if ( percentageSugar >= 140 && percentageSugar <= 199) {
            info.setText(getResources().getString(R.string.preDiabetesStage));
            image.setImageDrawable(getDrawable(R.drawable.sick));
            info.setTextColor(getResources().getColor(R.color.orange));
        }else if ( percentageSugar >= 200){
            info.setText(getResources().getString(R.string.sugarHigh));
            info.setTextColor(getResources().getColor(R.color.red));
            image.setImageDrawable(getDrawable(R.drawable.ambulance));

        }

    }
}
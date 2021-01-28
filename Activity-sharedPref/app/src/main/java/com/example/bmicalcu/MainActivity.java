package com.example.bmicalcu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText height, weight, fullname;
    TextView result, result2, fullnameResult;
    Button btnCalculate;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        fullname = (EditText) findViewById(R.id.fullname);
        result = (TextView) findViewById(R.id.result);
        result2 = (TextView) findViewById(R.id.result2);
        fullnameResult = (TextView) findViewById(R.id.fullnameResult);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        String Sfullname=sharedPreferences.getString("Sfullname", null);
        String Sweight=sharedPreferences.getString("Sweight", null);
        String Sheight=sharedPreferences.getString("Sheight", null);



          fullname.setHint(Sfullname);
          weight.setHint(Sweight);
          height.setHint(Sheight);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Sfullname", fullname.getText().toString());
                editor.putString("Sweight",weight.getText().toString());
                editor.putString("Sheight",height.getText().toString());



                editor.commit();

                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();
                String fullnameStr = fullname.getText().toString().trim();



                if (TextUtils.isEmpty(heightStr)) {
                    height.setError("Please enter your height");
                    height.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(weightStr)) {
                    weight.setError("Please enter your weight");
                    weight.requestFocus();
                    return;
                }

                if (heightStr != null && !"".equals(heightStr) && weightStr != null && !"".equals(weightStr)) {
                    float heightValue = Float.parseFloat(heightStr) / 100;
                    float weightValue = Float.parseFloat(weightStr);


                    float bmi = weightValue / (heightValue * heightValue);
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(2);

                    fullnameResult.setText("NAME: " + fullnameStr);
                    result.setText(String.valueOf(nf.format(bmi)));

                }

            }

        });
    }



}




package com.example.infration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //EditText editText;

    TextView text;
    TextView text2;

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //editText = findViewById(R.id.editTextTextPersonName);

        //editText.setText("What is this?");

        setContentView(R.layout.linear);

        text = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        text.setText("Covid19");
        text.setText("korea");

        et = findViewById(R.id.editTextTextPersonName2);
        et.setText("KuWang");
    }
}
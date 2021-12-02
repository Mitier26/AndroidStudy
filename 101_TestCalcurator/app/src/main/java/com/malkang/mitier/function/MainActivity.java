
package com.malkang.mitier.function;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    TextView timeView, resultView;
    EditText inputNum1, inputNum2;
    Button resetBtn;
    Button plu, min, mul, div, rem;

    int num1, num2;

    String TAG  = "AAAAAAA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeView = findViewById(R.id.textView);
        inputNum1 = findViewById(R.id.editTextNumber);
        inputNum2 = findViewById(R.id.editTextNumber2);
        resetBtn = findViewById(R.id.button11);
        resultView = findViewById(R.id.textView5);

        plu = findViewById(R.id.button5);
        min = findViewById(R.id.button6);
        mul = findViewById(R.id.button7);
        div = findViewById(R.id.button8);
        rem = findViewById(R.id.button9);

        timeView.setText(GetTime());

        //Scanner sc = new Scanner(System.in);

        plu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check())
                {
                    SetNum();

                    int result = num1 + num2;

                    resultView.setText("" + result);
                }

            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check())
                {
                    SetNum();

                    int result = num1 - num2;

                    resultView.setText("" + result);
                }

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check())
                {
                    SetNum();

                    float result = (float)num1 * num2;

                    resultView.setText("" + result);
                }

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check())
                {
                    SetNum();

                    float result = (float)num1 / num2;

                    resultView.setText("" + result);
                }

            }
        });

        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Check())
                {
                    SetNum();

                    float result = (float)num1 % num2;

                    resultView.setText("" + result);
                }

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputNum1.setText("");
                inputNum2.setText("");
                resultView.setText("");

            }

        });

    }

    public boolean Check()
    {
        if(inputNum1.getText().length() == 0)
        {
            return false;
        }
        else if(inputNum2.getText().length() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void SetNum()
    {
        String strNum1 = inputNum1.getText().toString().trim();
        String strNum2 = inputNum2.getText().toString().trim();
        num1 = Integer.parseInt(strNum1);
        num2 = Integer.parseInt(strNum2);
    }

    public String GetTime()
    {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String getTime = dateFormat.format(date);
        return getTime;
    }
}
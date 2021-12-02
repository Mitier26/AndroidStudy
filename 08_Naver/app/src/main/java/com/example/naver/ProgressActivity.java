package com.example.naver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProgressActivity extends AppCompatActivity {

    TextView idAndpw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        idAndpw = findViewById(R.id.textView13);
        String strid = "aaaa";
        String strpw = "1111";

        Intent intent = getIntent();
        idAndpw.setText("ID : " + intent.getStringExtra("id") + "PW : " + intent.getStringExtra("pw"));

        if(intent.getStringExtra("id").equals(strid) && intent.getStringExtra("pw").equals(strpw))
        {
            setResult(RESULT_OK);
        }
        else
        {
            setResult(RESULT_CANCELED);
        }
        finish();

    }
}
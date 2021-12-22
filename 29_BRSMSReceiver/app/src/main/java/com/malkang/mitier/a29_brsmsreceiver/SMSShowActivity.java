package com.malkang.mitier.a29_brsmsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SMSShowActivity extends AppCompatActivity {

    EditText content;
    EditText sender;
    EditText date;
    Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsshow);

        Intent receivedIntent = getIntent();
        sender = findViewById(R.id.editTextTextPersonName);
        content = findViewById(R.id.editTextTextMultiLine);
        date = findViewById(R.id.editTextTextPersonName2);
        okBtn = findViewById(R.id.button);

        sender.setText(receivedIntent.getStringExtra("sender"));
        content.setText(receivedIntent.getStringExtra("contents"));
        date.setText(receivedIntent.getStringExtra("receivedDate"));

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@@@@@@@@@@@@@", "확인 버튼 클릭");
                finish();
            }
        });

    }
}
package kr.or.bukedu.mitier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sub2Activity extends AppCompatActivity {

    Button sub1Btn, sub2Btn, sub3Btn, mainBtn;
    TextView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        sub1Btn = findViewById(R.id.button7);
        sub2Btn = findViewById(R.id.button8);
        sub3Btn = findViewById(R.id.button9);
        mainBtn = findViewById(R.id.button4);
        dataView = findViewById(R.id.textView6);

        Intent intent = getIntent();

        dataView.setText("" + intent.getIntExtra("data2",0));

        sub1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sub1Activity.class);
                startActivity(intent);
            }
        });

        sub2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sub2Activity.class);
                startActivity(intent);
            }
        });

        sub3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sub3Activity.class);
                startActivity(intent);
            }
        });

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
package kr.or.bukedu.mitier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button sub1Btn;
    Button sub2Btn;
    Button sub3Btn;
    Button menuBtn;

    EditText id, pw;

    String TAG  = "aaaaaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sub1Btn = findViewById(R.id.button9);
        sub2Btn = findViewById(R.id.button2);
        sub3Btn = findViewById(R.id.button3);

        menuBtn = findViewById(R.id.button14);

        id = findViewById(R.id.editTextTextPersonName);
        pw = findViewById(R.id.editTextTextPassword);

        sub1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sub1Activity.class);
                intent.putExtra("data", "iot");
                startActivity(intent);
            }
        });

        sub2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Sub2Activity.class);
                intent.putExtra("data2", 26);
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

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("id", "mitier");
                intent.putExtra("pw", "1234");

                intent.putExtra("inputId", id.getText().toString());
                intent.putExtra("inputPw", pw.getText().toString());

                startActivity(intent);
            }
        });

    }
}
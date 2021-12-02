package kr.or.bukedu.mitier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    TextView idText, pwText, inputId, inputPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        idText = findViewById(R.id.textView8);
        pwText = findViewById(R.id.textView9);
        inputId = findViewById(R.id.textView10);
        inputPw = findViewById(R.id.textView11);

        Intent intent = getIntent();
        idText.setText("ID : " + intent.getStringExtra("id"));
        pwText.setText("PW : " + intent.getStringExtra("pw"));

        inputId.setText("ID : " + intent.getStringExtra("inputId"));
        inputPw.setText("PW : " + intent.getStringExtra("inputPw"));

    }
}
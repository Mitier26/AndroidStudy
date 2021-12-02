package kr.or.bukedu.mitier.newnaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProcessActivity extends AppCompatActivity {

    String TAG = "ProcessActivity***";
    String id, pw;

    TextView idView, pwView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        idView = findViewById(R.id.textView9);
        pwView = findViewById(R.id.textView11);

        Intent intent = getIntent();

        id = intent.getStringExtra("id");
        pw = intent.getStringExtra("pw");

        idView.setText(id);
        pwView.setText(pw);
    }
}
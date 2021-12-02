package kr.or.bukedu.mitier.a15_agree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    Button resultBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 기본값으로 설정
        setResult(RESULT_CANCELED);
        resultBtn = findViewById(R.id.button);

        Intent intent = getIntent();

        setTitle(intent.getStringExtra("title"));

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });


    }
}
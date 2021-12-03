package kr.or.bukedu.mitier.a14_join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SubActivity extends AppCompatActivity {


    String TAG = "SubActivity*****";
    Button resultBtn;
    CheckBox check;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        resultBtn = findViewById(R.id.button);
        check = findViewById(R.id.checkBox3);
        // 체크 박스에 체크되어 있으면 OK

        intent = getIntent();
        setTitle(intent.getStringExtra("title"));

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(check.isChecked() == true)
                {
                    Log.d(TAG,"확인 버튼");
                    setResult(RESULT_OK);
                }
                else
                {
                     Log.d(TAG, "실패");
                    setResult(RESULT_CANCELED);
                }

                Log.d(TAG, "종료");
                finish();
            }
        });

    }

}
package kr.or.bukedu.mitier.a15_agree;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5;
    TextView textView1, textView2, textView3, textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView9);
        textView2 = findViewById(R.id.textView10);
        textView3 = findViewById(R.id.textView11);
        textView4 = findViewById(R.id.textView12);

        btn1 = findViewById(R.id.button6);
        btn2 = findViewById(R.id.button7);
        btn3 = findViewById(R.id.button8);
        btn4 = findViewById(R.id.button9);
        btn5 = findViewById(R.id.button10);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("title", textView1.getText());
                startActivityForResult(intent, 1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("title", textView2.getText());
                startActivityForResult(intent, 2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("title", textView3.getText());
                startActivityForResult(intent, 3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("title", textView4.getText());
                startActivityForResult(intent, 4);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn1.getText().equals("확인완료")
                && btn2.getText().equals("확인완료")
                && btn3.getText().equals("확인완료")
                && btn4.getText().equals("확인완료"))
                {
                    // 다음화면
                    Snackbar.make(v, "다음 진행", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    Snackbar.make(v, "약관확인", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == 1)
            {
                btn1.setBackgroundColor(Color.RED);
                btn1.setText("확인완료");
            }
            else if (requestCode ==2 )
            {
                btn2.setBackgroundColor(Color.RED);
                btn2.setText("확인완료");
            }
            else if (requestCode ==3 )
            {
                btn3.setBackgroundColor(Color.RED);
                btn3.setText("확인완료");
            }
            else if (requestCode ==4 )
            {
                btn4.setBackgroundColor(Color.RED);
                btn4.setText("확인완료");
            }
        }
    }
}

package com.malkang.mitier.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //츨력 하는 것
    TextView text;
    ImageView image;

    // 입력 받는 것
    EditText editText;

    // 버튼 3개
    Button textBtn;
    Button imageBtn;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id 를 이용하여 각 요소들이 무엇인지 가지고 온다.
        text = findViewById(R.id.textView);
        image = findViewById(R.id.imageView);

        editText = findViewById(R.id.editTextTextPersonName);

        textBtn = findViewById(R.id.button5);
        imageBtn = findViewById(R.id.button6);
        nextBtn = findViewById(R.id.button7);
        // id 는 화면 구성에 있는 id와 동일 하게 맞추어 준다.

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 에디트 텍스트에 글자 입력이 있는지 확인한다.
                if(editText.getText().length() != 0)
                {
                    // 텍스트 뷰에 텍스트를 세팅 하는 것
                    text.setText(editText.getText());
                }
                else
                {
                    text.setText("글자를 입력하세요");
                }

            }
        });

        textBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(),"함정", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 랜덤은 0 ~ 1 사이의 값이 나오는 double 값이다.
                int r = (int)(Math.random() *255);
                int g = (int)(Math.random() *255);
                int b = (int)(Math.random() *255);

                // 화면에 잠깐 팝업을 출력 해 주는 것
                Toast.makeText(getApplicationContext(), r + "+" + g + "+" + b, Toast.LENGTH_SHORT).show();

                // 배경의 색을 변경해 준다.
                image.setBackgroundColor(Color.rgb(r,g,b));
            }
        });

        imageBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                image.setBackgroundColor(Color.WHITE);
                return true;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다른 화면으로 이동하는 부분
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

    }


}
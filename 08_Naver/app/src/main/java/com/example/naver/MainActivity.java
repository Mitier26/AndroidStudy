package com.example.naver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

// xml에서는 화면 디자인, 구성.view들을 배치
// 자바 파일레서는 기능 정의, 동작 정의

public class MainActivity extends AppCompatActivity {

    final int REQUEST_PROCESS_LOGIN = 1;
    ImageView imageview;
    EditText editTextId;
    TextView textViewId;
    TextView textViewPw;
    EditText editTextPw;
    ImageButton imageButton;
    Button button;

    String TAG = "MainActivity***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ctrl + Q 도움말말
        imageview = findViewById(R.id.imageView);
        editTextId = findViewById(R.id.editTextTextPersonName);
        textViewId = findViewById(R.id.textView);
        textViewPw = findViewById(R.id.textView2);
        editTextPw = findViewById(R.id.editTextTextPassword);
        imageButton = findViewById(R.id.imageButton);
        button = findViewById(R.id.button2);


        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NaverActivity.class);
                startActivity(intent);
            }
        });


        imageButton.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = editTextId.getText().toString();
                        String pw = editTextPw.getText().toString();
                        Intent intent = new Intent(getApplicationContext(), ProgressActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("pw", pw);
                        startActivityForResult(intent,REQUEST_PROCESS_LOGIN);
                        //startActivity(intent);
                    }

                }
        );



        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 클릭의 이벤트가 발생하면 어떻게 해야해 라고 정의함.
                String id = editTextId.getText().toString();
                // 사용자가 입력한 id값을 editText에서 얻어옴
                String pw = editTextPw.getText().toString();
                Log.d(TAG, id + "/" + pw);
                Toast.makeText(getApplicationContext(), id + "/" + pw, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        imageButton.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
            }
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "엄마" + "/" + "아빠",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        button.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(getApplicationContext(),"고길동/고철수/고영희", Toast.LENGTH_LONG).show();
//                return true;
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQUEST_PROCESS_LOGIN:
                if(resultCode == RESULT_OK)
                {
                    Intent intent = new Intent(getApplicationContext(), IndexActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (resultCode == RESULT_CANCELED)
                {
                    editTextId.setText("");
                    editTextPw.setText("");
                    editTextId.requestFocus();

                }
                break;
        }
    }
}
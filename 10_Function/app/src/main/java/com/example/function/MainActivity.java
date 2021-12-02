package com.example.function;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    
    Button btn3;    // 버튼 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        btn3  = findViewById(R.id.button3); // 버튼 인프레이션
        //Check();
    }

    void Check()
    {
        char ch = 'a';
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        while((char)(input) != 'q')
        {
            System.out.println((ch));
            input = sc.nextInt();
        }
    }

    public void onBtnClick(View view)
    {
        view.setBackgroundColor(Color.BLUE);
        ((Button)view).setText("blue");
        textView.setText("IOT");
    }

    public void onBtnJoin(View view)
    {
        view.setBackgroundColor((Color.GREEN));
        textView2.setText("성공");
    }

    public void onBtnSearchId(View view)
    {
        view.setBackgroundColor(Color.RED);

    }

    public void onBtnSearchPw(View view)
    {
        view.setBackgroundColor(Color.YELLOW);
        ((Button)view).setText("Yellow");
        textView.setText("서치");
        textView2.setText("서치서치");
        btn3.setBackgroundColor(Color.GRAY);    //버턴 색 변경
    }
}
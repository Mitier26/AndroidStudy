package kr.or.bukedu.mitier.a14_join;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    String TAG = "AAAAAAAAA";

    Button nextButton;

    int[] checkBoxId = {R.id.checkBox1,R.id.checkBox2,R.id.checkBox3,R.id.checkBox4};
    int[] buttonId = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
    String[] titleText = {"전자금융거래 기본약관", "전자뱅킹서비스 이용약관", "위치기반 서비스 이용약관","우리스마트뱅킹 사용 유의사항" };
    CheckBox[] checkBoxes = new CheckBox[checkBoxId.length];
    Button[] buttons = new Button[buttonId.length];

    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), SubActivity.class);

        for(int i = 0; i< buttons.length; i++)
        {
            buttons[i] = findViewById(buttonId[i]);
        }
        for(int i = 0; i < 4 ; i ++)
        {
            checkBoxes[i] = findViewById(checkBoxId[i]);
        }

        nextButton = findViewById(R.id.button5);

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title", "전자금융거래 기본약관");
                startActivityForResult(intent, 0);
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title", "전자뱅킹서비스 이용약관");
                startActivityForResult(intent, 1);
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title", "위치기반 서비스 이용약관");
                startActivityForResult(intent, 2);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title", "우리스마트뱅킹 사용 유의사항");
                startActivityForResult(intent, 3);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < checkBoxes.length; i++)
                {

                    if(checkBoxes[i].isChecked() == false)
                    {
                        Snackbar.make(view, ""+titleText[i] + "확인해주세요.", Snackbar.LENGTH_LONG).show();
                        break;
                    }
                    else
                    {
                        // 다음 페이지
                    }
                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "" + requestCode);

        if(resultCode == RESULT_OK)
        {
            checkBoxes[requestCode].setChecked(true);
            buttons[requestCode].setBackgroundColor(Color.parseColor("#F06292"));
        }
        else
        {
            checkBoxes[requestCode].setChecked(false);
        }
    }
}
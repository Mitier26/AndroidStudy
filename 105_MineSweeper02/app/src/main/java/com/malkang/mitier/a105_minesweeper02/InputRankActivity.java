package com.malkang.mitier.a105_minesweeper02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputRankActivity extends AppCompatActivity {

    TextView timeText;
    EditText inputNameText;
    Button confirmButton;
    int keyNum =0;
    int maxRank = 10;
    int time= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_ranking);

        timeText = findViewById(R.id.textView8);
        inputNameText = findViewById(R.id.editTextTextPersonName);
        confirmButton = findViewById(R.id.button2);

        Intent intent = getIntent();
        time = intent.getIntExtra("time", 0);
        timeText.setText(time);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
            }
        });
    }

    public void SaveData()
    {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        int rankCount = pref.getInt("keyNum", 0);

        // 10개 이하면 데이터를 저장한다.
        // 10개 보다 많으면 데이터를 비교해야 한다.
        if(rankCount < maxRank)
        {
            editor.putString("saveData"+(rankCount+1), inputNameText +"/"+time);
            keyNum++;
        }
        else
        {
            // 데이터가 가득
            // 데이터를 전부 꺼내서 비교 하고 다시 넣어야?????
            for(int i = 1; i < rankCount; i++)
            {
                // 리스트에 저장된 데이
            }
        }

        editor.putInt("keyNum", keyNum);

    }
}
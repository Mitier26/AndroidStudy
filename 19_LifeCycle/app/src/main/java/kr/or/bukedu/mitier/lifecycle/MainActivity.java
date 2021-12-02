package kr.or.bukedu.mitier.lifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        Log.d("@@@@@@@@@@@@@@@" , "onCreate");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("고구마");
                // 화면을 회번하면 적용했던 값이 초기값으로 변한다.
                // 고구마가 없어진다.
                // 이유 onPause, onStop, onDestroy 가 실행되고
                // 다시 onCreate, onStart, onResume 이 실행된다.
                // 그래서 값이 사라진다.

//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu2Activity.class);
                startActivity(intent);

            }
        });
    }

    // 화면이 회전할 때 값이 사라지는 것을 방지하기 위한것
    // 데이터를 저장하는 것
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // onDestroy 이전에 실행된다.
        outState.putString("inputText", textView.getText().toString());
    }

    // 데이터를 불러오는 것
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // onStart 이후에 실행된다.
        if(savedInstanceState != null)
        {
            textView.setText(savedInstanceState.getString("inputText"));
        }
    }

    // 회면이 회전할 때 실행되는 것
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // 세로모드 일때 실행
       if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Log.d("@@@@@@@@@@@@@@@" , "세로모드");
        }
        // 가로모드 일때 실행
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Log.d("@@@@@@@@@@@@@@@" , "가로모드");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("@@@@@@@@@@@@@@@" , "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("@@@@@@@@@@@@@@@" , "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("@@@@@@@@@@@@@@@" , "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("@@@@@@@@@@@@@@@" , "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("@@@@@@@@@@@@@@@" , "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("@@@@@@@@@@@@@@@" , "onDestroy");
    }
}
package kr.or.bukedu.mitier.a99_testmove;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button moveBox, left, right;
    float startPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveBox = findViewById(R.id.button);
        left = findViewById(R.id.button3);
        right = findViewById(R.id.button2);

        startPos = moveBox.getTranslationX();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBox.setTranslationX(moveBox.getTranslationX() - 10);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBox.setTranslationX(moveBox.getTranslationX() + 10);
            }
        });

        moveBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBox.setTranslationX(startPos);
            }
        });

    }
}
package com.malkang.mitier.a17_minesweeper;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridLayout.LayoutParams gridLayout;
        gridLayout = new GridLayout.LayoutParams();

        //LinearLayout.LayoutParams lop = new LinearLayout.LayoutParams(20,20);

        GridLayout grid = findViewById(R.id.gridLayout);

        for(int i = 0 ; i <  20; i++)
        {
            ImageButton imageBtn = new ImageButton(this);

            imageBtn.setId(i);
            imageBtn.setImageDrawable(getDrawable(R.drawable.minesweeper_unopened_square));
            imageBtn.setScaleType(ImageView.ScaleType.FIT_XY);

            imageBtn.setMaxHeight(40);
            imageBtn.setMaxWidth(40);

            gridLayout.setMargins(0,0,0,0);
            grid.layout(0,0,0,0);

            imageBtn.setLayoutParams(gridLayout);


            grid.addView(imageBtn);
            Log.d("@@@@@@@@@@@@@@@@@", ""+i);
        }
    }
}
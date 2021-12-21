package com.malkang.mitier.a27_brbatterychangedlow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryStateActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_state);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String levelStr = intent.getDataString();

        int percent = Integer.parseInt(levelStr);
        //int level = Integer.valueOf(levelStr);
        //float fLevel = Float.valueOf(levelStr)

        int [] img = {R.drawable.battery1,R.drawable.battery2,R.drawable.battery3,R.drawable.battery4};
        Drawable[] imgDrawable = new Drawable[4];
        for(int i = 0; i < 4 ; i++)
        {
            imgDrawable[i] = getResources().getDrawable((img[i]));
        }
        imageView.setImageDrawable(imgDrawable[percent/25]);
//        if(percent >=75)
//        {
//            imageView.setImageResource(R.drawable.battery4);
//        }
//        else if(percent >=50)
//        {
//            imageView.setImageResource(R.drawable.battery3);
//        }
//        else if(percent >=20)
//        {
//            imageView.setImageResource(R.drawable.battery2);
//        }
//        else if(percent >=5)
//        {
//            imageView.setImageResource(R.drawable.battery1);
//        }
//        else if(percent <=5)
//        {
//            imageView.setImageResource(R.drawable.baseline_battery_alert_black_18dp);
//        }

        Log.d("@@@@@@@@@@@@@", "LevelStr="+levelStr);


    }
}
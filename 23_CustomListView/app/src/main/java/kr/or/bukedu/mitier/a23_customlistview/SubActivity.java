package kr.or.bukedu.mitier.a23_customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;

public class SubActivity extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        image = findViewById(R.id.imageView);

        Intent intent = getIntent();
        int index = intent.getIntExtra("data", 0);


        if(index == 0)
        {
            image.setImageResource(R.drawable.gimbab);
        }
        else if(index == 1)
        {
            image.setImageResource(R.drawable.odeng);
        }
        else if(index == 2)
        {
            image.setImageResource(R.drawable.ddug);
        }
        else if(index == 3)
        {
            image.setImageResource(R.drawable.ra);
        }
    }
}
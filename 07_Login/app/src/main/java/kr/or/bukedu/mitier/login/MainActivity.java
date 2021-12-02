package kr.or.bukedu.mitier.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnChange(View view)
    {
        view.setBackgroundColor(Color.BLUE);
    }

    public void color1(View view)
    {
        view.setBackgroundColor(Color.GREEN);
    }

    public void color2(View view)
    {
        view.setBackgroundColor(Color.YELLOW);
    }

    public void color3(View view)
    {
        view.setBackgroundColor(Color.LTGRAY);
    }
}
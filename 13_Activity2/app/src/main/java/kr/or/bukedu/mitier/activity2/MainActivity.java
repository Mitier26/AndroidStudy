package kr.or.bukedu.mitier.activity2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;
    final int REQUEST_CODE2 = 26;
    String TAG = "MainActivity***";

    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProcessActivity.class);
                intent.putExtra("id","id");
                intent.putExtra("pw", "pw");

                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProcessActivity.class);
                intent.putExtra("id","id");
                intent.putExtra("pw", "pw");

                startActivityForResult(intent,REQUEST_CODE2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE)
        {
            Log.d(TAG,"REQUEST_CODE");
            if(resultCode == RESULT_OK)
            {

                Log.d(TAG,"RESULT_OK");
                //String str = data.getStringExtra("fruit").toString();
                //Log.d(TAG,"RESULT_OK" + str);
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Log.d(TAG, "RESULT_CANCELED");
            }
        }
        else if(requestCode == REQUEST_CODE2)
        {
            Log.d(TAG,"REQUEST_CODE2");
            if(resultCode == RESULT_OK)
            {

                Log.d(TAG,"RESULT_OK2");
                //String str = data.getStringExtra("fruit").toString();
                //Log.d(TAG,"RESULT_OK" + str);
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Log.d(TAG, "RESULT_CANCELED2");
            }
        }

    }
}
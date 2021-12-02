package kr.or.bukedu.mitier.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        setResult(RESULT_OK);
        finish();

        //setResult(RESULT_CANCELED);
        //Intent sendIntent = new Intent();
        //sendIntent.putExtra("fruit", "apple");
        //setResult(RESULT_OK, sendIntent);
        //finish();
        // onActivityResult

    }
}
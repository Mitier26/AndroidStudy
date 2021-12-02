package kr.or.bukedu.mitier.newnaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity***";
    EditText idText, pwText;
    ImageButton loginIBtn;
    Button joinBtn;
    String id, pw;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         idText = findViewById(R.id.editTextTextPersonName);
         pwText = findViewById(R.id.editTextTextPersonName2);

        loginIBtn = findViewById(R.id.imageButton);
        joinBtn = findViewById(R.id.button);

        imageView = findViewById(R.id.imageView);

        id = idText.getText().toString();
        pw = pwText.getText().toString();

        loginIBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 id = idText.getText().toString();
                 pw = pwText.getText().toString();

                 Log.d(TAG, id + "/" + pw);

                Intent intent = new Intent(getApplicationContext(),ProcessActivity.class);

                // 데이터를 보내는 것
                intent.putExtra("id", id);
                intent.putExtra("pw", pw);
                startActivity(intent);
             }
         });

        joinBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 // 회원가입 화면으로 이동
                 Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                 startActivity(intent);
             }
         });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.google.com"));
                startActivity(intent);
            }
        });
    }
}
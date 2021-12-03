package kr.or.bukedu.mitier.bef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText number, name;
    Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        number = findViewById(R.id.editTextNumber);
        name = findViewById(R.id.editTextTextPersonName);
        okBtn = findViewById(R.id.button);

        Intent i = getIntent();

        String value = i.getStringExtra("data");

        String[] str = value.split("/");

        name.setHint(str[1]);
        number.setHint(str[2]);


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                if(name.length() < 1)
                {
                    name.setText("이름없음");
                }
                if(number.length() < 1)
                {
                    number.setHint("000-0000-0000");
                }
                String value = "/" + name.getText().toString() + "/" + number.getText().toString();

                intent.putExtra("return", value);

                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
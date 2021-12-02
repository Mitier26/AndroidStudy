package com.example.naver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class JoinActivity extends AppCompatActivity {

    TextView title, name, id, pw, birth;
    ImageView imageView;
    EditText editName, editId,editPw, editRe;
    DatePicker datePicker;
    Button button;
    RadioButton woman, man;
    CheckBox box1, box2;
    RadioGroup radio;

    String TAG = "JoinActivity***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        
        title = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView2);
        name = findViewById(R.id.textView4);
        id = findViewById(R.id.textView5);
        pw = findViewById(R.id.textView6);
        birth = findViewById(R.id.textView7);
        editName = findViewById(R.id.editTextTextPersonName2);
        editId = findViewById(R.id.editTextTextPersonName3);
        editPw = findViewById(R.id.editTextTextPersonName4);
        editRe = findViewById(R.id.editTextTextPersonName5);
        datePicker =findViewById(R.id.calendarView);
        button = findViewById(R.id.button4);
        radio = findViewById(R.id.radiogroup);
        woman = findViewById(R.id.radioButton6);
        man = findViewById(R.id.radioButton5);
        box1 = findViewById(R.id.checkBox);
        box2 = findViewById(R.id.checkBox2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 입렵받은 글을 변수에 넣는다.
                // trim : 빈공간을 잘라 버린다.
                String nameStr = editName.getText().toString().trim();
                // 변수의 길이다 0 이면, 입렵 받은 값이 없다면.
                if(nameStr.length() == 0)
                {
                    Snackbar.make(view, "이름을 입력하세요!", Snackbar.LENGTH_LONG).show();
                    Log.d(TAG, "이름정보없음" + nameStr.length());

                    return;     // 뒤 내용 상관 없이 위로 (종료), 이름이 없으면 다음 내용을 실행 하지 않겠다!
                }

                String idStr = editId.getText().toString().trim();
                if(idStr.length() == 0)
                {
                    Snackbar.make(view, "아이디를 입력하세요!", Snackbar.LENGTH_LONG).show();
                    return;
                }

                String pwStr = editPw.getText().toString().trim();
                if(pwStr.length() == 0)
                {
                    Snackbar.make(view, "패스워드를 입력하세요!", Snackbar.LENGTH_LONG).show();

                    return;
                }

                String  reStr = editRe.getText().toString().trim();

                // 문자열이 같은지 비교하는것 ( boolean 반환 )
                if(!reStr.equals(pwStr))
                {
                    Snackbar.make(view, "패스워드가 같지 않습니다.", Snackbar.LENGTH_LONG).show();

                    return;
                }

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                //Toast.makeText(getApplicationContext(), year + "년" + month + "월" + day +  "일", Toast.LENGTH_LONG).show();
                Log.d(TAG,year + "년" + month + "월" + day +  "일" );



                int check = radio.getCheckedRadioButtonId();
                if(woman.getId()  == check)
                {
                    //Toast.makeText(getApplicationContext(), "여자" , Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, "여자", Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    //Toast.makeText(getApplicationContext(), "남자" , Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, "남자", Snackbar.LENGTH_LONG).show();
                }

                if(box1.isChecked() && box2.isChecked())
                {
                    Snackbar.make(view, "둘다 좋아", Snackbar.LENGTH_LONG).show();
                }
                else if(box1.isChecked())
                {
                    Snackbar.make(view, "안드로이드가 좋아", Snackbar.LENGTH_LONG).show();
                }
                else if(box2.isChecked())
                {
                    Snackbar.make(view, "아두이노가 좋아" , Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    Snackbar.make(view, "좋아하는것이 없어", Snackbar.LENGTH_LONG).show();
                }

            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(view, "이름을 입력하세요", Snackbar.LENGTH_LONG).show();
                return true;
            }
        });
        
        // 같은 내용이다.
        //View.OnClickListener listener = new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //
        //    }
        //};
        //button.setOnClickListener(listener);
    }
}
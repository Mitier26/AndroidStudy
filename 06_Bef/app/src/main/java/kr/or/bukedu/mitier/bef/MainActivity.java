package kr.or.bukedu.mitier.bef;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

// 테스트용
class friend
{
    String name;
    int number;
}

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity***";
    LinearLayout layout;

    int[] resId={R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,
            R.id.button6, R.id.button7, R.id.button8,R.id.button9, R.id.button10,R.id.button11};

    Button[] buttons = new Button[resId.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);

        SharedPreferences pref = getSharedPreferences("pref_file", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        for (int i = 0 ; i < layout.getChildCount(); i++)
        {
            //buttons[i].setId(i);  // 버튼의 ID를 재정의 하는 것
            buttons[i] = findViewById(resId[i]);
            // 태그의 인덱스는 staitc으로 만들어야 한다.

            // 리퀘스트 번호로 이용, 버튼의 인덱스 번호로 이용
            buttons[i].setTag(i);

            int finalIndex = i; // 내부 for 문에서 사용하기 위한 것

            // 저장한 데이터 불러오기
            // 만약, 불러온 데이터에 "btn0" ~ "btn9" 의 데이터가 있다면
            if(pref.contains("btn"+(int)buttons[i].getTag()))
            {
                // Key 값을 이용하여 불러온 데이터를 str에 넣는다.
                String str = pref.getString("btn"+(int)buttons[i].getTag(), "0/감자/114");
                buttons[i].setText(str);    // 불러온 값을 버튼의 Text에 세팅
            }
            else
            {
                // 불러온 값이 없을 때 사용하는 기본값
                buttons[i].setText("0/이름/0000000000");
            }


            // 각 버튼에 기능을 부여
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(getApplicationContext(), CallActivity.class);
                    //startActivity(intent);


                    String value = buttons[finalIndex].getText().toString();
                    String[] str = value.split("/");

                    // 자동으로 114로 전화 걸기 화면으로 이동
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + str[2]));
                    startActivity(in);
                }
            });

            buttons[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                    String value = buttons[finalIndex].getText().toString();
                    intent.putExtra("data", value);

                    startActivityForResult(intent, (int)buttons[finalIndex].getTag());
                    return true;
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 리퀘스트 번호는 버튼의 번호
        Intent intent = data;
        String str = intent.getStringExtra("return");

        for(int i = 0; i < buttons.length; i++)
        {
            if((int)buttons[i].getTag() == requestCode)
            {
                if(resultCode == RESULT_OK)
                {
                    str = buttons[i].getTag().toString()+str;
                    buttons[i].setText(str);
                    // 간단한 데이터를 저장
                    // 파일을 만든다.
                    SharedPreferences pref = getSharedPreferences("pref_file", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("btn"+(int)buttons[i].getTag(), str);
                    editor.commit();
                    //데이터를 저장한 후에 commit() 호출해야 실제 저장됨
                }
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴xml 정보를 메모리에 올린다. 이것을 해야 핸들링할 수 있다.
        getMenuInflater().inflate(R.menu.reset, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 옵셥 버튼이 선택되면 하는 행동
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.reset:
                //초기화 작업을 해주어야 한다.

                Toast.makeText(getApplicationContext(), "초기화메뉴선택", Toast.LENGTH_LONG).show();

                for(int i = 0; i< buttons.length; i++)
                {
                    SharedPreferences pref = getSharedPreferences("pref_file", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("btn"+(int)buttons[i].getTag(), buttons[i].getTag()+"/이름/000-0000-0000");
                    editor.commit();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
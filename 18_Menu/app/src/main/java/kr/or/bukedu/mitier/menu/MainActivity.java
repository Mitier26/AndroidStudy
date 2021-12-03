package kr.or.bukedu.mitier.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(), "초기화옵션선택", Toast.LENGTH_LONG).show();
                Log.d("@@@@@@@@@@", "초기화메뉴선택");

                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("data1", "0/홍길동/000-0000-0000");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
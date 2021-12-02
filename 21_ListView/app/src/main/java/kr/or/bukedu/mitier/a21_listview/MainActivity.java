package kr.or.bukedu.mitier.a21_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        textView = findViewById(R.id.textView1);

        // 어댑터 생성, 데이터 관리
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1
        );

        // 어댑터를 리스트에 추가
        listView.setAdapter(adapter);

        // 어댑터에 데이터를 추가
        adapter.add("바나나");
        adapter.add("감자");
        adapter.add("고구마");
        adapter.add("토마토");
        adapter.add("양파");
        adapter.add("호두");
        adapter.add("배추");
        adapter.add("블랙올리브");
        adapter.add("브로콜리");
        adapter.add("수박");
        adapter.add("포도");
        adapter.add("호박");

        // 어댑터를 선택하는 기능
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(parent.getItemAtPosition(position).toString());

                String str = adapter.getItem(position);

                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package kr.or.bukedu.mitier.a23_customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리스트 뷰 찾아오기
        listView = findViewById(R.id.listview);

        // 데이터 어댑터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
          getApplicationContext(),
          R.layout.list_item
        );

        // 리스트 뷰랑 어댑터 연동
        listView.setAdapter(adapter);
        adapter.add("김밥");
        adapter.insert("어묵", 1);
        adapter.insert("떡볶이", 2);
        adapter.add("라볶기");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String str = (String)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                intent.putExtra("data", position);
                startActivity(intent);

            }
        });
    }
}
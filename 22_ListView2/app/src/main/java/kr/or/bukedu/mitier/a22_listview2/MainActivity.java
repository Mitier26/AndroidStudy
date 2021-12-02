package kr.or.bukedu.mitier.a22_listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<HashMap<String, String>> data = new ArrayList<>();

//    ArrayList arr = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        for(int i = 0 ; i < 20 ; i++)
        {
            HashMap<String , String> h1 = new HashMap<>();
            h1.put("data1", "감자" + i);
            h1.put("data2", "고구마" + i);

            data.add(h1);
        }

//        HashMap<String, String> map = new HashMap<>();
//        map = (HashMap<String, String>) arr.get(0);
//        String str1 = map.get("data1");
//        String str2 = map.get("data2");



        // 구성되어진 data
//        HashMap<String , String> h1 = new HashMap<>();
//        h1.put("data1", "감자");
//        h1.put("data2", "고구마");
//
//        HashMap<String , String> h2 = new HashMap<>();
//        h2.put("data1", "사과");
//        h2.put("data2", "포도");
//
//        arr.add(h1);

        // 데이터의 어댑터
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                data,   // 이미 있는 데이터를 가져다가 사용
                android.R.layout.simple_list_item_2,
                new String[]{"data1", "data2"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        listView.setAdapter(simpleAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // parent는 Object 타입을 반환한다.
                // 헤시맵 타입으로 캐스팅 해주어야 한다.
                HashMap<String, String> str= (HashMap<String, String>) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), str.get("data1"), Toast.LENGTH_SHORT).show();

                // 참고 자료
                // String school = h.get("school");
                // data.add(h);
                // HashMap<String, String> str = data.get(position);

            }
        });
    }


}
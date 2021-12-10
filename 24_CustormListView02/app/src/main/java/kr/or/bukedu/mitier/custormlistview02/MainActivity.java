package kr.or.bukedu.mitier.custormlistview02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        editText = findViewById(R.id.editTextTextPersonName);

        String str = editText.getText().toString();

        ArrayList<String> data = new ArrayList<String>();
        data = getContacts();

        // 어댑터 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), R.layout.list_item, data
        );

        listView.setAdapter(adapter);



    }

    ArrayList<String> getContacts()
    {
        ArrayList<String> data = new ArrayList<String>();
        // 전화부 정보 얻어오기
        // 전화부가 Content Provider 를 제공해주고 있다.
        // Content Reslover를 통해서 Provider에 접근하자.

        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // Col의 이름을 명시해서 찾아올 수도 있다.
        // String[] projection = {"NO.", "이름", "전화번호1", "전화번호2", "주소"}
        String[] projection = null; // 모든컬럼을 보겠다.

        // 한가지 특정 데이터만 가져오고 싶을때
        //String selection = null;    // 조건을 주지않고 전부 찾는다.
        // 중간에 k 가 들어 가는 것으로 찾아줘 k%, %k, 해당 문자로 검색 하는 기능
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like '%'";

        // 조건에 대한 값을 변수 처리할 경우
        String[] selectionArgs = null;

        // 이름순을 오름차순으로 보여줘 desc : 내림 차순
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc";

        // 조건으로 데이터를 찾는다, query : 질문
        // 커서의 형태로 돌려줌, 한줄 씩 돌려준다.
        Cursor cursor =  contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        //Cursor cursor =  contentResolver.query(uri, projection, selection, new String[]{"Android"}, sortOrder);

        while(cursor.moveToNext())
        {
            // 행 전체의 데이터를 읽어옴.
            // 커서를 이용하여 값을 가지고 온다.
            String name =  cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

            String str = name + " / " + number;

            data.add(str);
        }

        return data;
    }

    // SMS를 보여주는 기능
    ArrayList<String> getContacts2()
    {
        ArrayList<String> data = new ArrayList<String>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://sms");

        String[] projection = null;
        // body 컬럼의 이름
        String selection = null;
        //String selection = "body like '%삼성카드%";
        String[] selectionArgs = null;
        // 날짜를 보여줘
        String sortOrder = "date desc";

        Cursor cursor =  contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

        while(cursor.moveToNext())
        {
            // 행 전체의 데이터를 읽어옴.
            // 커서를 이용하여 값을 가지고 온다.
            String address =  cursor.getString(cursor.getColumnIndexOrThrow("address"));

            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));

            String str = address + " / " + body;

            data.add(str);
        }

        return data;
    }
}
package kr.or.bukedu.mitier.a25_permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE = 126;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        editText = findViewById(R.id.editTextTextPersonName2);
        //1.find... 해서 가지고 올 것들을 다 가지고 온다
        //2.find는 퍼미션이 실행되고 해야한다.
        //3.그래서 void create() 에서 실행을 한다.

        permissionCheck();  //4.전화부 권한 때문에 권한 처리
    }

    void create()
    {
        //22. 전화부 내용을 리스트 뷰에 보여준다.
        ArrayList<String> data = getContacts();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, data);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //23. 아이템을 선택하면 토스트로 이름 과 전화번호를 보여주기
                String a = (String)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), ""+a, Toast.LENGTH_LONG).show();

                String[] str = a.split("/");
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + str[1]));
                startActivity(in);
            }
        });

    }

    ArrayList<String> getContacts()
    {
        ArrayList<String> data = new ArrayList<String>();

        //23. 저장되어 있는 값에 접근하기위한 변수
        ContentResolver cr = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //24. 데이터는 Cursor의 형태로 가지고 온다.
        // projection : DBMS(데이터 베이스 메이지먼트 시스템) 여러개 있다.
        //              DB : DBMS 안에 여러 자료를 넣을수 있는 데이블
        // No.          name             age             address             tel
        // 1            홍길동            34              노원구              000-000-0000
        // 2            토리              7               송파구              111-1111-1111
        // cursor는 1, 2, 3 행을 나타냄
        // projection : 열 No.  name  age  address  tel 을 뜻한다.

        String[] projection = null;

        //String selection = null;
        //25. Ttt라는 이름의 정보를 가진 것만 출력
        //String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "='김이박'";
        String a = editText.getText().toString();
        //String selection = ContactsContract.CommonDataKinds.Phone.NUMBER + " like '%55%' or "
        //        + ContactsContract.CommonDataKinds.Phone.NUMBER + " like '%55%' of ";

        Log.d("@@@@@@@@@@@@@@", a);
        String selection = ContactsContract.CommonDataKinds.Phone.NUMBER + " like '%"+a+"%'";

        String[] selectionArgs = null;

        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc";

        Cursor cursor = cr.query(uri, null, selection, null,sortOrder);

        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String str = name+"/"+num;
            data.add(str);
        }

        return data;
    }

    void permissionCheck()
    {
        //6. 전화부 권한을 처음 실행할 때 1번 실행
        //7. 이미 권한이 허락되었는 체크
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
        {
            //8. 허락이 되었다면 다시 권한을 묻지 않고 실행한다.
            create();
        }
        else
        {
            //9. 권한이 허락되지 않았다면 권한 허락을 물어봐야 한다.
            //10. 이전 거부 이력이 있는지 확인
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS))
            {
                //11. 거부한 이력이 있으면 Ture 반환
                //12. 거부를 했을 경우 : 유저를 설득해 거부하지 않고 허락하도록 설득..토스트
                Toast.makeText(this, "권한이 없습니다. 권한을 허락해 주세요.", Toast.LENGTH_LONG).show();

            }
            //13. 거부 한적이 없는데 권한이 없다는것은 처음 설치했다는 것이다.
            //14. 처음 설치 하는 경우 : 허락 or 거부 시스템 팝업을 통행 유저가 선택할 수 있도록 한다.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
        }
    }//permissionCheck

    //15. 팝업에 유저가 선택한 값을 돌려받는다. 팝업에서 무엇을 선택했는지
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //16. 권한 팝업이 무엇인지 확인
        if(requestCode == REQUEST_CODE)
        {
            //17. 값이 있다있고 0 번째 값이 허락이 되었다면
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //18. 권한이 있으므로 실행
                create();
            }
            else
            {
                //19. 권한이 없을 경우 종료
                finish();
            }
        }
    }
}
//20. 퍼미션은 확인하기 위해서는 앱을 지우고 다시 설치 해야한다.
//21. 처음 한번만 물어보기 때문이다.
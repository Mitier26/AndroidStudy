package com.malkang.mitier.a29_brsmsreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver;
    String TAG = "@@@@@@@@@@@@@";
    int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this, REQUEST_CODE);

        create();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, REQUEST_CODE,
                new String[]{"android.permission.RECEIVE_SMS"},
                new AutoPermissionsListener() {
                    // 유저가 무엇을 선택했냐?
                    @Override
                    public void onGranted(int i, String[] strings) {
                        for(String str : strings)
                        {
                            // 허락된 권한을 본다.
                            Log.d(TAG, "onGranted");
                            Log.d(TAG, str);
                        }
                    }
                    // 디아니가 0 이되면 create를 호출
                    // 어떤 권한이 선택되어 있는지는 strings 안에 포함
                    @Override
                    public void onDenied(int i, String[] strings) {
                        if(strings.length == 0)// 거부한 이력이 없는 것
                        {
                            // 거부한 것이 없다면
                            create();
                        }
                        else
                        {
                            // 권한이 거부되었으면 거부한것을 보여준다.
                            for(String str : strings)
                            {
                                // 허락된 권한을 본다.
                                Log.d(TAG, "onDenied");
                                Log.d(TAG, str);
                            }
                            finish();
                            Toast.makeText(getApplicationContext(), "거부된 권한이 있습니다.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }

    public void create()
    {
        receiver = new BroadcastReceiver() {
            // 날짜의 포멧을 지정한다.
            public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public void onReceive(Context context, Intent intent) {
                //sms
                if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
                {
                    Log.d(TAG, "received SMS");
                    // 받은 메시지가 무엇인지 확인하기
                    Bundle bundle = intent.getExtras(); // intent로 값을 가지고 온다.
                    // putExtra 할 때 사용하던 타입이 Bundle 타입이였다.

                    // 번들에 포합된 문자 데이터를 객체 배열로 받아온다.
                    // PDU : Protocol Data Unit // 프로토콜이 정보의 운반을 위해서
                    // 데이터에 헤더외 트에일러를 붙여 전송하는 단위
                    Object[] objs = (Object[]) bundle.get("pdus");

                    // SMS를 받아올 SmsMessge 배열을 만든다.
                    SmsMessage[] messages = new SmsMessage[objs.length];

                    int smscount = objs.length;
                    Log.d(TAG, "smsCount : " + smscount);

                    for(int i = 0; i< smscount; i++)
                    {
                        // 채널 정보, 버전 보다 높다면
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            // PDU format message
                            String format = bundle.getString("format");
                            // SmsMessage의 static 메서드인 createFormatPdu로 objs의
                            // 데이터를 message에 담는다.
                            // 이 때 objs는 byte배열로 형변환 해야 한다.
                            Log.d(TAG, "format : " + format);

                            messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
                        }
                        else
                        {
                            messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
                        }
                    } //for

                    if(messages != null && messages.length>0)
                    {
                        // 보내는 사람
                        String sender = messages[0].getOriginatingAddress();
                        Log.d(TAG, "Sender : " + sender);

                        // SMS 내용
                        String contents = messages[0].getMessageBody();
                        Log.d(TAG, "Body : " + contents);

                        // 날짜의 값을 인간이 볼수 있는 값으로 변환하는것
                        Date receivedDate = new Date(messages[0].getTimestampMillis());
                        Log.d(TAG, "SMS received Date : " + receivedDate.toString());

                        //sms 수신되고, 다이얼로그 띄우고 ok누르면 액티비티로 sms 정보 보여주기
                        // 대화 상자를 만들기 위한 빌더 객체 생성
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("문자정보");
                        builder.setMessage("SMS수신되었습니다. 확인하겠습니까?");
                        builder.setIcon(android.R.drawable.ic_dialog_alert);


                        // 확인 버튼을 누르면
                        builder.setPositiveButton("예, yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(TAG, "click button, Yes");
                                // new Activity
                                Intent sendIntent = new Intent(context, SMSShowActivity.class);
                                sendIntent.putExtra("sender", sender);
                                sendIntent.putExtra("contents", contents);
                                //sendIntent.putExtra("receivedDate", receivedDate.toString());
                                sendIntent.putExtra("receivedDate", format.format(receivedDate));
                                Log.d(TAG, receivedDate.toString() + "-----------");
                                startActivity(sendIntent);
                            }
                        }); // yes 버튼

                        builder.setNegativeButton("아니요, No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d(TAG,"No 버튼 클릭");
                            }
                        }); // no 버튼
                        AlertDialog dialog = builder.create();

                        dialog.show();
                    }

               }
            }
        };

        IntentFilter intentFilter = new IntentFilter(
                "android.provider.Telephony.SMS_RECEIVED"
        );
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if(receiver != null)
            unregisterReceiver(receiver);
    }
}
package com.malkang.mitier.a27_brbatterychangedlow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "@@@@@@@@@@@@@@@@@@";
    BroadcastReceiver receiver;
    final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3. 리시버 정의
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 내가 받고자한 브로드캐스트인지 확인
                if(intent.getAction().equals("android.intent.action.BATTERY_CHANGED"))
                {
                    //Log.d(TAG, "battery_changed 방송메세지 수신");
                    // 현재 배터리량
                    int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 100);
                    //Log.d(TAG, "battery_changed 방송메세지 수신" + level);
                    String str = "현재 배터리가 " + level +"%입니다.";
                    //Log.d(TAG, str);

                    // 알림 만들기 노티피케이션
                    Intent intentBattery = new Intent(context, BatteryStateActivity.class);
                    intentBattery.setData(Uri.parse(level+""));

                    // 기다리고 있는 intent
                    // 알람을 눌렀을 때 어디로 갈 것인가.
                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            context,
                            REQUEST_CODE,
                            intentBattery,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );

                    //notification 생성
                    NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                    String CHANNEL_ID = "default";
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(
                            context,
                            CHANNEL_ID
                    );

                    // 필수 사항  아이콘
                    // 알림창을 천천히 내리면 확인가능한 알림창의 아이콤으 이동한다.
                    // 그래서 작은 아이콘이 필수로 있어야 한다.
                    builder.setSmallIcon(R.drawable.baseline_battery_alert_black_18dp);
                    builder.setContentTitle("배터리 알림");  // 알림창의 제목
                    builder.setContentText(str);    // 알람창 내부의 글
                    builder.setContentIntent(pendingIntent);

                    // 오래오 버전 이상이면 채널 정보가 들어 가야 한다.
                    NotificationChannel channel;
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                    {
                        // 알림의 중요도를 설정한다.
                        // 밤에도 울리는 알람 등
                        channel = new NotificationChannel(CHANNEL_ID, "배터리 정보",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManager.createNotificationChannel(channel);
                    }


                    //builder : 알림창을 만들어 주는 것.
                    // 1 : 알림창의 번호
                    notificationManager.notify(1, builder.build());


                }
                else if (intent.getAction().equals("android.intent.action.BATTERY_LOW"))
                {
                    Log.d(TAG, "battery_low 방송메세지 수신");
                    Toast.makeText(context, "충전하세요", Toast.LENGTH_LONG).show();
                }
            }
        };

        //4. 인텐트 필터지저, 내가 받고자하는 브로드캐스트 지정정
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");

        // 1.브로드캐스트리스버 등록
        // 누가,  어떤것
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 2. 브로드캐스트리시버 해제제
       unregisterReceiver(receiver);
    }
}
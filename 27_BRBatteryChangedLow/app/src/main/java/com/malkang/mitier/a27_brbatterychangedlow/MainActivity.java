package com.malkang.mitier.a27_brbatterychangedlow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "@@@@@@@@@@@@@@@@@@";
    BroadcastReceiver receiver;
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
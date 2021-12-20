package com.malkang.mitier.a26_brbootcompleted;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
/*
BOOT_COMPLETED 브로드캐스트 수신자
manifest등록 방식
 */
public class MyBootCompletedReceiver extends BroadcastReceiver {

    String TAG = "@@@@@@@@@@@@@@@@@@@@@@@";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Log.d("@@@@@@@@@@@@@@", "onReceiver()");
        if( intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            // boot Completed 받으면 할 것을 만든다.
            Toast.makeText(context, "부팅완료", Toast.LENGTH_LONG).show();
        }
        //else if()
    }
}
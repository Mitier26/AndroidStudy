package com.malkang.mitier.a30_thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 쓰레드 : 동시에 작동 되도록 하는 것
    // 동시에 작동되는 것을 만들기 위한것
    String TAG = "@@@@@@@@@@@";
    int value =0;
    TextView textView;

    Handler myHandler;
    Handler handler;
    final int MESSAGE_READ = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyThread thread = new MyThread();
        // 쓰레드 시작
        thread.start();


        // 인터페이스를 상속 받은 객체
        MyThread2 myThread2 = new MyThread2();
        // 자바에서 재공하고 이쓰 쓰레드
        // Runnable 객체를 만들어 인자로 넣어 주어야 한다.
        Thread th = new Thread(myThread2);
        th.start();


        MyThread thread1 = new MyThread();
        thread1.start();

        MyThread2 myThread21 = new MyThread2();
        Thread th2 = new Thread(myThread21);
        th2.start();

        // 총 5개의 쓰레드가 돌고 있다.
        // main 도 쓰레드 이다.

        YourThread yourThread = new YourThread();


        Button checkBtn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourThread.start();
            }
        });

        myHandler = new MyHandler();
        handler = new Handler();
    }

    class MyHandler extends  Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch(msg.what)
            {
                case MESSAGE_READ:
                    textView.setText(""+msg.arg1);
                    Log.d(TAG, "handleMessage"+value);
                    break;
            }

        }
    }

    class YourThread extends Thread{

        public void run() {
            super.run();

            while(value < 1000)
            {
                value++;
                // 만든 쓰레드 내부에서 UI 작업을 할 수 없다.
                // UI 관련 작업은 메인에서 해야 한다.
                // 메인에 UI 작업을 요청해야함다.
                // 1. 메인에서 obtainMessage를 쓰레드에 보낸다.
                // 2. 메인에 메시지 큐에 할당을 해야 한다.
                // 3. 쓰레드에서 메시지를 보낸다.
                // 4. 메인에 있는 핸들 메시지에 setText를 한다.
                // textView.setText("value"+value);

                // 1
                Message msg = myHandler.obtainMessage(MESSAGE_READ, value,0);
                // 2
                myHandler.sendMessage(msg);


                // B. runnabble 의 객체를 이용하는 방법
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("runnable" + value);
                    }
                });

                // C.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("runOnUiThread" + value);
                    }
                });

                // 핸들러 없이 그냥 하고 싶은 장소에서 하면 된다.

                try {
                    Thread.sleep(444);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }

        }
    }
    // 2 가지 방법이 있다.
    class MyThread extends Thread{
        public void run()
        {
            //  쓰레드의 동작 정의

        }
    }

    class MyThread2 implements Runnable
    {
        // run을 꼭 만들어 주어야 한다.
        // B
        @Override
        public void run() {

        }
    }
}
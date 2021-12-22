package com.malkang.mitier.a30_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    // 쓰레드 : 동시에 작동 되도록 하는 것
    // 동시에 작동되는 것을 만들기 위한것
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
        @Override
        public void run() {

        }
    }
}
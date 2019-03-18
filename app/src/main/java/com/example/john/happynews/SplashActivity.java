package com.example.john.happynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.john.LoginRegister.LoginActivity;
import com.victor.loading.book.BookLoading;
import com.victor.loading.newton.NewtonCradleLoading;

public class SplashActivity extends AppCompatActivity {
    int second = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        BookLoading book = (BookLoading) findViewById(R.id.newton_cradle_loading);
        book.start();
        Thread thread=new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        second--;
                        if (second == 0) {
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit);
                            finish();
                            overridePendingTransition(R.anim.activity_exit, R.anim.activity_enter);
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}

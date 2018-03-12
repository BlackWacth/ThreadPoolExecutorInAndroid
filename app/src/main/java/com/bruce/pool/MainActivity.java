package com.bruce.pool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bruce.pool.thread.DefaultExecutorSupplier;

import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Log.i("hzw", "availableProcessors = " + availableProcessors);

        Future<?> submit = DefaultExecutorSupplier.getInstance().forBackgroundTasks().submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        submit.cancel(true);
    }
}

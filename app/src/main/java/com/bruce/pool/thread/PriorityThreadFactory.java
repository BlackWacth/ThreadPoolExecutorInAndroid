package com.bruce.pool.thread;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * 带优先级的线程工厂
 * Created by Bruce on 2018/3/9.
 */
public class PriorityThreadFactory implements ThreadFactory {

    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(@NonNull final Runnable r) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(mThreadPriority);
                } catch (IllegalArgumentException | SecurityException e) {
                    e.printStackTrace();
                }
                r.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}

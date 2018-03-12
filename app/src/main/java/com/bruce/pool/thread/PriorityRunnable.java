package com.bruce.pool.thread;

/**
 * Created by Bruce on 2018/3/9.
 */
public class PriorityRunnable implements Runnable {

    private final Priority mPriority;

    public PriorityRunnable(Priority priority) {
        mPriority = priority;
    }

    @Override
    public void run() {

    }

    public Priority getPriority() {
        return mPriority;
    }
}

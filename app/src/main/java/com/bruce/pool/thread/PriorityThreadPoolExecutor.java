package com.bruce.pool.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bruce on 2018/3/9.
 */
public class PriorityThreadPoolExecutor extends ThreadPoolExecutor {

    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new PriorityBlockingQueue<Runnable>(), threadFactory);
    }

    @Override
    public Future<?> submit(Runnable task) {


        return super.submit(task);
    }

    private static final class PriorityFutureTask extends FutureTask<PriorityRunnable> implements Comparable<PriorityFutureTask> {

        private final PriorityRunnable mPriorityRunnable;

        public PriorityFutureTask(@NonNull PriorityRunnable runnable) {
            super(runnable, null);
            mPriorityRunnable = runnable;
        }


        @Override
        public int compareTo(@NonNull PriorityFutureTask o) {
            return mPriorityRunnable.getPriority().ordinal() - o.mPriorityRunnable.getPriority().ordinal();
        }
    }
}

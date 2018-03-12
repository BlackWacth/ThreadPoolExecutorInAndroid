package com.bruce.pool.thread;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池执行器辅助类
 * Created by Bruce on 2018/3/9.
 */
public class DefaultExecutorSupplier {

    /**
     * 决定线程数量的核心数量
     * Runtime.getRuntime().availableProcessors(); ==> 可用于Java虚拟机的处理器数量。
     */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static final int KEEP_ALIVE_TIME = 10;

    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 用于执行后台任务
     */
    private final ThreadPoolExecutor mForBackgroundTasks;

    /**
     * 用于轻量级后台任务
     */
    private final ThreadPoolExecutor mForLightWeightBackgroundTasks;

    /**
     * 用于执行后台任务，具有优先级执行
     */
    private final PriorityThreadPoolExecutor mForBackgroundPriorityTasks;

    /**
     * 主线程任务执行器
     */
    private final Executor mMainThreadExecutor;

    private static DefaultExecutorSupplier sInstance;

    private DefaultExecutorSupplier() {
        ThreadFactory backgroundPriorityThreadFactory = new PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        mForBackgroundTasks = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingDeque<Runnable>(), backgroundPriorityThreadFactory);

        mForLightWeightBackgroundTasks = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, new LinkedBlockingDeque<Runnable>(), backgroundPriorityThreadFactory);

        mForBackgroundPriorityTasks = new PriorityThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, backgroundPriorityThreadFactory);

        mMainThreadExecutor = new MainThreadExecutor();
    }

    public static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
        }
        return sInstance;
    }

    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }

    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }

    public Executor forMainThreadExecutor() {
        return mMainThreadExecutor;
    }

    public PriorityThreadPoolExecutor forBackgroundPriorityTasks() {
        return mForBackgroundPriorityTasks;
    }
}

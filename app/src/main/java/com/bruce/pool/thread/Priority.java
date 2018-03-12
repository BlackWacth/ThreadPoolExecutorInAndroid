package com.bruce.pool.thread;

/**
 * 优先等级
 * Created by Bruce on 2018/3/9.
 */
public enum  Priority {

    /**
     * 最低优先级。用于预取数据。
     */
    LOW,

    /**
     * 中等优先级。用于预热可能很快可见的数据。
     */
    MEDIUM,

    /**
     * 较高优先级。用于屏幕上当前可见的数据。
     */
    HIGH,

    /**
     * 最高优先级。用于即时需要的数据（主要用于紧急情况）。
     */
    IMMEDIATE;
}

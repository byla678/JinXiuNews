package com.itfeige.my.jinxiunews.utils;

import com.itfeige.my.jinxiunews.common.MyApplication;

public class ThreadUtils {
    /**
     * 子线程执行task
     */
    public static void runInThread(Runnable task) {
        new Thread(task).start();
    }

    /**
     * UI主线程执行task
     */
    public static void runInUIThread(Runnable task) {
        MyApplication.getHandler().post(task);
    }
}

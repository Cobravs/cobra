package com.cobra.es.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Slf4j
public class ThreadPoolSingle {


    private volatile static ThreadPoolSingle instance;
    private ThreadPoolExecutor threadPoolExecutor;
    private static int MAX_POOL_SIZE;
    private static final int KEEP_ALIVE = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

    private ThreadPoolSingle() {
        int coreNum = Runtime.getRuntime().availableProcessors();
        log.info("ThreadPoolSingle coreNum={}", coreNum);
        MAX_POOL_SIZE = coreNum * 2;
        threadPoolExecutor = new ThreadPoolExecutor(
                coreNum,
                MAX_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS,
                workQueue);
        // 预启动所有核心线程
        log.info("ThreadPoolSingle prestartAllCoreThreads...");
        threadPoolExecutor.prestartAllCoreThreads();
    }

    public static void execute(Runnable runnable) {
        if (instance == null) {
            synchronized (ThreadPoolSingle.class) {
                if (instance == null) {
                    instance = new ThreadPoolSingle();
                    log.info("ThreadPoolSingle init SUCCESS！！！");
                }
            }
        }
        instance.threadPoolExecutor.execute(runnable);
    }
}

package com.leyou.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程执行类
 */
public class ConcurrentExecutor {

    /**
     * 线程执行器
     */
    private static ExecutorService executor;

    static {
        //线程数量cpu核心数量的2倍
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
    }

    /**
     * 提交单个任务
     * @param task 任务
     * @return Future
     */
    public static Future<?> submitTask(AbstractTask task) {
        return executor.submit(task);
    }

    /**
     * 提交单个任务并等待完成
     * @param task 任务
     * @return  任务的结果
     */
    public static Object submitTaskAwait(AbstractTask task) {
        List<AbstractTask> tasks = new ArrayList<>();
        tasks.add(task);
        List<Object> objects = submitTaskAwait(tasks);
        return objects.get(0);
    }

    /**
     * 提交多个任务
     * @param tasks 任务列表
     * @return List<Future<?>>
     */
    public static List<Future<?>> submitTasks(List<AbstractTask> tasks) {
        List<Future<?>> futures = new ArrayList<>();
        for (AbstractTask task : tasks) {
            futures.add(executor.submit(task));
        }
        return futures;
    }

    /**
     * 提交多个任务并等待完成
     * @param tasks 多个任务列表
     * @return  多个任务的结果列表
     */
    public static List<Object> submitTaskAwait(List<AbstractTask> tasks) {
        //初始化计数器
        List<Future<?>> futures = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(tasks.size());
        for (AbstractTask task : tasks) {
            //设置任务的计数器
            task.setCountDownLatch(latch);
            futures.add(executor.submit(task));
        }

        //获取所有任务的结果
        List<Object> results = new ArrayList<>();
        try {
            //等待所有任务完成
            latch.await();
            for (Future<?> future : futures) {
                results.add(future.get());
            }
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return results;
    }


}
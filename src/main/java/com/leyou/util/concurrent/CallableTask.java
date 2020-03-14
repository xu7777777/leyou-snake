package com.leyou.util.concurrent;

import java.util.concurrent.Callable;

/**
 * 任务接口
 */
public interface CallableTask extends Callable<Object> {
    /**
     * 获取任务名称
     */
    String getTaskName();

    /**
     * 执行任务
     */
    Object doTask();
}

package com.leyou.util.concurrent;

import lombok.Data;

import java.util.concurrent.CountDownLatch;

/**
 * 抽象任务类，执行任务的共同逻辑
 */
@Data
public abstract class AbstractTask implements CallableTask {

    //线程计数器
    private CountDownLatch countDownLatch;

    @Override
    public Object call() {
        Object result = null;
        try {
            result = this.doTask();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //计数器减一
            this.countDownLatch.countDown();

        }
        return result;
    }

    /**
     * 获取任务名字
     * @return 任务名字
     */
    public String getTaskName() {
        return this.getClass().getSimpleName();
    }
}

package com.DAsyncScheduler.task;


import com.DAsyncScheduler.common.Constants;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 这里实现 定时任务的 添加和移除
 */
@Component("myCronTaskRegister")
public class CronTaskRegister implements DisposableBean {

    @Resource(name = "myTaskScheduler")
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler() {
        return this.taskScheduler;
    }

    /**
     * 执行任务
     * @param task
     * @param cronExpression
     */
    public void addCronTask(SchedulingRunnable task, String cronExpression) {
        if (null != Constants.scheduledTasks.get(task.taskId())) {
            removeCronTask(task.taskId());
        }
        //这里支持项目启动后动态修改任务启动时间，本质是暂停之前的任务 然后重新启动一个 再重启的过程就可以去动态配置cron
        CronTask cronTask = new CronTask(task, cronExpression);
        Constants.scheduledTasks.put(task.taskId(), scheduleCronTask(cronTask));
    }

    /**
     * 停止任务
     * @param taskId
     */
    public void removeCronTask(String taskId) {
        ScheduledTask scheduledTask = Constants.scheduledTasks.remove(taskId);
        if (scheduledTask == null) return;
        scheduledTask.cancel();
    }

    private ScheduledTask scheduleCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }


    @Override
    public void destroy() {
        for (ScheduledTask task : Constants.scheduledTasks.values()) {
            task.cancel();
        }
        Constants.scheduledTasks.clear();
    }

}

package com.DAsyncScheduler.common;

import com.DAsyncScheduler.domain.ExecOrder;
import com.DAsyncScheduler.task.ScheduledTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    //任务组；beanName->ExecOrder
    public static final Map<String, List<ExecOrder>> execOrderMap = new ConcurrentHashMap<>();
    public static final Map<String, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

}

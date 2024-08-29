package com.DAsyncScheduler.task;


import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;


public class SchedulingRunnable implements Runnable {

    private Object bean;         //类对象
    private String beanName;     //类名称
    private String methodName;   //方法名称

    public SchedulingRunnable(Object bean, String beanName, String methodName) {
        this.bean = bean;
        this.beanName = beanName;
        this.methodName = methodName;
    }

    @Override
    public void run() {
        try {
            Method method = bean.getClass().getDeclaredMethod(methodName);
            ReflectionUtils.makeAccessible(method);
            method.invoke(bean);
        } catch (Exception e) {
        }
    }


    public String taskId() {
        return beanName + "_" + methodName;
    }

}

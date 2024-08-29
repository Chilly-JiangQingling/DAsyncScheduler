package com.DAsyncScheduler.domain;


import lombok.Data;

@Data
public class ExecOrder {

    private Object bean;         //类对象
    private String beanName;     //类对象名称
    private String methodName;   //方法名称
    private String desc;         //任务描述
    private String cron;         //任务执行
    private Boolean autoStartup; //任务状态


}

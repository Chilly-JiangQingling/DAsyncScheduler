package com.DAsyncScheduler.domain;

import lombok.Data;

@Data
public class DcsScheduleInfo {

    private String ip;                      //机器IP
    private String schedulerServerId;       //任务服务ID；  工程名称En
    private String schedulerServerName;     //任务服务名称；工程名称Ch
    private String beanName;                //类对象名称
    private String methodName;              //方法名称
    private String desc;                    //任务描述
    private String cron;                    //任务执行
    private Integer status;                 //任务状态；0关闭、1开启、2宕机
}

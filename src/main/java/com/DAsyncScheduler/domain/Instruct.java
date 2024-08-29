package com.DAsyncScheduler.domain;


import lombok.Data;

@Data
public class Instruct {

    private String ip;                  //机器IP
    private String schedulerServerId;   //任务服务ID；工程名称En
    private String beanName;            //类对象名称
    private String methodName;          //方法名称
    private String cron;                //任务执行
    private Integer status;             //Constants.InstructStatus 0关闭、1启动、2更新

}

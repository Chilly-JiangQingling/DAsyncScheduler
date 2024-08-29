package com.DAsyncScheduler.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DcsServerNode {

    private String schedulerServerId;       //任务服务ID；  工程名称En
    private String schedulerServerName;     //任务服务名称；工程名称Ch


}

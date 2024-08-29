package com.DAsyncScheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCollect {

    private int ipCount;
    private int serverCount;
    private int beanCount;
    private int methodCount;

}

package com.DAsyncScheduler.annotation;


import com.DAsyncScheduler.config.DcsSchedulingConfiguration;
import com.DAsyncScheduler.task.CronTaskRegister;
import com.DAsyncScheduler.config.SchedulingConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个自启动装配注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({DcsSchedulingConfiguration.class})
@ImportAutoConfiguration({SchedulingConfig.class, CronTaskRegister.class})
public @interface EnableDcsScheduling {
}

package com.DAsyncScheduler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定一个定时任务标记注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DcsScheduled {

    String desc() default "缺省";

    String cron() default "";

    boolean autoStartup() default true;

}

package com.DAsyncScheduler.config;


import com.DAsyncScheduler.annotation.DcsScheduled;
import com.DAsyncScheduler.common.Constants;

import com.DAsyncScheduler.domain.ExecOrder;
import com.DAsyncScheduler.task.CronTaskRegister;
import com.DAsyncScheduler.task.SchedulingRunnable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

import java.util.ArrayList;


import java.util.List;
import java.util.Set;




/**
 * 初始化定时定时任务
 */
public class DcsSchedulingConfiguration implements  BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : methods) {
            DcsScheduled dcsScheduled = AnnotationUtils.findAnnotation(method, DcsScheduled.class);
            if (null == dcsScheduled || 0 == method.getDeclaredAnnotations().length) continue;
            List<ExecOrder> execOrderList = Constants.execOrderMap.computeIfAbsent(beanName, k -> new ArrayList<>());
            ExecOrder execOrder = new ExecOrder();
            execOrder.setBean(bean);
            execOrder.setBeanName(beanName);
            execOrder.setMethodName(method.getName());
            execOrder.setDesc(dcsScheduled.desc());
            execOrder.setCron(dcsScheduled.cron());
            execOrder.setAutoStartup(dcsScheduled.autoStartup());
            execOrderList.add(execOrder);
        }
        return bean;
    }

    /**
     * 当容器初始化完成之后执行
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
            // 启动任务
            init_task(applicationContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //启动任务
    private void init_task(ApplicationContext applicationContext) {
        CronTaskRegister cronTaskRegistrar = applicationContext.getBean("CronTaskRegister", CronTaskRegister.class);
        Set<String> beanNames = Constants.execOrderMap.keySet();
        for (String beanName : beanNames) {
            List<ExecOrder> execOrderList = Constants.execOrderMap.get(beanName);
            for (ExecOrder execOrder : execOrderList) {
                if (!execOrder.getAutoStartup()) continue;
                SchedulingRunnable task = new SchedulingRunnable(execOrder.getBean(), execOrder.getBeanName(), execOrder.getMethodName());
                cronTaskRegistrar.addCronTask(task, execOrder.getCron());
            }
        }
    }


}

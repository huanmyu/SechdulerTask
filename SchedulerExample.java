package com.gtstar.test;

import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

//调度器
public class SchedulerExample {
public static void main(String[] args) throws Exception {
	// 创建工厂实例
    StdSchedulerFactory factory = new StdSchedulerFactory();
    
    // 创建配置工厂的属性对象
    Properties props = new Properties();
    props.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool"); // 线程池定义
    props.put("org.quartz.threadPool.threadCount", "10"); // 默认Scheduler的线程数
    
    try {
        // 使用定义的属性初始化工厂
        factory.initialize(props);
        
        Scheduler scheduler = factory.getScheduler();
        //获取JobDetail对象
        JobDetail job= JobBuilder.newJob(HelloWorldJob.class).withIdentity("myJob", "group1").build();
        //每间隔5秒执行一次
        CronScheduleBuilder builder=CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("myTrigger", "group2").withSchedule(builder).build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        System.out.println("------scheudler started, metadata----->: "+scheduler.getMetaData());
    } catch (SchedulerException e) {
        e.printStackTrace();
    }
}
}

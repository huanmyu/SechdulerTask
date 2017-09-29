package com.gtstar.test;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

public class HelloWorldJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello World!");
		//每一个Job都有其自己所属的JobDetail
		JobDetail detail=context.getJobDetail();
		System.out.println("获取到的JobDetail的名称和组名"+detail.getKey());
		System.out.println("获取到的Job类："+ detail.getJobClass());
		//获取Scheduler
		Scheduler scheduler=context.getScheduler();
		try {
			System.out.println("scheduler的名字是："+scheduler.getSchedulerName());
			System.out.println("任务执行的时间："+context.getFireTime());
			System.out.println("任务下次执行的时间："+context.getNextFireTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

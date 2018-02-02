package com.mxj.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleQuartzTest {

	public static void main(String[] args) {
		
		try {
			
			//调度器容器
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//构建一个JobDetail
			JobDetail job  = JobBuilder.newJob(SimpleJob.class)
					.withIdentity("simple", "g1")
					
					.usingJobData("username", "rob")//定义属性 jobDataMap.put(dataKey, value);
					.build();
			
			//构建一个执行周期器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simple", "g1").startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule() // 使用SimpleTrigger
							.withIntervalInSeconds(1) // 每隔一秒执行一次
							.repeatForever()) // 一直执行，奔腾到老不停歇
					.build();
			
			
			 //加入这个调度
            scheduler.scheduleJob(job, trigger);
			
            //启动这个调度器
            scheduler.start();
			
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		

	}
}

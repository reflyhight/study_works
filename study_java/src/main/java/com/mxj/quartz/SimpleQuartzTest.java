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
			JobDetail jobDetail  = JobBuilder.newJob(SimpleJob.class)
					.withIdentity("simple", "g1")
					.build();
			
			//构造schedulebuilder
			SimpleScheduleBuilder schedulebuilder = SimpleScheduleBuilder.simpleSchedule()
			.withIntervalInSeconds(1)
			.repeatForever();
			
			//构建一个触发器
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simple", "g1")
					.withSchedule(schedulebuilder)
					.startNow()
					.build();
			
			 //加入这个调度
            scheduler.scheduleJob(jobDetail, trigger);
            
            //启动这个调度器
            scheduler.start();
            
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

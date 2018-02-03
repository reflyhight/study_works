package com.mxj.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronQuartzTest {
	public static void main(String[] args) throws SchedulerException {
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		ScheduleBuilder schedulebuilder=CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
		
		
		//构建一个触发器
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simple", "g1")
				.withSchedule(schedulebuilder)
				.startNow()
				.build();
		
		
		
		JobDetail jobDetail= JobBuilder.newJob(CronJob.class)
				.withIdentity("cronjob", "g1")
				.build();
		
		scheduler.scheduleJob(jobDetail, trigger);
		
		scheduler.start();
		
	}
}

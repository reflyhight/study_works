package com.mxj.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(context.getTrigger().getKey().getName());
		JobDetail jobDetail = context.getJobDetail();
		String username = jobDetail.getJobDataMap().getString("username");
		System.out.println(username);
	}
}

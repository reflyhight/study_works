package com.mxj.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {

	
	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		
		//延迟5秒执行
		scheduledThreadPool.schedule( new Runnable() {
			public void run() {
				System.out.println("0000");
			}
		}, 5, TimeUnit.SECONDS);
		
		
		//延迟三秒执行，然后每2秒周期性的执行一次
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("00");
			}
		}, 3, 2, TimeUnit.SECONDS);
	}
}

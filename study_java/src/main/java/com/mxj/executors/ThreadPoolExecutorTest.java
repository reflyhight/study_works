package com.mxj.executors;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

	
	public static void main(String[] args) {
		
		//Executors底层初始化线程池的方法
		
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,5,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
		
		threadPoolExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
}

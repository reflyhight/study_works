package com.mxj.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolTest {

	
	public static void main(String[] args) {
		
		
		//线程数量可以自动调整的，当线程空闲超过60秒时，线程就会被杀死，当池中没有线程以后，没有其他线程再调用的话，进程会结束
		
		//		ThreadPoolExecutor(0, Integer.MAX_VALUE,
		//                60L, TimeUnit.SECONDS,
		//                new SynchronousQueue<Runnable>());
		
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		newCachedThreadPool.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("00");
			}
		});
	}
	
	
	
}

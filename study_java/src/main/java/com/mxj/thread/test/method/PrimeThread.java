package com.mxj.thread.test.method;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author haima	测试是否开启了一个线程，直接运行run，则发生阻塞
 * @date 2017年11月4日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class PrimeThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			
			System.out.println("this is child thread");
			try {
				Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

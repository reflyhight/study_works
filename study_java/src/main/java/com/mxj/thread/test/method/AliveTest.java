package com.mxj.thread.test.method;

/**
 * 
 * @author haima	alive方法
 * @date 2017年11月13日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class AliveTest extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("t1's alive status is:"+this.isAlive());
		this.stop();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

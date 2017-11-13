package com.mxj.thread.test.method;


/**
 * 
 * @author haima	
 * @date 2017年11月13日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class DaemonThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("aliving");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

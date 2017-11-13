package com.mxj.thread.test.method;


/**
 * 优先级，优先级好操作系统关系很大，测试不出效果
 * @author haima
 * @date 2017年11月13日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class PriorityThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}

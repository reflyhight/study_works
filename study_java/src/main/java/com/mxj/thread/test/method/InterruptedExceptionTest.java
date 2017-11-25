package com.mxj.thread.test.method;

/**
 * 
 * @author haima
 * @date 2017年11月5日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class InterruptedExceptionTest extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true){
			try {
				Thread.sleep(100000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("throw the InterruptedException");
			}
		}
	}
}

package com.mxj.thread.test.synch.objectLock;


/**
 * 多个方法都使用sync
 * @author haima
 * @date 2017年11月14日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class MoreMethod {
	
	
	public synchronized void methodA(){
		System.out.println("A");
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("A_End");
	}
	
	public synchronized void methodB(){
		System.out.println("B");
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("B_End");
	}

}

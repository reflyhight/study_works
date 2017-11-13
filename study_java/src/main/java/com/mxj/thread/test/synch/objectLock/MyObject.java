package com.mxj.thread.test.synch.objectLock;


public class MyObject {

	
	public synchronized void methondA(){//排队进入
		System.out.println("begin methondA threadName:"+Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
}

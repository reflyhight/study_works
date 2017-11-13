package com.mxj.thread.test.synch.objectLock;

public class ThreadA extends Thread{
	MyObject object;
	public ThreadA(MyObject object){
		this.object=object;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		object.methondA();
	}
	
}


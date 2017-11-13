package com.mxj.thread.test.synch.objectLock;

public class ThreadB extends Thread{
	MyObject object;
	public ThreadB(MyObject object){
		this.object=object;
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		object.methondA();
	}
}

package com.mxj.thread.test.synch.simple;

public class ThreadA extends Thread{
	
	private SelfvarNum selfvarNum;
	public ThreadA(SelfvarNum selfvarNum){
		this.selfvarNum=selfvarNum;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		selfvarNum.addI("a");
		super.run();
	}
}

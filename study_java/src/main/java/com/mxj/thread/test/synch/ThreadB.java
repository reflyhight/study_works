package com.mxj.thread.test.synch;

public class ThreadB extends Thread{
	private SelfvarNum selfvarNum;
	public ThreadB(SelfvarNum selfvarNum){
		this.selfvarNum=selfvarNum;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		this.selfvarNum.addI("b");
		super.run();
	}
}

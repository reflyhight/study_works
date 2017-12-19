package com.mxj.thread.test.lock.reentrant;

public class ThreadA extends Thread{

	private Service service;

	public ThreadA(Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < 10000; i++) {
			this.service.set();
		}
	}
	
}

package com.mxj.thread.test.lock.reentrant;

public class MainTest {

	
	public static void main(String[] args) {
		Service service= new Service();
		ThreadA a= new ThreadA(service);
		ThreadB b= new ThreadB(service);
		
		
		
		a.start();
		
		b.start();
	}
	
}

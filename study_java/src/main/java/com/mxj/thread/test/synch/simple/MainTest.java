package com.mxj.thread.test.synch.simple;

public class MainTest {
	
	public static void main(String[] args) {
		
		//		SelfvarNum selfvarNum= new SelfvarNum();
		//		ThreadA ta= new ThreadA(selfvarNum);
		//		ThreadB tb= new ThreadB(selfvarNum);
		//		
		//		ta.start();
		//		tb.start();
		
			SelfvarNum selfvarNum_a= new SelfvarNum();
			SelfvarNum selfvarNum_b= new SelfvarNum();
			
			ThreadA ta= new ThreadA(selfvarNum_a);
			ThreadB tb= new ThreadB(selfvarNum_b);
			
			ta.start();
			tb.start();
			//
		
	}
	
	
	

}

package com.mxj.thread.test.method;

public class NormalThread extends Thread{
	@Override
	public void run() {
		Long start= System.currentTimeMillis();
		for(int i=0;i<1000L;i++){
			System.out.println(i);
		}
		
		System.out.println("======normal========="+(System.currentTimeMillis()-start));
	}
}

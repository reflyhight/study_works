package com.mxj.thread.test.method;

public class YieldThread extends Thread{
	
	
	@Override
	public void run() {
		Long start= System.currentTimeMillis();
		for(int i=0;i<1000L;i++){
			System.out.println(i);
			yield();
		}
		
		System.out.println("======yield========="+(System.currentTimeMillis()-start));
	}
}


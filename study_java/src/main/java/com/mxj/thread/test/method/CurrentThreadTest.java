package com.mxj.thread.test.method;

/**
 * 
 * @author haima	直接运行run方法和start方法的区别currentThread
 * @date 2017年11月13日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class CurrentThreadTest extends Thread{
		
		CurrentThreadTest(String threadName){
			this.setName(threadName);//设置线程名
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(currentThread().getName());
			super.run();
			
		}
}

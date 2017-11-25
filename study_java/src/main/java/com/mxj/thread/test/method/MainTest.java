package com.mxj.thread.test.method;



/**
 * 
 * @author haima
 * @date 2017年11月5日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class MainTest {
	public static void main(String[] args) {
		
		YieldThread t1= new YieldThread();
		t1.start();
		
		NormalThread normalThread =  new NormalThread();
		normalThread.start();
		
		
		//		DaemonThread t1= new DaemonThread();
		//		t1.setDaemon(true);//必须在开启线程之前设置为守护线程
		//		t1.start();
		//		try {
		//			Thread.sleep(3000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		System.out.println("game over");
		
//		Thread.currentThread().setPriority(6);
//		PriorityThread t1= new PriorityThread();
//		t1.start();
//		System.out.println(t1.getPriority());
		
		
		
		//		StopThread st= new StopThread();
		//		st.start();
		//		try {
		//			Thread.sleep(500L);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		
		//		st.stop();
		//		st.printM1M2();
		
		
		
		//		CurrentThreadTest t1= new CurrentThreadTest("haima");
		//		String threadName1=t1.currentThread().getName();
		//		String threadName2=Thread.currentThread().getName();
		//		String t1ThreadName = t1.getName();
		//		
		//		System.out.println(threadName1);
		//		System.out.println(threadName2);
		//		System.out.println(t1ThreadName);
		
		
		//		AliveTest t1= new AliveTest();
		//		t1.run();
		//		try {
		//			Thread.sleep(200);//保证t1被启动了
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		System.out.println("t1's alive status is:"+t1.isAlive());
		//		try {
		//			Thread.sleep(2000);
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		System.out.println("t1's alive status is:"+t1.isAlive());
				
				
		
		
		
		//		InterruptedExceptionTest t1= new InterruptedExceptionTest();
		//		t1.start();
		//		try {
		//			Thread.sleep(1000);//主线程暂停1秒，保证t1线程已经被启动了
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		t1.interrupt();
		//		System.out.println("I want to interrupt");
		//		System.out.println("t1's interrupt status is :"+t1.isInterrupted());
		
		
		
		//		Thread.currentThread().interrupt();
		//		System.out.println("currentThread is interrupted:"+Thread.interrupted());
		//		System.out.println("currentThread is interrupted:"+Thread.interrupted());
		//		
		
		//		//当前线程名
		//		String currentThreadName = Thread.currentThread().getName();
		//		System.out.println("currentThread:"+currentThreadName);
		//		InterruptThread t1= new InterruptThread();
		//		t1.start();
		//		//t1线程对象线程名
		//		String t1Name = t1.getName();
		//		System.out.println("t1ThreadName:"+t1Name);
		//		
		//		//中断请求信号
		//		t1.interrupt();
		//		System.out.println("t1 is interrupted:"+t1.isInterrupted());
		//		System.out.println("t1 is interrupted:"+t1.isInterrupted());
		//		System.out.println("currentThread is interrupted:"+Thread.interrupted());
		
		
		
		
		//		PrimeThread t1= new PrimeThread();
		//		t1.run();
		
		//		while(true){
		//			System.out.println("this is Main thread");
		//			try {
		//				Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
		//			} catch (InterruptedException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		}
		
		//		RunableThread t2= new RunableThread();
		//		new Thread(t2).start();
		
		//		InterruptThread interruptThread = new InterruptThread();
		//		interruptThread.start();
		//		try {
		//			Thread.sleep(1000);//保证线程启动，1秒后，主线程发起中断信号
		//		} catch (InterruptedException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		System.out.println("interrupt");
		//		interruptThread.interrupt();
		//		System.exit(0);
	
	}
}

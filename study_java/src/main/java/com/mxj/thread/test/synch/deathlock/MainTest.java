package com.mxj.thread.test.synch.deathlock;

public class MainTest {
	public static void main(String[] args) throws InterruptedException {
		
		DeathThread t1= new DeathThread("a");
		new Thread(t1).start();
		
		Thread.sleep(200);
		
		t1.setSwitchStr("b");
		new Thread(t1).start();
		
		//检测死锁
		//jdk/bin目录中的jps工具先查看进程ID
		//在用jstack -l ID
	}
}

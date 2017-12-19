package com.mxj.thread.test.lock.reentrant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private boolean hasValue = false;

	public void set() {
		lock.lock();
		try {
			while (hasValue) {

				condition.await();
			}
			System.out.println("xxx");
			hasValue=true;
			condition.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void get(){
		
		lock.lock();
		try {
			while (!hasValue) {

				condition.await();
			}
			System.out.println("yyy");
			hasValue=false;
			condition.signal();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}

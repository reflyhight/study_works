package com.mxj.thread.test.synch.deathlock;

public class DeathThread implements Runnable {

	Object lock1 = new Object();
	Object lock2 = new Object();

	public String getSwitchStr() {
		return switchStr;
	}

	public void setSwitchStr(String switchStr) {
		this.switchStr = switchStr;
	}

	private String switchStr;

	public DeathThread(String switchStr) {
		this.switchStr = switchStr;
	}

	
	//嵌套锁，，，
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if ("a".equals(this.switchStr)) {
			synchronized (lock1) {
				try {
					System.out.println(this.switchStr);
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println(this.switchStr);
				}
			}


		} else if ("b".equals(this.switchStr)) {

			synchronized (lock2) {
				try {
					System.out.println(this.switchStr);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				synchronized (lock1) {
					System.out.println(this.switchStr);
				}
			}


		}

	}

}

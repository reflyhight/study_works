package com.mxj.thread.test.method;

/**
 * stop	造成数据不一致的情况
 * @author haima
 * @date 2017年11月13日 
 * @com blog.mxjhaima.com
 * @email jh624haima@126.com
 */
public class StopThread extends Thread {

	private String m1;
	private String m2;

	public String getM1() {
		return m1;
	}

	public void setM1(String m1) {
		this.m1 = m1;
	}

	public String getM2() {
		return m2;
	}

	public void setM2(String m2) {
		this.m2 = m2;
	}

	public void printM1M2() {
		System.out.println("m1:" + m1);
		System.out.println("m2:" + m2);
	}

	@Override
	public void run() {
		super.run();
		// TODO Auto-generated method stub
		setM1("rob");
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setM2("jiang");

	}

}

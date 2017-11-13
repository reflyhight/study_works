package com.mxj.singleton.test;

/**
 * 
 * 
 * @author Rob Jiang
 * @date 2017年10月13日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class MyThread extends Thread{

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(MySingle.getInstance().hashCode());
	}
}

package com.mxj.singleton.test;


/**
 * 
 * 
 * @author Rob Jiang
 * @date 2017年10月13日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class Main {

	public static void main(String[] args) {
		MyThread t1= new MyThread();
		MyThread t2= new MyThread();
		MyThread t3= new MyThread();
		
		t1.start();
		t2.start();
		t3.start();
		
		
		
	}
	
}

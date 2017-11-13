package com.mxj.thread.test.synch;

public class SelfvarNum {
	
	private int num=0;
	
	//同步方法与非同步方法
	public /*synchronized*/ void  addI(String username){
		try {
			if(username.equals("a")){
				num=100;
				System.out.println("a set over");
				Thread.sleep(2000);
			}else{
				num=200;
				System.out.println("b set over");
			}
			
			System.out.println(username+":num="+num);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

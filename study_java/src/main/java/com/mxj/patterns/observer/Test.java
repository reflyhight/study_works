package com.mxj.patterns.observer;

public class Test {

	public static void main(String[] args) {
		 Reader reader= new Reader();
		 Publisher publisher=new Publisher();
		 publisher.addObserver(reader);
		
		 publisher.publish();
		 publisher.publish();

//		Wolf wolf = new Wolf("wolf1");
//		Sheep sheep1 = new Sheep("sheep1");
//		Sheep sheep2 = new Sheep("sheep2");
//		Sheep sheep3 = new Sheep("sheep3");
//		// 注册观察者,sheep1,sheep2加入，sheep3未加入
//		wolf.addObserver(sheep1);
//		wolf.addObserver(sheep2);
//		String wolfStat = "hungry";
//		// wolf begin cry
//		wolf.cry(wolfStat);
	}
}

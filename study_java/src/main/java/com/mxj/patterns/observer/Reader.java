package com.mxj.patterns.observer;

import java.util.Observable;
import java.util.Observer;

public class Reader implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("ok,I got it");
	}
	
	
	

}

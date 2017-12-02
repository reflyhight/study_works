package com.mxj.patterns.observer;

import java.util.Observable;

public class Publisher extends Observable{

	
	public void publish(){
		System.out.println("do something");
		setChanged();
		notifyObservers();
	}
}

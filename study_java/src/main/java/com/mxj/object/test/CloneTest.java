package com.mxj.object.test;

public class CloneTest {

	public static void main(String[] args) {
		CloneObject obj= new CloneObject("rob");
		try {
			CloneObject obj1=(CloneObject)obj.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}

class CloneObject implements Cloneable{
	private String name;
	public CloneObject(String name){
		this.name=name;
	}
	
	@Override
	public CloneObject clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (CloneObject)super.clone();
	}
}
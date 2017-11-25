package com.mxj.singleton.test;

/**
 * 
 * 
 * @author Rob Jiang
 * @date 2017年10月13日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class MySingle {

	private static MySingle instance;
	
	static {
		instance=new MySingle();
	}
	
	private MySingle() {
	}

	public static MySingle getInstance() {
		return instance;
	}
}

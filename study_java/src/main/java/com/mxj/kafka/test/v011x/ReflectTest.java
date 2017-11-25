package com.mxj.kafka.test.v011x;

import org.apache.kafka.common.utils.Utils;


/**
 * 
 * @author Rob Jiang
 * @date 2017年10月20日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class ReflectTest {

	public static <T> T newInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new RuntimeException("");
			// TODO Auto-generated catch block
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("");
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		//比较后面两行代码的区别
		//Utils.newInstance(Class.forName(klass, true, Utils.getContextOrKafkaClassLoader()).asSubclass(base));
		//Utils.newInstance(Class.forName(klass, true, Utils.getContextOrKafkaClassLoader())
		
		Class<? extends Child> asSubclass = Class.forName("com.mxj.kafka.test.v011x.Child", true, Utils.getContextOrKafkaClassLoader()).asSubclass(Child.class);
		Class<?> forName = Class.forName("com.mxj.kafka.test.v011x.Child", true, Utils.getContextOrKafkaClassLoader());
		System.out.println("00");
	}

	
	
}


class Parents{
	void sayhello(){
		
	}
}

class Child extends Parents{
	/* (non-Javadoc)
	 * @see com.mxj.kafka.test.v011x.Parents#sayhello()
	 */
	@Override
	void sayhello() {
		// TODO Auto-generated method stub
		super.sayhello();
	}
}



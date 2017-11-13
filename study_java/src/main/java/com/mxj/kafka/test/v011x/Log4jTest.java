package com.mxj.kafka.test.v011x;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Rob Jiang
 * @date 2017年10月17日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class Log4jTest {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Log4jTest.class);
		
		logger.warn("hello");
		logger.info("haha");
		logger.debug("hhh");
	}
}

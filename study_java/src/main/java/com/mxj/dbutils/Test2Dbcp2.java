package com.mxj.dbutils;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class Test2Dbcp2 {

	private static DataSource ds;
	static {
		Properties properties= new Properties();
		try {
			properties.load(Test2Dbcp2.class.getClassLoader().getResourceAsStream("dbcp2.properties"));
			ds=BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static DataSource getDataSource(){
		return ds;
	}
	
}

package com.mxj.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

/**
 * 官方第一个demo
 * 
 * @author Rob Jiang
 * @dat 2017年11月25日
 * @email jh624haima@126.com
 * @blog blog.mxjhaima.com
 */
public class Test1DButils {

	public static void main(String[] args) {
	}

	ResultSetHandler<Object[]> h = new ResultSetHandler<Object[]>() {
		public Object[] handle(ResultSet rs) throws SQLException {
			if (!rs.next()) {
				return null;
			}

			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			Object[] result = new Object[cols];

			for (int i = 0; i < cols; i++) {
				result[i] = rs.getObject(i + 1);
			}

			return result;
		}
	};

	@Test // The following example demonstrates how these classes are used
			// together.
	public void test1() {

		// Create a ResultSetHandler implementation to convert the
		// first row into an Object[].

		DataSource dataSource = Test2Dbcp2.getDataSource();
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner(dataSource);

		// Execute the query and get the results back from the handler
		try {
			Object[] result = run.query("SELECT * FROM base_info WHERE star_id=?", h, 1);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test // You could also perform the previous query using a
			// java.sql.Connection object instead of a DataSource. Notice that
			// you are responsible for closing the Connection in this example.
	public void test2() throws SQLException {
		QueryRunner run = new QueryRunner();

		Connection conn = Test2Dbcp2.getDataSource().getConnection();
		try {
			Object[] result = run.query(conn, "SELECT * FROM base_info WHERE star_id=?", h, 1);
			System.out.println(result);
			// do something with the result
		} finally {
			// Use this helper method so we don't have to check for null
			conn.close();
		}

	}

	// You can not only fetch data from the database - you can also insert or
	// update data. The following example will first insert a person into the
	// database and after that change the person's height.
	public void test3() {
		QueryRunner run = new QueryRunner(Test2Dbcp2.getDataSource());
		try {
			// Execute the SQL update statement and return the number of
			// inserts that were made
			int inserts = run.update("INSERT INTO Person (name,height) VALUES (?,?)", "John Doe", 1.82);
			// The line before uses varargs and autoboxing to simplify the code

			// Now it's time to rise to the occation...
			int updates = run.update("UPDATE Person SET height=? WHERE name=?", 2.05, "John Doe");
			// So does the line above
		} catch (SQLException sqle) {
			// Handle it
		}
	}
	
	@Test
	public void test4() throws SQLException{
		QueryRunner run = new QueryRunner(Test2Dbcp2.getDataSource());

		// Use the BeanHandler implementation to convert the first
		// ResultSet row into a Person JavaBean.
		ResultSetHandler<User> h = new BeanHandler<User>(User.class);

		// Execute the SQL statement with one replacement parameter and
		// return the results in a new Person object generated by the BeanHandler.
		User u = run.query(
		    "select * from user where name=?", h, "rob"); 
	}

}

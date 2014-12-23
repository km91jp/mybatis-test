package com.example;

import java.sql.SQLException;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.test.Insert;
import com.example.test.InsertAndUpdate;
import com.example.executor.Test1NExecutor;
import com.example.executor.TestExecutor;
import com.example.test.Update;
import com.example.test1n.Select;

public class Main {

	public static void main(String args[]) throws SQLException {
		TestExecutor executor;
		ApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
/*
			executor = context.getBean(TestExecutor.class);
			executor.setTestCount(2);
			executor.setExecutionCount(3);
			executor.executeByMyBatis2("insert&update", new InsertAndUpdate());
			executor.executeByMyBatis2("insert,update", new Insert(),
					new Update());
			executor.executeByMyBatis3("insert&update", ExecutorType.SIMPLE,
					new InsertAndUpdate());
			executor.executeByMyBatis3("insert,update", ExecutorType.SIMPLE,
					new Insert(), new Update());
			executor.executeByMyBatis3("insert&update", ExecutorType.REUSE,
					new InsertAndUpdate());
			executor.executeByMyBatis3("insert,update", ExecutorType.REUSE,
					new Insert(), new Update());
			executor.executeByMyBatis3("insert&update", ExecutorType.BATCH,
					new InsertAndUpdate());
			executor.executeByMyBatis3("insert,update", ExecutorType.BATCH,
					new Insert(), new Update());
*/
			Test1NExecutor test1nExecutor = context.getBean(Test1NExecutor.class);
			test1nExecutor.executeByMyBatis21N("1n", new Select());
			test1nExecutor.executeByMyBatis31N("1n", new Select());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (context != null) {
				((ClassPathXmlApplicationContext) context).close();
			}
		}
	}
}
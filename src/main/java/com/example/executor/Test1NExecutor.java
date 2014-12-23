package com.example.executor;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.TransactionManager;

import org.apache.ibatis.session.ExecutorType;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.dao.MyBatis1NTestOperation;
import com.example.dao.MyBatisTestOperation;
import com.example.dto.Blog;
import com.example.test.TestCallback;
import com.example.test1n.Test1NCallback;

@Component
public class Test1NExecutor {

	private Logger LOGGER = LoggerFactory.getLogger(TestExecutor.class);

	@Autowired
	@Qualifier("myBatis21NTestOperation")
	private MyBatis1NTestOperation operation1n2;

	@Autowired
	@Qualifier("myBatis31NTestOperation")
	private MyBatis1NTestOperation operation1n3;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private int testCount = 1; // default
	private int executionCount = 1; // default

	public void executeByMyBatis21N(String testName, Test1NCallback... callback)
			throws SQLException {
		execute(executionCount, "[MyBatis2]" + testName, operation1n2, callback);
	}

	public void executeByMyBatis31N(String testName, Test1NCallback... callback)
			throws SQLException {
		execute(executionCount, "[MyBatis3]" + testName, operation1n3, callback);
	}

	private void execute(int executionCount, String testName,
			MyBatis1NTestOperation operation, Test1NCallback... callback)
			throws SQLException {
		long totalTime = 0;
		StringBuilder logBuilder = new StringBuilder();
		LOGGER.info(String.format("execute %s", testName));
		logBuilder.append("elapsed time:[");
		for (int tryCount = 1; tryCount <= executionCount; tryCount++) {
			if (tryCount != 1) {
				logBuilder.append(", ");
			}
			long startTime = System.currentTimeMillis();
			executeMeasurementTestProcess(operation, callback);
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
			logBuilder.append(String.format("%s", elapsedTime));
			totalTime += elapsedTime;
		}
		logBuilder.append(String.format("] Average:%s",
				Math.ceil(totalTime / executionCount)));
		LOGGER.info(logBuilder.toString());
	}

	private void executeMeasurementTestProcess(MyBatis1NTestOperation operation,
			Test1NCallback... callback) throws SQLException {
		TransactionStatus tranStat = null;
		try {
			tranStat = transactionManager
					.getTransaction(new DefaultTransactionDefinition());
			for (int k = 0; k < callback.length; k++) {
				callback[k].setOperation(operation);
				for (int i = 1; i <= testCount; i++) {
					List<Blog> blogList = callback[k].execute();
					LOGGER.info(String.format("num=%d",blogList.size()));
					for(Blog blog : blogList){
						LOGGER.info(blog.toString());
					}
					// transactionManager.commit(tranStat);
					// tranStat = transactionManager.getTransaction(new
					// DefaultTransactionDefinition());
				}
			}
			transactionManager.commit(tranStat);
		} finally {
			if (tranStat != null && !tranStat.isCompleted()) {
				transactionManager.rollback(tranStat);
			}
		}
	}

	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}

	public void setExecutionCount(int executionCount) {
		this.executionCount = executionCount;
	}

}

package com.example.executor;

import java.sql.SQLException;

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

import com.example.dao.MyBatisTestOperation;
import com.example.test.TestCallback;
import com.example.test1n.Test1NCallback;

@Component
public class TestExecutor {
	
	private Logger LOGGER = LoggerFactory.getLogger(TestExecutor.class);

	@Autowired
	@Qualifier("myBatis2TestOperation")
	private MyBatisTestOperation operation2;

	@Autowired
	@Qualifier("myBatis3TestOperation")
	private MyBatisTestOperation operation3;

	@Autowired
	@Qualifier("myBatis3ReuseTestOperation")
	private MyBatisTestOperation operation3reuse;

	@Autowired
	@Qualifier("myBatis3BatchTestOperation")
	private MyBatisTestOperation operation3batch;
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	private int testCount = 1; // default
	private int executionCount = 1; // default

	public void executeByMyBatis2(String testName, TestCallback... callback)
			throws SQLException {
		execute(executionCount, "[MyBatis2]" + testName, operation2, callback);
	}
	
	public void executeByMyBatis3(String testName, ExecutorType type, TestCallback... callback)
			throws SQLException {
		switch(type){
		case SIMPLE:
		execute(executionCount, "[MyBatis3]" + testName + "[SIMPLE]",
				operation3, callback);
		break;
		case REUSE:
		execute(executionCount, "[MyBatis3]" + testName + "[REUSE]",
				operation3reuse, callback);
		break;
		case BATCH:
		execute(executionCount, "[MyBatis3]" + testName + "[BATCH]",
				operation3batch, callback);
		break;
		}
	}
	
	public void execute(int executionCount, String testName,
			MyBatisTestOperation operation, TestCallback... callback)
			throws SQLException {
		long totalTime = 0;
		StringBuilder logBuilder = new StringBuilder();
		LOGGER.info(String.format("execute %s", testName));
		logBuilder.append("elapsed time:[");
		for (int tryCount = 1; tryCount <= executionCount; tryCount++) {
			if(tryCount != 1){
				logBuilder.append(", ");
			}
			operation.initialize();
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

	private void executeMeasurementTestProcess(MyBatisTestOperation operation,
			TestCallback... callback) throws SQLException {
		TransactionStatus tranStat = null;
		try {
			tranStat = transactionManager
					.getTransaction(new DefaultTransactionDefinition());
			for (int k = 0; k < callback.length; k++) {
				callback[k].setOperation(operation);
				for (int i = 1; i <= testCount; i++) {
					callback[k].execute(i);
					//transactionManager.commit(tranStat);
					//tranStat = transactionManager.getTransaction(new DefaultTransactionDefinition());
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

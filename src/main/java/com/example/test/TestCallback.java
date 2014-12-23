package com.example.test;

import java.sql.SQLException;

import com.example.dao.MyBatisTestOperation;

public interface TestCallback {
	public void setOperation(MyBatisTestOperation operation);
	public void execute(int i) throws SQLException;
}

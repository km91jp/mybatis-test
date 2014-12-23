package com.example.test;

import java.sql.SQLException;
import java.util.Date;

import com.example.dao.MyBatisTestOperation;
import com.example.dto.MyBatisTestInsert;

public class Insert implements TestCallback{
	public void execute(int i) throws SQLException {
		// insert
		MyBatisTestInsert insertObj = new MyBatisTestInsert();
		insertObj.setId(i);
		insertObj.setName("name" + i);
		insertObj.setCreateDate(new Date());
		//System.out.println("execute insert!");
		operation.insert(insertObj);
	}
	private MyBatisTestOperation operation;
	@Override
	public void setOperation(MyBatisTestOperation operation) {
		this.operation = operation;
	}
}

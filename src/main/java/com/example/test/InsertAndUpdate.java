package com.example.test;

import java.sql.SQLException;
import java.util.Date;

import com.example.dao.MyBatisTestOperation;
import com.example.dto.MyBatisTestInsert;
import com.example.dto.MyBatisTestUpdate;

public class InsertAndUpdate implements TestCallback {
	public void execute(int i) throws SQLException {
		// insert
		MyBatisTestInsert insertObj = new MyBatisTestInsert();
		insertObj.setId(i);
		insertObj.setName("name" + i);
		insertObj.setCreateDate(new Date());
		operation.insert(insertObj);
		//System.out.println("execute insert and...");
		// update
		MyBatisTestUpdate updateObj = new MyBatisTestUpdate();
		updateObj.setUpdateId(i);
		updateObj.setName("name" + i + "_update");
		updateObj.setModDate(new Date());
		operation.update(updateObj);
		//System.out.println("execute update!");
	}

	private MyBatisTestOperation operation;

	@Override
	public void setOperation(MyBatisTestOperation operation) {
		this.operation = operation;
	}
}

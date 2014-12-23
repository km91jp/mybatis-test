package com.example.test;

import java.sql.SQLException;
import java.util.Date;

import com.example.dao.MyBatisTestOperation;
import com.example.dto.MyBatisTestUpdate;

public class Update implements TestCallback {

	@Override
	public void execute(int i) throws SQLException {
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

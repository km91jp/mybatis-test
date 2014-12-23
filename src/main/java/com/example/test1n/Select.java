package com.example.test1n;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.example.dao.MyBatis1NTestOperation;
import com.example.dao.MyBatisTestOperation;
import com.example.dto.Blog;
import com.example.dto.MyBatisTestInsert;
import com.example.test.TestCallback;

public class Select implements Test1NCallback{
	private MyBatis1NTestOperation operation;
	public List<Blog> execute() throws SQLException {
		return this.operation.selectAll();
	}
	@Override
	public void setOperation(MyBatis1NTestOperation operation) {
		this.operation = operation;
	}
}

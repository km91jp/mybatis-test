package com.example.test1n;

import java.sql.SQLException;
import java.util.List;

import com.example.dao.MyBatis1NTestOperation;
import com.example.dto.Blog;
import com.example.test.TestCallback;

public interface Test1NCallback {
	public void setOperation(MyBatis1NTestOperation operation);
	public List<Blog> execute() throws SQLException;
}

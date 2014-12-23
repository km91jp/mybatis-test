package com.example.dao;

import com.example.dto.MyBatisTestInsert;
import com.example.dto.MyBatisTestUpdate;

public interface MyBatisTestOperation {
	
	public int insert(MyBatisTestInsert insertObject);
	
	public int update(MyBatisTestUpdate updateObject);
	
	public int initialize();
	
}

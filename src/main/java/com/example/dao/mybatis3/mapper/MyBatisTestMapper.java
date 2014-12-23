package com.example.dao.mybatis3.mapper;

import com.example.dto.MyBatisTestInsert;
import com.example.dto.MyBatisTestUpdate;

public interface MyBatisTestMapper {
	
	public int insert(MyBatisTestInsert insertObj);
	
	public int update(MyBatisTestUpdate updateObj);
	
	public int initialize();
	
}

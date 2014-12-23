package com.example.dao.mybatis3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.dao.MyBatisTestOperation;
import com.example.dao.mybatis3.mapper.MyBatisTestReuseMapper;
import com.example.dto.MyBatisTestInsert;
import com.example.dto.MyBatisTestUpdate;

@Component("myBatis3ReuseTestOperation")
public class MyBatis3ReuseTestOperation implements MyBatisTestOperation {

	@Autowired
	@Qualifier("reuseModeMapper")
	private MyBatisTestReuseMapper mapper;

	public int insert(MyBatisTestInsert insertObject) {
		return mapper.insert(insertObject);
	}

	public int update(MyBatisTestUpdate updateObject) {
		return mapper.update(updateObject);
	}

	public int initialize() {
		return mapper.initialize();
	}

}

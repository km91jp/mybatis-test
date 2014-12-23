package com.example.dao.mybatis3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.dao.MyBatis1NTestOperation;
import com.example.dao.mybatis3.mapper.MyBatis1NTestMapper;
import com.example.dto.Blog;

@Component("myBatis31NTestOperation")
public class MyBatis31NTestOperation implements MyBatis1NTestOperation {

	@Autowired
	@Qualifier("1nMapper")
	private MyBatis1NTestMapper mapper;

	@Override
	public List<Blog> selectAll() {
		return mapper.selectAll();
	}

}

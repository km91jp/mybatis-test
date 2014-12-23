package com.example.dao.mybatis2;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.example.dao.MyBatis1NTestOperation;
import com.example.dto.Blog;

@Component("myBatis21NTestOperation")
public class MyBatis21NTestOperation extends SqlMapClientDaoSupport implements MyBatis1NTestOperation{
	
	@Override
	public List<Blog> selectAll() {
		SqlMapClientTemplate client = getSqlMapClientTemplate();
		return client.queryForList("selectAll", null);
	}

}

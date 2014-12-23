package com.example.dao.mybatis2;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.example.dao.MyBatisTestOperation;
import com.example.dto.MyBatisTestInsert;
import com.example.dto.MyBatisTestUpdate;

@Component("myBatis2TestOperation")
public class MyBatis2TestOperation extends SqlMapClientDaoSupport implements MyBatisTestOperation{
	
	@Override
	public int insert(MyBatisTestInsert insertObject) {
		SqlMapClientTemplate client = getSqlMapClientTemplate();
		return client.update("insert", insertObject);
	}
	
	@Override
	public int update(MyBatisTestUpdate updateObject) {
		SqlMapClientTemplate client = getSqlMapClientTemplate();
		return client.update("update", updateObject);
	}

	@Override
	public int initialize() {
		SqlMapClientTemplate client = getSqlMapClientTemplate();
		return client.delete("initialize", null);
	}

}

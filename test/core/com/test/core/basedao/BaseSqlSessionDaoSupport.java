package com.test.core.basedao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseSqlSessionDaoSupport extends SqlSessionDaoSupport {
	public SqlSessionFactory sqlSessionFactory = null;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
		this.sqlSessionFactory = sqlSessionFactory;
	}

}

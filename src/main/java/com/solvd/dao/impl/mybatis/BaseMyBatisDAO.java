package com.solvd.dao.impl.mybatis;

import com.solvd.common.MyBatisConfig;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class BaseMyBatisDAO {
    protected static final SqlSessionFactory SQL_SESSION_FACTORY = MyBatisConfig.getSqlSessionFactory();
}

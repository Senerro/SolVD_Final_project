package com.solvd.common;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class MyBatisConfig {
    private static final String CONFIG_FILE = "mybatis-config.xml";
    @Getter
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try (InputStream is = Resources.getResourceAsStream(CONFIG_FILE)) {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(is);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing SqlSessionFactory: " + e);
        }
    }
}

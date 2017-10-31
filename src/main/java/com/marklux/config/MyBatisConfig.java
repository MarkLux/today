package com.marklux.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.SQLException;

import static com.marklux.config.ConstantSetting.MODEL_PACKAGE;


/**
 * Created by mark on 17/10/24.
 */
@Configuration
public class MyBatisConfig {
    /*
        Druid配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        /**
         * 配置连接池
         */
        DruidDataSource dataSource = new DruidDataSource();

        /**
         * 初始化，最小，最大大小
         */
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);

        /**
         * 配置获取连接等待超时时间
         */
        dataSource.setMaxWait(60000);
        /**
         * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
         */
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        /**
         * 连接错误尝试重新连接次数
         */
        dataSource.setConnectionErrorRetryAttempts(2);
        /**
         * 配置连接在池中最小的生存时间，单位是毫秒
         */
        dataSource.setMinEvictableIdleTimeMillis(300000);

        /**
         * 配置监控统计拦截的filters，去掉后无法监控界面sql
         */
        dataSource.setValidationQuery("select 1");
        try {
            dataSource.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;

    }

    // 注入到IoC容器
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 数据源注入
        bean.setDataSource(druidDataSource());
        // 领域模型包名
        bean.setTypeAliasesPackage(MODEL_PACKAGE);
        // mybatis配置文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));
        bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));

        return bean.getObject();
    }
}

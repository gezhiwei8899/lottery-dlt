package com.gezhiwe.lotterydlt.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Magoo
 */
@Configuration
@MapperScan(basePackages = "com.gezhiwe.lotterydlt.dao",
        sqlSessionTemplateRef = "storeSqlSessionTemplate")
public class DsConfigOfBackstage {

    @Bean(name = "storeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.store")
    @Primary
    public DataSource backstageDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "storeSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactoryOfBackstage(
            @Qualifier("storeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources1 = pathMatchingResourcePatternResolver.getResources("classpath:mybatis/mapper/*.xml");
        Resource[] resources2 = pathMatchingResourcePatternResolver.getResources("classpath:mybatis/generater/*.xml");
        Resource[] resources = new Resource[resources1.length + resources2.length];

        System.arraycopy(resources1, 0, resources, 0, resources1.length);
        System.arraycopy(resources2, 0, resources, resources1.length, resources2.length);
        bean.setMapperLocations(resources);
        return bean.getObject();

    }

    @Bean(name = "storeTransactionManager")
    @Primary
    public DataSourceTransactionManager backstageTransactionManager(
            @Qualifier("storeDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "storeSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("storeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
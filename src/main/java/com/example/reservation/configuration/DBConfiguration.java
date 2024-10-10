package com.example.reservation.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")  // properties 파일 위치
public class DBConfiguration {

    @Autowired
    private ApplicationContext applicationContext;  // 스프링컨테이너(Bean의 생명주기 등 관리)

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")  // spring.datasources.hikari로 시작하는 설정을 매핑
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){  // DB 연결 관리
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();  // SQL 실행, 트랜젝션 관리 등 담당
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));  // mybatis의 xml 파일 경로
        factoryBean.setTypeAliasesPackage("com.example.demo.*");  // mybatis에서 사용할 DTO클래스의 패키지 경로
        factoryBean.setConfiguration(mybatisConfg());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate((sqlSessionFactory()));
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")  // mybatis.configuration으로 시작하는 설정을 매핑
    public org.apache.ibatis.session.Configuration mybatisConfg(){
        return new org.apache.ibatis.session.Configuration();
    }
}
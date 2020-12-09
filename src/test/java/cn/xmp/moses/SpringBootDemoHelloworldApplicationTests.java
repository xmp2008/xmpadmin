package cn.xmp.moses;

import cn.xmp.modules.security.shiro.properties.ShiroProperties;
import org.apache.shiro.hazelcast.cache.HazelcastCacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests {
	@Autowired
	DataSource dataSource;
	@Autowired
	private ShiroProperties properties;

	@Test
	public void contextLoads() {
		//获取数据库连接方式
		Class<? extends DataSource> aClass = dataSource.getClass();
		System.out.println("-------------"+aClass);
		try {
			//连接数据库
			Connection connection = dataSource.getConnection();
			System.out.println(connection);
			//关闭连接
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	@Test
	public void shiroTest() {
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ShiroProperties.class);
//		ShiroProperties bean = applicationContext.getBean(ShiroProperties.class);
//		System.out.println("properties = " + bean);
//		System.out.println("bean.getLoginUrl() = " + bean.getLoginUrl());
		System.out.println("properties = " + properties);
		System.out.println("properties.getDefaultViewUrl() = " + properties.getDefaultViewUrl());
		ApplicationContext context = new AnnotationConfigApplicationContext(HazelcastCacheManager.class);
		HazelcastCacheManager bean = context.getBean(HazelcastCacheManager.class);
		System.out.println("bean = " + bean);
	}

}

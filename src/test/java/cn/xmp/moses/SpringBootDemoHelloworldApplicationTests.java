package cn.xmp.moses;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoHelloworldApplicationTests {
	@Autowired
	DataSource dataSource;

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

}

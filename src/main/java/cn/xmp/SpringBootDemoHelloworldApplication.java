package cn.xmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * SpringBoot启动类
 * </p>
 *
 * @author: xiemopeng
 * @date: Created in 2020/12/09
 * @copyright: Copyright (c)
 * @version: V1.0
 * @modified: xiemopeng
 */
@SpringBootApplication
@MapperScan("cn.xmp.modules.system.mapper*")
public class SpringBootDemoHelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoHelloworldApplication.class, args);
	}

}

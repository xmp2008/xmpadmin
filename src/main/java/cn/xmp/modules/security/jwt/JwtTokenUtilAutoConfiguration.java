package cn.xmp.modules.security.jwt;

import cn.xmp.modules.security.jwt.properties.JwtProperties;
import cn.xmp.modules.security.jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author wangliang
 * @since 2017/12/22
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
//@ConditionalOnProperty(prefix = "xyjwt", value = "enabled", havingValue = "true")
public class JwtTokenUtilAutoConfiguration {

    private JwtProperties jwtProperties;

//    @Resource(name = "hazelcastInstance")
//    private HazelcastInstance hazelcastInstance;

    @Autowired
    public JwtTokenUtilAutoConfiguration(JwtProperties jwtProperties
//            , HazelcastInstance hazelcastInstance
    ) {
        this.jwtProperties = jwtProperties;
//        this.hazelcastInstance = hazelcastInstance;
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil(jwtProperties
//                , hazelcastInstance
        );
    }

}

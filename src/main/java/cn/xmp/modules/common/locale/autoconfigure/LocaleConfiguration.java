package cn.xmp.modules.common.locale.autoconfigure;

import cn.xmp.modules.common.locale.utlis.LocaleService;
import cn.xmp.modules.common.locale.utlis.MessageAspect;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.beans.ConstructorProperties;
import java.util.Locale;

@Configuration
@EnableConfigurationProperties({LocaleProperties.class})
public class LocaleConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware {
    private LocaleProperties localeProperties;
    private static final String CLASS_PATH = "classpath:";

    public void setEnvironment(Environment environment) {
    }

    @Bean(
        name = {"localeResolver"}
    )
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName(this.localeProperties.getParamName());
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        if (null != this.localeProperties.getBasenames()) {
            messageSource.setBasenames(this.localeProperties.getBasenames());
        } else {
            String[] defaultBasenames = new String[]{"classpath:i18n/messages", "classpath:i18n/validatorMessages", "classpath:i18n/commonMessages"};
            messageSource.setBasenames(defaultBasenames);
        }

        messageSource.setCacheSeconds(this.localeProperties.getCacheSeconds());
        messageSource.setDefaultEncoding(this.localeProperties.getDefaultEncoding());
        return messageSource;
    }

    @Bean
    public LocaleService localeService() {
        return new LocaleService(this.messageSource());
    }

    @Bean
    public MessageAspect messageAspect() throws NoSuchMethodException {
        Class<? extends MessageAspect> aopClass = this.localeProperties.getAopClass();
        MessageAspect aop = null;
        if (null != aopClass) {
            aop = (MessageAspect)BeanUtils.instantiateClass(aopClass.getConstructor(LocaleService.class), new Object[]{this.localeService()});
        }

        return aop;
    }

    @ConstructorProperties({"localeProperties"})
    public LocaleConfiguration(LocaleProperties localeProperties) {
        this.localeProperties = localeProperties;
    }
}

package cn.xmp.moses.locale.utlis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.ConstructorProperties;

@Aspect
public class MessageAspect {
    private static final Logger log = LoggerFactory.getLogger(MessageAspect.class);
    protected LocaleService localeService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void aspectRequestMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void aspectPostMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void aspectGetMapping() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseStatus)")
    public void aspectResponseStatus() {
    }

    @Around("aspectRequestMapping() || aspectPostMapping() || aspectGetMapping() || aspectResponseStatus()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("MessageAspect is not implemented! please make sure locale.aop-class is configured in application.yml");
        return null;
    }

    @ConstructorProperties({"localeService"})
    public MessageAspect(LocaleService localeService) {
        this.localeService = localeService;
    }
}

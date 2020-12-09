package cn.xmp.modules.common.locale.aop;

import cn.hutool.core.util.StrUtil;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.locale.utlis.LocaleService;
import cn.xmp.modules.common.locale.utlis.MessageAspect;
import cn.xmp.modules.common.model.response.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;

@Aspect
public class MessageAspectImpl extends MessageAspect {
    private static final Logger log = LoggerFactory.getLogger(MessageAspectImpl.class);
    @Value("${app.verbose-message}")
    Boolean isVerboseMessage;
    @Autowired
    BuildProperties buildProperties;
    @Autowired
    private Environment environment;

    public MessageAspectImpl(LocaleService localeService) {
        super(localeService);
    }

    @Around("aspectRequestMapping() || aspectPostMapping() || aspectGetMapping() || aspectResponseStatus()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = joinPoint.proceed();
        if (object instanceof Response) {
            Response response = (Response)object;
            String message = this.convertMessage(response.getMessage());
            if (StrUtil.isNotEmpty(message)) {
                String[] profiles = this.environment.getActiveProfiles();
                String currentProfile = profiles[profiles.length - 1];
                String artifact = "";
                if (this.isVerboseMessage && !response.getReturnCode().equals(ReturnCodeEnum.CODE_1000.getCode()) && ("dev".equals(currentProfile) || "local".equals(currentProfile) || "test".equals(currentProfile))) {
                    artifact = "[" + currentProfile + "][" + this.buildProperties.getArtifact() + "][" + this.buildProperties.getVersion() + "] ";
                }

                response.setMessage(artifact + message);
            }

            object = response;
        }

        return object;
    }

    private String convertMessage(String messageCode) {
        if (StrUtil.isNotEmpty(messageCode)) {
            String[] codeArray = StrUtil.split(messageCode, " ");
            if (codeArray != null && codeArray.length > 0) {
                String[] var3 = codeArray;
                int var4 = codeArray.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String s = var3[var5];
                    String str = this.localeService.getMessage(s, (Object[])null, (String)null);
                    if (StrUtil.isNotEmpty(str)) {
                        messageCode = messageCode.replace(s, str);
                    }
                }

                return messageCode;
            } else {
                return this.localeService.getMessage(messageCode, (Object[])null, (String)null);
            }
        } else {
            return null;
        }
    }
}

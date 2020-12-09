package cn.xmp.modules.common.locale.utlis;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LocaleService {
    private MessageSource messageSource;

    public LocaleService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code) {
        return this.getMessage(code, (Object[])null);
    }

    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(code, args, defaultMessage, locale);
    }
}

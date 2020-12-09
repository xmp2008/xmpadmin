package cn.xmp.modules.common.locale.autoconfigure;

import cn.xmp.modules.common.locale.utlis.MessageAspect;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
    prefix = "locale"
)
public class LocaleProperties {
    private Boolean enabled = true;
    private String cookieName = "NG_TRANSLATE_LANG_KEY";
    private String paramName = "language";
    private String[] basenames = null;
    private String defaultEncoding = "UTF-8";
    private Integer cacheSeconds = 3600;
    private Class<? extends MessageAspect> aopClass = MessageAspect.class;

    public LocaleProperties() {
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public String getCookieName() {
        return this.cookieName;
    }

    public String getParamName() {
        return this.paramName;
    }

    public String[] getBasenames() {
        return this.basenames;
    }

    public String getDefaultEncoding() {
        return this.defaultEncoding;
    }

    public Integer getCacheSeconds() {
        return this.cacheSeconds;
    }

    public Class<? extends MessageAspect> getAopClass() {
        return this.aopClass;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setBasenames(String[] basenames) {
        this.basenames = basenames;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public void setCacheSeconds(Integer cacheSeconds) {
        this.cacheSeconds = cacheSeconds;
    }

    public void setAopClass(Class<? extends MessageAspect> aopClass) {
        this.aopClass = aopClass;
    }
}

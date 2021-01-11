package cn.xmp.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 代码生成配置表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@TableName("t_generator_config")
public class GeneratorConfig extends Model<GeneratorConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 作者
     */
    private String author;
    /**
     * 基础包名
     */
    @TableField("base_package")
    private String basePackage;
    /**
     * entity文件存放路径
     */
    @TableField("entity_package")
    private String entityPackage;
    /**
     * mapper文件存放路径
     */
    @TableField("mapper_package")
    private String mapperPackage;
    /**
     * mapper xml文件存放路径
     */
    @TableField("mapper_xml_package")
    private String mapperXmlPackage;
    /**
     * servcie文件存放路径
     */
    @TableField("service_package")
    private String servicePackage;
    /**
     * serviceImpl文件存放路径
     */
    @TableField("service_impl_package")
    private String serviceImplPackage;
    /**
     * controller文件存放路径
     */
    @TableField("controller_package")
    private String controllerPackage;
    /**
     * 是否去除前缀 1是 0否
     */
    @TableField("is_trim")
    private String isTrim;
    /**
     * 前缀内容
     */
    @TableField("trim_value")
    private String trimValue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getMapperXmlPackage() {
        return mapperXmlPackage;
    }

    public void setMapperXmlPackage(String mapperXmlPackage) {
        this.mapperXmlPackage = mapperXmlPackage;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getIsTrim() {
        return isTrim;
    }

    public void setIsTrim(String isTrim) {
        this.isTrim = isTrim;
    }

    public String getTrimValue() {
        return trimValue;
    }

    public void setTrimValue(String trimValue) {
        this.trimValue = trimValue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GeneratorConfig{" +
        "id=" + id +
        ", author=" + author +
        ", basePackage=" + basePackage +
        ", entityPackage=" + entityPackage +
        ", mapperPackage=" + mapperPackage +
        ", mapperXmlPackage=" + mapperXmlPackage +
        ", servicePackage=" + servicePackage +
        ", serviceImplPackage=" + serviceImplPackage +
        ", controllerPackage=" + controllerPackage +
        ", isTrim=" + isTrim +
        ", trimValue=" + trimValue +
        "}";
    }
}

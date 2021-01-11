package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 代码生成配置表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class GeneratorConfigRequest  {
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
	private String basePackage;
    /**
     * entity文件存放路径
     */
	private String entityPackage;
    /**
     * mapper文件存放路径
     */
	private String mapperPackage;
    /**
     * mapper xml文件存放路径
     */
	private String mapperXmlPackage;
    /**
     * servcie文件存放路径
     */
	private String servicePackage;
    /**
     * serviceImpl文件存放路径
     */
	private String serviceImplPackage;
    /**
     * controller文件存放路径
     */
	private String controllerPackage;
    /**
     * 是否去除前缀 1是 0否
     */
	private String isTrim;
    /**
     * 前缀内容
     */
	private String trimValue;


}

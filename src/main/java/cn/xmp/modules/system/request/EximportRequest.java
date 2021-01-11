package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * Excel导入导出测试
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class EximportRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 字段1
     */
	private String field1;
    /**
     * 字段2
     */
	private Integer field2;
    /**
     * 字段3
     */
	private String field3;
	private Date createTime;


}

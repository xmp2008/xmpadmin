package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 用户权限测试
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class DataPermissionTestRequest  {
    private static final long serialVersionUID = 1L;

	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private Integer deptId;
	private Date createTime;
	private Integer id;


}

package cn.xmp.modules.system.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */

@Getter
@Setter
@ToString(callSuper = true)
public class SysRoleRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Long roleId;
    /**
     * 名称
     */
	private String name;
    /**
     * 角色级别
     */
	private Integer level;
    /**
     * 描述
     */
	private String description;
    /**
     * 数据权限
     */
	private String dataScope;
    /**
     * 创建者
     */
	private String createBy;
    /**
     * 更新者
     */
	private String updateBy;
    /**
     * 创建日期
     */
	private Date createTime;
    /**
     * 更新时间
     */
	private Date updateTime;


}

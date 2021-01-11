package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class DeptRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
	private Long deptId;
    /**
     * 上级部门ID
     */
	private Long parentId;
    /**
     * 部门名称
     */
	private String deptName;
    /**
     * 排序
     */
	private Long orderNum;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date modifyTime;


}

package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class MenuRequest  {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单/按钮ID
     */
	private Long menuId;
    /**
     * 上级菜单ID
     */
	private Long parentId;
    /**
     * 菜单/按钮名称
     */
	private String menuName;
    /**
     * 菜单URL
     */
	private String url;
    /**
     * 权限标识
     */
	private String perms;
    /**
     * 图标
     */
	private String icon;
    /**
     * 类型 0菜单 1按钮
     */
	private String type;
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

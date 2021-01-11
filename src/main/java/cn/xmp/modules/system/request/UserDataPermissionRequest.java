package cn.xmp.modules.system.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户数据权限关联表
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */

@Getter
@Setter
@ToString(callSuper = true)
public class UserDataPermissionRequest  {
    private static final long serialVersionUID = 1L;

	private Long userId;
	private Long deptId;


}

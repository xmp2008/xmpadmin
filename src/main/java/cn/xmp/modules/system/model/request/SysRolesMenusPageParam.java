package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * 角色菜单关联 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Data
public class SysRolesMenusPageParam {
    private Integer pageNumber;
    private Integer pageSize;
}

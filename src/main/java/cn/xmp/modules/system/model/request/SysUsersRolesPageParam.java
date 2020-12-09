package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * 用户角色关联 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Data
public class SysUsersRolesPageParam {
    private Integer pageNumber;
    private Integer pageSize;
}

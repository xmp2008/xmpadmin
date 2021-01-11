package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * 用户角色关联表 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
public class UserRolePageParam {
    private Integer pageNumber;
    private Integer pageSize;
}

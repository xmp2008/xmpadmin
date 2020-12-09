package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * 角色表 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Data
public class SysRolePageParam {
    private Integer pageNumber;
    private Integer pageSize;
}

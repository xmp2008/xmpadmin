package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * 登录日志表 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
public class LoginLogPageParam {
    private Integer pageNumber;
    private Integer pageSize;
}

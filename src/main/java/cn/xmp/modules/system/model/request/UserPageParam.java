package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * 用户表 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
public class UserPageParam {
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 状态：1启用、0禁用
     */
    private Long enabled;
    /**
     * 用户名
     */
    private String username;
    private Integer pageNumber;
    private Integer pageSize;
}

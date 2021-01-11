package cn.xmp.modules.system.model.request;
import lombok.Data;
/**
 * <p>
 * Excel导入导出测试 分页查询请求包装类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Data
public class EximportPageParam {
    private Integer pageNumber;
    private Integer pageSize;
}

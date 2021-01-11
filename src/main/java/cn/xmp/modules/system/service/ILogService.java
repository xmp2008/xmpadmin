package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.Log;
import cn.xmp.modules.system.model.request.LogPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface ILogService extends IService<Log> {
    public BaseResponse add(Log model);

    public BaseResponse delete(Log model);

    public BaseResponse update(Log model);

    public BaseResponse query(Log model);

    public PageResponse page(LogPageParam pageParam);
}

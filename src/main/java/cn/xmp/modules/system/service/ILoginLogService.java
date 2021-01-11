package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.LoginLog;
import cn.xmp.modules.system.model.request.LoginLogPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface ILoginLogService extends IService<LoginLog> {
    public BaseResponse add(LoginLog model);

    public BaseResponse delete(LoginLog model);

    public BaseResponse update(LoginLog model);

    public BaseResponse query(LoginLog model);

    public PageResponse page(LoginLogPageParam pageParam);
}

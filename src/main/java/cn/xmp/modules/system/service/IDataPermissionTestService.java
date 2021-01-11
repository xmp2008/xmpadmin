package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.DataPermissionTest;
import cn.xmp.modules.system.model.request.DataPermissionTestPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 用户权限测试 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IDataPermissionTestService extends IService<DataPermissionTest> {
    public BaseResponse add(DataPermissionTest model);

    public BaseResponse delete(DataPermissionTest model);

    public BaseResponse update(DataPermissionTest model);

    public BaseResponse query(DataPermissionTest model);

    public PageResponse page(DataPermissionTestPageParam pageParam);
}

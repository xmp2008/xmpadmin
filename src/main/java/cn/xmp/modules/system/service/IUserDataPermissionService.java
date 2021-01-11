package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.UserDataPermission;
import cn.xmp.modules.system.model.request.UserDataPermissionPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 用户数据权限关联表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IUserDataPermissionService extends IService<UserDataPermission> {
    public BaseResponse add(UserDataPermission model);

    public BaseResponse delete(UserDataPermission model);

    public BaseResponse update(UserDataPermission model);

    public BaseResponse query(UserDataPermission model);

    public PageResponse page(UserDataPermissionPageParam pageParam);
}

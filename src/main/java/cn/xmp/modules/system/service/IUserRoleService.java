package cn.xmp.modules.system.service;

import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.UserRole;
import cn.xmp.modules.system.model.request.UserRolePageParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IUserRoleService extends IService<UserRole> {
    public BaseResponse add(UserRole model);

    public BaseResponse delete(UserRole model);

    public BaseResponse update(UserRole model);

    public BaseResponse query(UserRole model);

    public PageResponse page(UserRolePageParam pageParam);

}

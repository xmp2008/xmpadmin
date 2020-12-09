package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.SysUsersRoles;
import cn.xmp.modules.system.model.request.SysUsersRolesPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface ISysUsersRolesService extends IService<SysUsersRoles> {
    public BaseResponse add(SysUsersRoles model);

    public BaseResponse delete(SysUsersRoles model);

    public BaseResponse update(SysUsersRoles model);

    public BaseResponse query(SysUsersRoles model);

    public PageResponse page(SysUsersRolesPageParam pageParam);
}

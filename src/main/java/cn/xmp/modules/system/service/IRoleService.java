package cn.xmp.modules.system.service;

import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.Role;
import cn.xmp.modules.system.model.request.RolePageParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IRoleService extends IService<Role> {
    public BaseResponse add(Role model);

    public BaseResponse delete(Role model);

    public BaseResponse update(Role model);

    public BaseResponse query(Role model);

    public PageResponse page(RolePageParam pageParam);
    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<Role> findUserRole(String username);
}

package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.SysRole;
import cn.xmp.modules.system.model.request.SysRolePageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface ISysRoleService extends IService<SysRole> {
    public BaseResponse add(SysRole model);

    public BaseResponse delete(SysRole model);

    public BaseResponse update(SysRole model);

    public BaseResponse query(SysRole model);

    public PageResponse page(SysRolePageParam pageParam);

    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<SysRole> findUserRole(String username);
}

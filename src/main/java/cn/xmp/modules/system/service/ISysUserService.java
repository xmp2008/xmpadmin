package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.SysUser;
import cn.xmp.modules.system.model.request.SysUserPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface ISysUserService extends IService<SysUser> {
    public BaseResponse add(SysUser model);

    public BaseResponse delete(SysUser model);

    public BaseResponse update(SysUser model);

    public BaseResponse query(SysUser model);

    public PageResponse page(SysUserPageParam pageParam);

    public BaseResponse queryByName(SysUser model);
    /**
     * 获取用户角色和权限集
     *
     * @param user 用户
     */
    void doGetUserAuthorizationInfo(SysUser user);
}

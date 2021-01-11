package cn.xmp.modules.system.service;

import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.User;
import cn.xmp.modules.system.model.request.UserPageParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IUserService extends IService<User> {
    public BaseResponse add(User model);

    public BaseResponse delete(User model);

    public BaseResponse update(User model);

    public BaseResponse query(User model);

    public PageResponse page(UserPageParam pageParam);

    public BaseResponse queryByName(User model);
    /**
     * 获取用户角色和权限集
     *
     * @param user 用户
     */
    void doGetUserAuthorizationInfo(User user);
}

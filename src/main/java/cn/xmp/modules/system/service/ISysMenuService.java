package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.SysMenu;
import cn.xmp.modules.system.model.request.SysMenuPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface ISysMenuService extends IService<SysMenu> {
    public BaseResponse add(SysMenu model);

    public BaseResponse delete(SysMenu model);

    public BaseResponse update(SysMenu model);

    public BaseResponse query(SysMenu model);

    public PageResponse page(SysMenuPageParam pageParam);

    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<SysMenu> findUserPermissions(String username);
}

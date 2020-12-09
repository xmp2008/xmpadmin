package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.SysRolesMenus;
import cn.xmp.modules.system.model.request.SysRolesMenusPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色菜单关联 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface ISysRolesMenusService extends IService<SysRolesMenus> {
    public BaseResponse add(SysRolesMenus model);

    public BaseResponse delete(SysRolesMenus model);

    public BaseResponse update(SysRolesMenus model);

    public BaseResponse query(SysRolesMenus model);

    public PageResponse page(SysRolesMenusPageParam pageParam);
}

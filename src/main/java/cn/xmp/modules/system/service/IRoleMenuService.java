package cn.xmp.modules.system.service;

import cn.xmp.modules.system.entity.RoleMenu;
import cn.xmp.modules.system.model.request.RoleMenuPageParam;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    public BaseResponse add(RoleMenu model);

    public BaseResponse delete(RoleMenu model);

    public BaseResponse update(RoleMenu model);

    public BaseResponse query(RoleMenu model);

    public PageResponse page(RoleMenuPageParam pageParam);
}

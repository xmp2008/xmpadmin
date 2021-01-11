package cn.xmp.modules.system.service;

import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.Menu;
import cn.xmp.modules.system.entity.menu.MenuTree;
import cn.xmp.modules.system.model.request.MenuPageParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
public interface IMenuService extends IService<Menu> {
    public BaseResponse add(Menu model);

    public BaseResponse delete(Menu model);

    public BaseResponse update(Menu model);

    public BaseResponse query(Menu model);

    public PageResponse page(MenuPageParam pageParam);

    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 获取前端所需菜单
     *
     * @return
     */
    public BaseResponse build();

    /**
     * 查找所有的菜单/按钮 （树形结构）
     *
     * @param menu menu
     * @return MenuTree<Menu>
     */
    BaseResponse<MenuTree<Menu>> findMenus(Menu menu);
}

package cn.xmp.modules.system.mapper;

import cn.xmp.modules.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<SysMenu> findUserPermissions(String username);
}

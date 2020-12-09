package cn.xmp.modules.system.mapper;

import cn.xmp.modules.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<SysRole> findUserRole(String username);
}

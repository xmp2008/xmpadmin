package cn.xmp.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.common.utils.StringUtil;
import cn.xmp.modules.system.entity.*;
import cn.xmp.modules.system.mapper.UserMapper;
import cn.xmp.modules.system.model.request.UserPageParam;
import cn.xmp.modules.system.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    public UserServiceImpl() {
        super();
    }

    public UserServiceImpl(UserMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserDataPermissionService userDataPermissionService;

    /**
     * 添加方法
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse add(User user) {
        BaseResponse baseResponse = null;
        try {
            user.setCreateTime(new Date());
//            user.setStatus(User.STATUS_VALID);
            user.setAvatar(User.DEFAULT_AVATAR);
            user.setTheme(User.THEME_BLACK);
            user.setIsTab(User.TAB_OPEN);
            user.setPassword(DigestUtil.md5Hex(User.DEFAULT_PASSWORD));
//        save(user);
            boolean back = this.save(user);
            baseResponse = ResponseConvert.convert(back);

            // 保存用户角色
            String[] roles = user.getRoleIds().split(StringPool.COMMA);
            setUserRoles(user, roles);
            // 保存用户数据权限关联关系
            String[] deptIds = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getDeptIds(), StringPool.COMMA);
            if (ArrayUtils.isNotEmpty(deptIds)) {
                setUserDataPermissions(user, deptIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存用户失败:{}", e);
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1005.getCode());
        }

        return baseResponse;
    }

    private void setUserDataPermissions(User user, String[] deptIds) {
        List<UserDataPermission> userDataPermissions = new ArrayList<>();
        Arrays.stream(deptIds).forEach(deptId -> {
            UserDataPermission permission = new UserDataPermission();
            permission.setDeptId(Long.valueOf(deptId));
            permission.setUserId(user.getUserId());
            userDataPermissions.add(permission);
        });
        userDataPermissionService.saveBatch(userDataPermissions);
    }

    private void setUserRoles(User user, String[] roles) {
        List<UserRole> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(Long.valueOf(roleId));
            userRoles.add(userRole);
        });
        userRoleService.saveBatch(userRoles);
    }

    /**
     * 删除
     *
     * @return
     */
    @Override
    public BaseResponse delete(User model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getUserId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.removeById(model.getUserId());
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    /**
     * 修改方法
     *
     * @return
     */
    @Override
    public BaseResponse update(User model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getUserId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            boolean back = this.updateById(model);
            baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
    }

    /**
     * 单个查询
     *
     * @return
     */
    @Override
    public BaseResponse query(User model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getUserId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            User data = this.getById(model.getUserId());
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        return baseResponse;
    }

    /**
     * 条件查询分页列表
     *
     * @param pageParam
     * @return
     */
    @Override
    public PageResponse page(UserPageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<User> page = new Page<User>();

        Page<User> pageResponse = null;
        //封装条件
        QueryWrapper<User> ew = new QueryWrapper<User>();
        if (StringUtil.isNotEmpty(pageParam.getUsername())) {
            ew.eq("username", pageParam.getUsername());
        }
        if (StringUtil.isNotEmpty(pageParam.getMobile())) {
            ew.eq("mobile", pageParam.getMobile());
        }
        if (StringUtil.isNotEmpty(pageParam.getEnabled())) {
            ew.eq("status", pageParam.getEnabled());
        }
        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
            page.setCurrent(pageParam.getPageNumber());
            page.setSize(pageParam.getPageSize());
            pageResponse = this.page(page, ew);
        } else {
            List<User> selectList = this.list(ew);
            pageResponse = new Page<>();
            pageResponse.setRecords(selectList);
        }
        //记录数
        pageResponse.setTotal(this.count(ew));
        if (CollUtil.isEmpty(pageResponse.getRecords())) {
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1002.getCode());
        }
        BeanUtils.copyProperties(pageResponse, response);
        response.setTotal((int) pageResponse.getTotal());
//        log.info("response:{}", response);
        return response;
    }

    /**
     * 根据条件查询用户
     *
     * @return
     */
    @Override
    public BaseResponse queryByName(User model) {
        BaseResponse baseResponse;
        if (null == model) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", model.getUsername());
            User data = this.getOne(wrapper);
            if (null != data) {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
                baseResponse.setDataInfo(data);
            } else {
                baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            }
        }
        return baseResponse;
    }


    @Override
    public void doGetUserAuthorizationInfo(User user) {
        // 获取用户角色集
        List<Role> roleList = this.roleService.findUserRole(user.getUsername());
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        user.setRoles(roleSet);

        // 获取用户权限集
        List<Menu> permissionList = this.menuService.findUserPermissions(user.getUsername());
        Set<String> permissionSet = permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
        user.setStringPermissions(permissionSet);

//        String deptIds = this.userDataPermissionService.findByUserId(String.valueOf(user.getUserId()));
//        user.setDeptIds(deptIds);
    }
}

package cn.xmp.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.xmp.modules.system.entity.SysMenu;
import cn.xmp.modules.system.entity.SysRole;
import cn.xmp.modules.system.entity.SysUser;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.system.mapper.SysUserMapper;
import cn.xmp.modules.system.model.request.SysUserPageParam;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.system.service.ISysMenuService;
import cn.xmp.modules.system.service.ISysRoleService;
import cn.xmp.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Service
//@AllArgsConstructor
@RequiredArgsConstructor
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
//    public SysUserServiceImpl() {
//        super();
//    }
//
//    public SysUserServiceImpl(SysUserMapper baseMapper) {
//        this.baseMapper = baseMapper;
//    }
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ISysRoleService roleService;
    /**
     * 添加方法
     *
     * @return
     */
    @Override
    public BaseResponse add(SysUser model) {
        boolean back = this.save(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
    }

    /**
     * 删除
     *
     * @return
     */
    @Override
    public BaseResponse delete(SysUser model) {
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
    public BaseResponse update(SysUser model) {
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
     * 根据条件查询用户
     *
     * @return
     */
    @Override
    public BaseResponse queryByName(SysUser model) {
        BaseResponse baseResponse;
        if (null == model) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
            wrapper.eq("username", model.getUsername());
            SysUser data = this.getOne(wrapper);
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
     * 单个查询
     *
     * @return
     */
    @Override
    public BaseResponse query(SysUser model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getUserId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            SysUser data = this.getById(model.getUserId());
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
    public PageResponse page(SysUserPageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<SysUser> page = new Page<SysUser>();

        Page<SysUser> pageResponse = null;
        //封装条件
        QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
            page.setCurrent(pageParam.getPageNumber());
            page.setSize(pageParam.getPageSize());
            pageResponse = this.page(page, ew);
        } else {
            List<SysUser> selectList = this.list(ew);
            pageResponse = new Page<>();
            pageResponse.setRecords(selectList);
        }
        //记录数
        pageResponse.setTotal(this.count(ew));
        if (CollUtil.isEmpty(pageResponse.getRecords())) {
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1002.getCode());
        }
        BeanUtils.copyProperties(pageResponse, response);

        return response;
    }


    @Override
    public void doGetUserAuthorizationInfo(SysUser user) {
        // 获取用户角色集
        List<SysRole> roleList = this.roleService.findUserRole(user.getUsername());
        Set<String> roleSet = roleList.stream().map(SysRole::getName).collect(Collectors.toSet());
        user.setRoles(roleSet);

        // 获取用户权限集
        List<SysMenu> permissionList = this.menuService.findUserPermissions(user.getUsername());
        Set<String> permissionSet = permissionList.stream().map(SysMenu::getPermission).collect(Collectors.toSet());
        user.setStringPermissions(permissionSet);

//        String deptIds = this.userDataPermissionService.findByUserId(String.valueOf(user.getUserId()));
//        user.setDeptIds(deptIds);
    }
}
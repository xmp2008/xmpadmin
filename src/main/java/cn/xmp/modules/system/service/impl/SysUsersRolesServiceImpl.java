package cn.xmp.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.xmp.modules.system.entity.SysUsersRoles;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.system.mapper.SysUsersRolesMapper;
import cn.xmp.modules.system.model.request.SysUsersRolesPageParam;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.system.service.ISysUsersRolesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Service
public class SysUsersRolesServiceImpl extends ServiceImpl<SysUsersRolesMapper, SysUsersRoles> implements ISysUsersRolesService {
    public SysUsersRolesServiceImpl() {
        super();
     }

    public SysUsersRolesServiceImpl(SysUsersRolesMapper baseMapper) {
        this.baseMapper = baseMapper;
     }
    /**
    * 添加方法
    * @return
    */
    @Override
    public BaseResponse add(SysUsersRoles model) {
        boolean back = this.save(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
        }
    /**
    * 删除
    * @return
    */
    @Override
    public BaseResponse delete(SysUsersRoles model) {
        BaseResponse baseResponse ;
        if (null == model || null == model.getRoleId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        boolean back = this.removeById(model.getRoleId());
        baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
        }
    /**
    * 修改方法
    * @return
    */
    @Override
    public BaseResponse update(SysUsersRoles model) {
        BaseResponse baseResponse ;
        if (null == model || null == model.getRoleId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        boolean back = this.updateById(model);
        baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
        }
    /**
    * 单个查询
    * @return
    */
    @Override
    public BaseResponse query(SysUsersRoles model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getRoleId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        SysUsersRoles data = this.getById(model.getRoleId());
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
    * @param pageParam
    * @return
    */
    @Override
    public PageResponse page(SysUsersRolesPageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<SysUsersRoles> page = new Page<SysUsersRoles>();

        Page<SysUsersRoles> pageResponse = null;
        //封装条件
        QueryWrapper<SysUsersRoles> ew = new QueryWrapper<SysUsersRoles>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
        page.setCurrent(pageParam.getPageNumber());
        page.setSize(pageParam.getPageSize());
        pageResponse = this.page(page, ew);
        } else {
        List<SysUsersRoles> selectList = this.list(ew);
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
}
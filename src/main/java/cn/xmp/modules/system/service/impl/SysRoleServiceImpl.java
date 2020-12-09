package cn.xmp.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.xmp.modules.system.entity.SysRole;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.system.mapper.SysRoleMapper;
import cn.xmp.modules.system.model.request.SysRolePageParam;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.system.service.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
//    public SysRoleServiceImpl() {
//        super();
//     }
//
//    public SysRoleServiceImpl(SysRoleMapper baseMapper) {
//        this.baseMapper = baseMapper;
//     }
    /**
    * 添加方法
    * @return
    */
    @Override
    public BaseResponse add(SysRole model) {
        boolean back = this.save(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
        }
    /**
    * 删除
    * @return
    */
    @Override
    public BaseResponse delete(SysRole model) {
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
    public BaseResponse update(SysRole model) {
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
    public BaseResponse query(SysRole model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getRoleId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        SysRole data = this.getById(model.getRoleId());
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
    public PageResponse page(SysRolePageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<SysRole> page = new Page<SysRole>();

        Page<SysRole> pageResponse = null;
        //封装条件
        QueryWrapper<SysRole> ew = new QueryWrapper<SysRole>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
        page.setCurrent(pageParam.getPageNumber());
        page.setSize(pageParam.getPageSize());
        pageResponse = this.page(page, ew);
        } else {
        List<SysRole> selectList = this.list(ew);
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
    public List<SysRole> findUserRole(String username) {
        return this.baseMapper.findUserRole(username);
    }
}
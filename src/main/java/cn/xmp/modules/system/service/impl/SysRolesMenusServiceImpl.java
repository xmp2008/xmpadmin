package cn.xmp.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.xmp.modules.system.entity.SysRolesMenus;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.system.mapper.SysRolesMenusMapper;
import cn.xmp.modules.system.model.request.SysRolesMenusPageParam;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.system.service.ISysRolesMenusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>
 * 角色菜单关联 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Service
public class SysRolesMenusServiceImpl extends ServiceImpl<SysRolesMenusMapper, SysRolesMenus> implements ISysRolesMenusService {
    public SysRolesMenusServiceImpl() {
        super();
     }

    public SysRolesMenusServiceImpl(SysRolesMenusMapper baseMapper) {
        this.baseMapper = baseMapper;
     }
    /**
    * 添加方法
    * @return
    */
    @Override
    public BaseResponse add(SysRolesMenus model) {
        boolean back = this.save(model);
        BaseResponse baseResponse = ResponseConvert.convert(back);
        return baseResponse;
        }
    /**
    * 删除
    * @return
    */
    @Override
    public BaseResponse delete(SysRolesMenus model) {
        BaseResponse baseResponse ;
        if (null == model || null == model.getMenuId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        boolean back = this.removeById(model.getMenuId());
        baseResponse = ResponseConvert.convert(back);
        }
        return baseResponse;
        }
    /**
    * 修改方法
    * @return
    */
    @Override
    public BaseResponse update(SysRolesMenus model) {
        BaseResponse baseResponse ;
        if (null == model || null == model.getMenuId()) {
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
    public BaseResponse query(SysRolesMenus model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getMenuId()) {
        baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
        SysRolesMenus data = this.getById(model.getMenuId());
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
    public PageResponse page(SysRolesMenusPageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<SysRolesMenus> page = new Page<SysRolesMenus>();

        Page<SysRolesMenus> pageResponse = null;
        //封装条件
        QueryWrapper<SysRolesMenus> ew = new QueryWrapper<SysRolesMenus>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
        page.setCurrent(pageParam.getPageNumber());
        page.setSize(pageParam.getPageSize());
        pageResponse = this.page(page, ew);
        } else {
        List<SysRolesMenus> selectList = this.list(ew);
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
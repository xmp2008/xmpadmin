package cn.xmp.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.xmp.modules.system.entity.SysMenu;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.system.mapper.SysMenuMapper;
import cn.xmp.modules.system.model.request.SysMenuPageParam;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.system.service.ISysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
//    public SysMenuServiceImpl() {
//        super();
//    }
//
//    public SysMenuServiceImpl(SysMenuMapper baseMapper) {
//        this.baseMapper = baseMapper;
//    }

    /**
     * 添加方法
     *
     * @return
     */
    @Override
    public BaseResponse add(SysMenu model) {
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
    public BaseResponse delete(SysMenu model) {
        BaseResponse baseResponse;
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
     *
     * @return
     */
    @Override
    public BaseResponse update(SysMenu model) {
        BaseResponse baseResponse;
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
     *
     * @return
     */
    @Override
    public BaseResponse query(SysMenu model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getMenuId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            SysMenu data = this.getById(model.getMenuId());
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
    public PageResponse page(SysMenuPageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<SysMenu> page = new Page<SysMenu>();

        Page<SysMenu> pageResponse = null;
        //封装条件
        QueryWrapper<SysMenu> ew = new QueryWrapper<SysMenu>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
            page.setCurrent(pageParam.getPageNumber());
            page.setSize(pageParam.getPageSize());
            pageResponse = this.page(page, ew);
        } else {
            List<SysMenu> selectList = this.list(ew);
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
    public List<SysMenu> findUserPermissions(String username) {
        return this.baseMapper.findUserPermissions(username);
    }
}
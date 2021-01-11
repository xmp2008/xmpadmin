package cn.xmp.modules.system.service.impl;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.model.response.ResponseConvert;
import cn.xmp.modules.system.entity.RoleMenu;
import cn.xmp.modules.system.mapper.RoleMenuMapper;
import cn.xmp.modules.system.model.request.RoleMenuPageParam;
import cn.xmp.modules.system.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    public RoleMenuServiceImpl() {
        super();
    }

    public RoleMenuServiceImpl(RoleMenuMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 添加方法
     *
     * @return
     */
    @Override
    public BaseResponse add(RoleMenu model) {
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
    public BaseResponse delete(RoleMenu model) {
        BaseResponse baseResponse;
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
     *
     * @return
     */
    @Override
    public BaseResponse update(RoleMenu model) {
        BaseResponse baseResponse;
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
     *
     * @return
     */
    @Override
    public BaseResponse query(RoleMenu model) {
        BaseResponse baseResponse;
        if (null == model || null == model.getRoleId()) {
            baseResponse = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        } else {
            RoleMenu data = this.getById(model.getRoleId());
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
    public PageResponse page(RoleMenuPageParam pageParam) {
        PageResponse response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1000.getCode());
        Page<RoleMenu> page = new Page<RoleMenu>();

        Page<RoleMenu> pageResponse = null;
        //封装条件
        QueryWrapper<RoleMenu> ew = new QueryWrapper<RoleMenu>();

        //针对分页判断
        if (null != pageParam && null != pageParam.getPageNumber() && null != pageParam.getPageSize()) {
            page.setCurrent(pageParam.getPageNumber());
            page.setSize(pageParam.getPageSize());
            pageResponse = this.page(page, ew);
        } else {
            List<RoleMenu> selectList = this.list(ew);
            pageResponse = new Page<>();
            pageResponse.setRecords(selectList);
        }
        //记录数
        pageResponse.setTotal(this.count(ew));
        if (CollectionUtils.isEmpty(pageResponse.getRecords())) {
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1002.getCode());
        }
        BeanUtils.copyProperties(pageResponse, response);

        return response;
    }
}

package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.Role;
import cn.xmp.modules.system.model.request.RolePageParam;
import cn.xmp.modules.system.request.RoleRequest;
import cn.xmp.modules.system.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/role")
public class RoleAdmin {


    private IRoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody RoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add Role: {}", request);
        try {
            Role bean = new Role();
            BeanUtils.copyProperties(request, bean);
            response = roleService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody RoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update Role: {}", request);
        try {
            Role bean = new Role();
            BeanUtils.copyProperties(request, bean);
            response = roleService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody RoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query Role : {}", request);
        try {
            Role bean = new Role();
            BeanUtils.copyProperties(request, bean);
            response = roleService.query(bean);
            log.info("query Role back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody RoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete Role : {}", request);
        try {
            Role bean = new Role();
            BeanUtils.copyProperties(request, bean);
            response = roleService.delete(bean);
            log.info("delete Role back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody RolePageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("Role page method request : {}", request);
            response = roleService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse list(@RequestBody RoleRequest request) {
        BaseResponse response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        //业务操作
        log.info("list Role : {}", request);
        try {
            Role bean = new Role();
            BeanUtils.copyProperties(request, bean);
            QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
            if (StringUtils.isNotBlank(bean.getRoleName())) {
                queryWrapper.lambda().like(Role::getRoleName, bean.getRoleName());
            }
            List<Role> list = roleService.list(queryWrapper);
            if (CollectionUtils.isEmpty(list)) {
                response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1002.getCode());
            } else {
                response.setDataInfo(list);
            }

            log.info("list Role back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

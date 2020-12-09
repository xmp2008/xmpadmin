package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.SysRole;
import cn.xmp.modules.system.model.request.SysRolePageParam;
import cn.xmp.modules.system.model.request.SysRoleRequest;
import cn.xmp.modules.system.service.ISysRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/sysRole")
public class SysRoleController  {


    private ISysRoleService sysRoleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody SysRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add SysRole: {}", request);
        try {
        SysRole bean = new SysRole();
        BeanUtils.copyProperties(request, bean);
        response = sysRoleService.add(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody SysRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update SysRole: {}", request);
        try {
        SysRole bean = new SysRole();
        BeanUtils.copyProperties(request, bean);
        response = sysRoleService.update(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody SysRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query SysRole : {}", request);
        try {
        SysRole bean = new SysRole();
        BeanUtils.copyProperties(request, bean);
        response = sysRoleService.query(bean);
        log.info("query SysRole back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;

        }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody SysRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete SysRole : {}", request);
        try {
        SysRole bean = new SysRole();
        BeanUtils.copyProperties(request, bean);
        response = sysRoleService.delete(bean);
        log.info("delete SysRole back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody SysRolePageParam request) {
        PageResponse response;
        try {
        //业务操作
        log.info("SysRole page method request : {}", request);
        response = sysRoleService.page(request);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
    }
    }

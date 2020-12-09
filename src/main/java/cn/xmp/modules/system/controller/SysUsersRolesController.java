package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.SysUsersRoles;
import cn.xmp.modules.system.model.request.SysUsersRolesPageParam;
import cn.xmp.modules.system.model.request.SysUsersRolesRequest;
import cn.xmp.modules.system.service.ISysUsersRolesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户角色关联 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/sysUsersRoles")
public class SysUsersRolesController  {


    private ISysUsersRolesService sysUsersRolesService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody SysUsersRolesRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add SysUsersRoles: {}", request);
        try {
        SysUsersRoles bean = new SysUsersRoles();
        BeanUtils.copyProperties(request, bean);
        response = sysUsersRolesService.add(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody SysUsersRolesRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update SysUsersRoles: {}", request);
        try {
        SysUsersRoles bean = new SysUsersRoles();
        BeanUtils.copyProperties(request, bean);
        response = sysUsersRolesService.update(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody SysUsersRolesRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query SysUsersRoles : {}", request);
        try {
        SysUsersRoles bean = new SysUsersRoles();
        BeanUtils.copyProperties(request, bean);
        response = sysUsersRolesService.query(bean);
        log.info("query SysUsersRoles back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;

        }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody SysUsersRolesRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete SysUsersRoles : {}", request);
        try {
        SysUsersRoles bean = new SysUsersRoles();
        BeanUtils.copyProperties(request, bean);
        response = sysUsersRolesService.delete(bean);
        log.info("delete SysUsersRoles back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody SysUsersRolesPageParam request) {
        PageResponse response;
        try {
        //业务操作
        log.info("SysUsersRoles page method request : {}", request);
        response = sysUsersRolesService.page(request);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
    }
    }

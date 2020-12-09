package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.SysRolesMenus;
import cn.xmp.modules.system.model.request.SysRolesMenusPageParam;
import cn.xmp.modules.system.model.request.SysRolesMenusRequest;
import cn.xmp.modules.system.service.ISysRolesMenusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色菜单关联 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/sysRolesMenus")
public class SysRolesMenusController  {


    private ISysRolesMenusService sysRolesMenusService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody SysRolesMenusRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add SysRolesMenus: {}", request);
        try {
        SysRolesMenus bean = new SysRolesMenus();
        BeanUtils.copyProperties(request, bean);
        response = sysRolesMenusService.add(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody SysRolesMenusRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update SysRolesMenus: {}", request);
        try {
        SysRolesMenus bean = new SysRolesMenus();
        BeanUtils.copyProperties(request, bean);
        response = sysRolesMenusService.update(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody SysRolesMenusRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query SysRolesMenus : {}", request);
        try {
        SysRolesMenus bean = new SysRolesMenus();
        BeanUtils.copyProperties(request, bean);
        response = sysRolesMenusService.query(bean);
        log.info("query SysRolesMenus back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;

        }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody SysRolesMenusRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete SysRolesMenus : {}", request);
        try {
        SysRolesMenus bean = new SysRolesMenus();
        BeanUtils.copyProperties(request, bean);
        response = sysRolesMenusService.delete(bean);
        log.info("delete SysRolesMenus back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody SysRolesMenusPageParam request) {
        PageResponse response;
        try {
        //业务操作
        log.info("SysRolesMenus page method request : {}", request);
        response = sysRolesMenusService.page(request);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
    }
    }

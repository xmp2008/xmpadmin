package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.SysMenu;
import cn.xmp.modules.system.model.request.SysMenuPageParam;
import cn.xmp.modules.system.model.request.SysMenuRequest;
import cn.xmp.modules.system.service.ISysMenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/sysMenu")
public class SysMenuController  {


    private ISysMenuService sysMenuService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody SysMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add SysMenu: {}", request);
        try {
        SysMenu bean = new SysMenu();
        BeanUtils.copyProperties(request, bean);
        response = sysMenuService.add(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody SysMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update SysMenu: {}", request);
        try {
        SysMenu bean = new SysMenu();
        BeanUtils.copyProperties(request, bean);
        response = sysMenuService.update(bean);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody SysMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query SysMenu : {}", request);
        try {
        SysMenu bean = new SysMenu();
        BeanUtils.copyProperties(request, bean);
        response = sysMenuService.query(bean);
        log.info("query SysMenu back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;

        }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody SysMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete SysMenu : {}", request);
        try {
        SysMenu bean = new SysMenu();
        BeanUtils.copyProperties(request, bean);
        response = sysMenuService.delete(bean);
        log.info("delete SysMenu back: {}", response);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
        }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody SysMenuPageParam request) {
        PageResponse response;
        try {
        //业务操作
        log.info("SysMenu page method request : {}", request);
        response = sysMenuService.page(request);
        } catch (Exception e) {
        log.error("error: {}", e);
        response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(e.getMessage());
        }
        return response;
    }
    }

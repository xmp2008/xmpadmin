package cn.xmp.modules.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;;
import org.springframework.beans.BeanUtils;
import cn.xmp.modules.system.entity.RoleMenu;
import cn.xmp.modules.system.request.RoleMenuRequest;
import cn.xmp.modules.system.model.request.RoleMenuPageParam;
import cn.xmp.modules.system.service.IRoleMenuService;

/**
 * <p>
 * 角色菜单关联表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/roleMenu")
public class RoleMenuAdmin {


    private IRoleMenuService roleMenuService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody RoleMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add RoleMenu: {}", request);
        try {
            RoleMenu bean = new RoleMenu();
            BeanUtils.copyProperties(request, bean);
            response = roleMenuService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody RoleMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update RoleMenu: {}", request);
        try {
            RoleMenu bean = new RoleMenu();
            BeanUtils.copyProperties(request, bean);
            response = roleMenuService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody RoleMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query RoleMenu : {}", request);
        try {
            RoleMenu bean = new RoleMenu();
            BeanUtils.copyProperties(request, bean);
            response = roleMenuService.query(bean);
            log.info("query RoleMenu back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody RoleMenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete RoleMenu : {}", request);
        try {
            RoleMenu bean = new RoleMenu();
            BeanUtils.copyProperties(request, bean);
            response = roleMenuService.delete(bean);
            log.info("delete RoleMenu back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody RoleMenuPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("RoleMenu page method request : {}", request);
            response = roleMenuService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

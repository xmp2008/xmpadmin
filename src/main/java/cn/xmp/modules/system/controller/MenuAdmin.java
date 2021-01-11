package cn.xmp.modules.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import org.springframework.beans.BeanUtils;
import cn.xmp.modules.system.entity.Menu;
import cn.xmp.modules.system.request.MenuRequest;
import cn.xmp.modules.system.model.request.MenuPageParam;
import cn.xmp.modules.system.service.IMenuService;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/menu")
public class MenuAdmin {


    private IMenuService menuService;

    @GetMapping(value = "/build")
//    @RequiresPermissions("user:list")
//    @ApiOperation("获取前端所需菜单")
    public BaseResponse buildMenus() {
        BaseResponse response;
        try {
//            Subject subject = SecurityUtils.getSubject();
//            SysUser user = (SysUser) subject.getPrincipal();
////            subject.logout();
//            log.info("当前登录的用户:{}", user);
            response = menuService.build();
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody MenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add Menu: {}", request);
        try {
            Menu bean = new Menu();
            BeanUtils.copyProperties(request, bean);
            response = menuService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody MenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update Menu: {}", request);
        try {
            Menu bean = new Menu();
            BeanUtils.copyProperties(request, bean);
            response = menuService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody MenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query Menu : {}", request);
        try {
            Menu bean = new Menu();
            BeanUtils.copyProperties(request, bean);
            response = menuService.query(bean);
            log.info("query Menu back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody MenuRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete Menu : {}", request);
        try {
            Menu bean = new Menu();
            BeanUtils.copyProperties(request, bean);
            response = menuService.delete(bean);
            log.info("delete Menu back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody MenuPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("Menu page method request : {}", request);
            response = menuService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse tree(@RequestBody Menu request) {
        BaseResponse response;
        try {
            //业务操作
            log.info("Menu page method request : {}", request);
            response = menuService.findMenus(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

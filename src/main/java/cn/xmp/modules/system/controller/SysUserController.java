package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.SysUser;
import cn.xmp.modules.system.model.request.SysUserPageParam;
import cn.xmp.modules.system.model.request.SysUserRequest;
import cn.xmp.modules.system.service.ISysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2020-12-01
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/sysUser")
public class SysUserController {


    private ISysUserService sysUserService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:add")
    public BaseResponse add(@RequestBody SysUserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add SysUser: {}", request);
        try {
            SysUser bean = new SysUser();
            BeanUtils.copyProperties(request, bean);
            response = sysUserService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:update")
    public BaseResponse update(@RequestBody SysUserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update SysUser: {}", request);
        try {
            SysUser bean = new SysUser();
            BeanUtils.copyProperties(request, bean);
            response = sysUserService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:query")
    public BaseResponse query(@RequestBody SysUserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query SysUser : {}", request);
        try {
            SysUser bean = new SysUser();
            BeanUtils.copyProperties(request, bean);
            response = sysUserService.query(bean);
            log.info("query SysUser back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:delete")
    public BaseResponse delete(@RequestBody SysUserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete SysUser : {}", request);
        try {
            SysUser bean = new SysUser();
            BeanUtils.copyProperties(request, bean);
            response = sysUserService.delete(bean);
            log.info("delete SysUser back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:list")
    public PageResponse page(@RequestBody SysUserPageParam request) {
        PageResponse response;
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            System.out.println("session.getId() = " + session.getId());
            //业务操作
            log.info("SysUser page method request : {}", request);
            response = sysUserService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

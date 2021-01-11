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
import cn.xmp.modules.system.entity.UserDataPermission;
import cn.xmp.modules.system.request.UserDataPermissionRequest;
import cn.xmp.modules.system.model.request.UserDataPermissionPageParam;
import cn.xmp.modules.system.service.IUserDataPermissionService;

/**
 * <p>
 * 用户数据权限关联表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/userDataPermission")
public class UserDataPermissionAdmin {


    private IUserDataPermissionService userDataPermissionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody UserDataPermissionRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add UserDataPermission: {}", request);
        try {
            UserDataPermission bean = new UserDataPermission();
            BeanUtils.copyProperties(request, bean);
            response = userDataPermissionService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody UserDataPermissionRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update UserDataPermission: {}", request);
        try {
            UserDataPermission bean = new UserDataPermission();
            BeanUtils.copyProperties(request, bean);
            response = userDataPermissionService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody UserDataPermissionRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query UserDataPermission : {}", request);
        try {
            UserDataPermission bean = new UserDataPermission();
            BeanUtils.copyProperties(request, bean);
            response = userDataPermissionService.query(bean);
            log.info("query UserDataPermission back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody UserDataPermissionRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete UserDataPermission : {}", request);
        try {
            UserDataPermission bean = new UserDataPermission();
            BeanUtils.copyProperties(request, bean);
            response = userDataPermissionService.delete(bean);
            log.info("delete UserDataPermission back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody UserDataPermissionPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("UserDataPermission page method request : {}", request);
            response = userDataPermissionService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

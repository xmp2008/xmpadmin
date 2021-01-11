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
import cn.xmp.modules.system.entity.UserRole;
import cn.xmp.modules.system.request.UserRoleRequest;
import cn.xmp.modules.system.model.request.UserRolePageParam;
import cn.xmp.modules.system.service.IUserRoleService;

/**
 * <p>
 * 用户角色关联表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/userRole")
public class UserRoleAdmin {


    private IUserRoleService userRoleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody UserRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add UserRole: {}", request);
        try {
            UserRole bean = new UserRole();
            BeanUtils.copyProperties(request, bean);
            response = userRoleService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody UserRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update UserRole: {}", request);
        try {
            UserRole bean = new UserRole();
            BeanUtils.copyProperties(request, bean);
            response = userRoleService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody UserRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query UserRole : {}", request);
        try {
            UserRole bean = new UserRole();
            BeanUtils.copyProperties(request, bean);
            response = userRoleService.query(bean);
            log.info("query UserRole back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody UserRoleRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete UserRole : {}", request);
        try {
            UserRole bean = new UserRole();
            BeanUtils.copyProperties(request, bean);
            response = userRoleService.delete(bean);
            log.info("delete UserRole back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody UserRolePageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("UserRole page method request : {}", request);
            response = userRoleService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

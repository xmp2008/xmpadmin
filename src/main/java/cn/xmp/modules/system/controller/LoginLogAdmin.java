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
import cn.xmp.modules.system.entity.LoginLog;
import cn.xmp.modules.system.request.LoginLogRequest;
import cn.xmp.modules.system.model.request.LoginLogPageParam;
import cn.xmp.modules.system.service.ILoginLogService;

/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/loginLog")
public class LoginLogAdmin {


    private ILoginLogService loginLogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody LoginLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add LoginLog: {}", request);
        try {
            LoginLog bean = new LoginLog();
            BeanUtils.copyProperties(request, bean);
            response = loginLogService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody LoginLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update LoginLog: {}", request);
        try {
            LoginLog bean = new LoginLog();
            BeanUtils.copyProperties(request, bean);
            response = loginLogService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody LoginLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query LoginLog : {}", request);
        try {
            LoginLog bean = new LoginLog();
            BeanUtils.copyProperties(request, bean);
            response = loginLogService.query(bean);
            log.info("query LoginLog back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody LoginLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete LoginLog : {}", request);
        try {
            LoginLog bean = new LoginLog();
            BeanUtils.copyProperties(request, bean);
            response = loginLogService.delete(bean);
            log.info("delete LoginLog back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody LoginLogPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("LoginLog page method request : {}", request);
            response = loginLogService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

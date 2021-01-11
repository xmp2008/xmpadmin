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
import cn.xmp.modules.system.entity.Log;
import cn.xmp.modules.system.request.LogRequest;
import cn.xmp.modules.system.model.request.LogPageParam;
import cn.xmp.modules.system.service.ILogService;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/log")
public class LogAdmin {


    private ILogService logService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody LogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add Log: {}", request);
        try {
            Log bean = new Log();
            BeanUtils.copyProperties(request, bean);
            response = logService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody LogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update Log: {}", request);
        try {
            Log bean = new Log();
            BeanUtils.copyProperties(request, bean);
            response = logService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody LogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query Log : {}", request);
        try {
            Log bean = new Log();
            BeanUtils.copyProperties(request, bean);
            response = logService.query(bean);
            log.info("query Log back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody LogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete Log : {}", request);
        try {
            Log bean = new Log();
            BeanUtils.copyProperties(request, bean);
            response = logService.delete(bean);
            log.info("delete Log back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody LogPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("Log page method request : {}", request);
            response = logService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

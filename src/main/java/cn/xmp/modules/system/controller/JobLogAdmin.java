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
import cn.xmp.modules.system.entity.JobLog;
import cn.xmp.modules.system.request.JobLogRequest;
import cn.xmp.modules.system.model.request.JobLogPageParam;
import cn.xmp.modules.system.service.IJobLogService;

/**
 * <p>
 * 调度日志表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/jobLog")
public class JobLogAdmin {


    private IJobLogService jobLogService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody JobLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add JobLog: {}", request);
        try {
            JobLog bean = new JobLog();
            BeanUtils.copyProperties(request, bean);
            response = jobLogService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody JobLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update JobLog: {}", request);
        try {
            JobLog bean = new JobLog();
            BeanUtils.copyProperties(request, bean);
            response = jobLogService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody JobLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query JobLog : {}", request);
        try {
            JobLog bean = new JobLog();
            BeanUtils.copyProperties(request, bean);
            response = jobLogService.query(bean);
            log.info("query JobLog back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody JobLogRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete JobLog : {}", request);
        try {
            JobLog bean = new JobLog();
            BeanUtils.copyProperties(request, bean);
            response = jobLogService.delete(bean);
            log.info("delete JobLog back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody JobLogPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("JobLog page method request : {}", request);
            response = jobLogService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

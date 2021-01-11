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
import cn.xmp.modules.system.entity.Job;
import cn.xmp.modules.system.request.JobRequest;
import cn.xmp.modules.system.model.request.JobPageParam;
import cn.xmp.modules.system.service.IJobService;

/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/job")
public class JobAdmin {


    private IJobService jobService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody JobRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add Job: {}", request);
        try {
            Job bean = new Job();
            BeanUtils.copyProperties(request, bean);
            response = jobService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody JobRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update Job: {}", request);
        try {
            Job bean = new Job();
            BeanUtils.copyProperties(request, bean);
            response = jobService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody JobRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query Job : {}", request);
        try {
            Job bean = new Job();
            BeanUtils.copyProperties(request, bean);
            response = jobService.query(bean);
            log.info("query Job back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody JobRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete Job : {}", request);
        try {
            Job bean = new Job();
            BeanUtils.copyProperties(request, bean);
            response = jobService.delete(bean);
            log.info("delete Job back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody JobPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("Job page method request : {}", request);
            response = jobService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

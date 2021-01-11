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
import cn.xmp.modules.system.entity.Eximport;
import cn.xmp.modules.system.request.EximportRequest;
import cn.xmp.modules.system.model.request.EximportPageParam;
import cn.xmp.modules.system.service.IEximportService;

/**
 * <p>
 * Excel导入导出测试 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/eximport")
public class EximportAdmin {


    private IEximportService eximportService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody EximportRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add Eximport: {}", request);
        try {
            Eximport bean = new Eximport();
            BeanUtils.copyProperties(request, bean);
            response = eximportService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody EximportRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update Eximport: {}", request);
        try {
            Eximport bean = new Eximport();
            BeanUtils.copyProperties(request, bean);
            response = eximportService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody EximportRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query Eximport : {}", request);
        try {
            Eximport bean = new Eximport();
            BeanUtils.copyProperties(request, bean);
            response = eximportService.query(bean);
            log.info("query Eximport back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody EximportRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete Eximport : {}", request);
        try {
            Eximport bean = new Eximport();
            BeanUtils.copyProperties(request, bean);
            response = eximportService.delete(bean);
            log.info("delete Eximport back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody EximportPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("Eximport page method request : {}", request);
            response = eximportService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

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
import cn.xmp.modules.system.entity.GeneratorConfig;
import cn.xmp.modules.system.request.GeneratorConfigRequest;
import cn.xmp.modules.system.model.request.GeneratorConfigPageParam;
import cn.xmp.modules.system.service.IGeneratorConfigService;

/**
 * <p>
 * 代码生成配置表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/generatorConfig")
public class GeneratorConfigAdmin {


    private IGeneratorConfigService generatorConfigService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody GeneratorConfigRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add GeneratorConfig: {}", request);
        try {
            GeneratorConfig bean = new GeneratorConfig();
            BeanUtils.copyProperties(request, bean);
            response = generatorConfigService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody GeneratorConfigRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update GeneratorConfig: {}", request);
        try {
            GeneratorConfig bean = new GeneratorConfig();
            BeanUtils.copyProperties(request, bean);
            response = generatorConfigService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody GeneratorConfigRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query GeneratorConfig : {}", request);
        try {
            GeneratorConfig bean = new GeneratorConfig();
            BeanUtils.copyProperties(request, bean);
            response = generatorConfigService.query(bean);
            log.info("query GeneratorConfig back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody GeneratorConfigRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete GeneratorConfig : {}", request);
        try {
            GeneratorConfig bean = new GeneratorConfig();
            BeanUtils.copyProperties(request, bean);
            response = generatorConfigService.delete(bean);
            log.info("delete GeneratorConfig back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody GeneratorConfigPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("GeneratorConfig page method request : {}", request);
            response = generatorConfigService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

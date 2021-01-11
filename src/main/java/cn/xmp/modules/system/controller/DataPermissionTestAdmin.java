package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.system.entity.DataPermissionTest;
import cn.xmp.modules.system.model.request.DataPermissionTestPageParam;
import cn.xmp.modules.system.request.DataPermissionTestRequest;
import cn.xmp.modules.system.service.IDataPermissionTestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户权限测试 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/dataPermissionTest")
public class DataPermissionTestAdmin {


    private IDataPermissionTestService dataPermissionTestService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody DataPermissionTestRequest request) {
        BaseResponse response;
        //业务操作
        log.info("add DataPermissionTest: {}", request);
        try {
            DataPermissionTest bean = new DataPermissionTest();
            BeanUtils.copyProperties(request, bean);
            response = dataPermissionTestService.add(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody DataPermissionTestRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update DataPermissionTest: {}", request);
        try {
            DataPermissionTest bean = new DataPermissionTest();
            BeanUtils.copyProperties(request, bean);
            response = dataPermissionTestService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody DataPermissionTestRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query DataPermissionTest : {}", request);
        try {
            DataPermissionTest bean = new DataPermissionTest();
            BeanUtils.copyProperties(request, bean);
            response = dataPermissionTestService.query(bean);
            log.info("query DataPermissionTest back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody DataPermissionTestRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete DataPermissionTest : {}", request);
        try {
            DataPermissionTest bean = new DataPermissionTest();
            BeanUtils.copyProperties(request, bean);
            response = dataPermissionTestService.delete(bean);
            log.info("delete DataPermissionTest back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody DataPermissionTestPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("DataPermissionTest page method request : {}", request);
            response = dataPermissionTestService.page(request);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

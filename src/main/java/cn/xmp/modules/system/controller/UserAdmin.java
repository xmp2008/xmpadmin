package cn.xmp.modules.system.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.model.response.PageResponse;
import cn.xmp.modules.common.validator.AttributeValidatorException;
import cn.xmp.modules.common.validator.ValidatorConditionType;
import cn.xmp.modules.system.entity.User;
import cn.xmp.modules.system.model.request.UserPageParam;
import cn.xmp.modules.system.request.UserRequest;
import cn.xmp.modules.system.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xiemopeng
 * @since 2021-01-07
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/admin/user")
public class UserAdmin {


    private IUserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestBody UserRequest request) {
        BaseResponse response = new BaseResponse();
        //业务操作
        log.info("add User: {}", request);
        try {
            request.validate(ValidatorConditionType.CREATE);
            User bean = new User();
            BeanUtils.copyProperties(request, bean);
            response = userService.add(bean);
        } catch (AttributeValidatorException e) {
            log.error("新增用户API异常: {}", e);
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestBody UserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("update User: {}", request);
        try {
            User bean = new User();
            BeanUtils.copyProperties(request, bean);
            response = userService.update(bean);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse query(@RequestBody UserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query User : {}", request);
        try {
            User bean = new User();
            BeanUtils.copyProperties(request, bean);
            response = userService.query(bean);
            log.info("query User back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse queryDetail(@RequestBody UserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("query User : {}", request);
        try {
            User bean = new User();
            BeanUtils.copyProperties(request, bean);
            response = userService.findUserDetailList(bean);
            User dataInfo = (User) response.getDataInfo();
            dataInfo.setPassword("");
            response.setDataInfo(dataInfo);
            log.info("query User back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse delete(@RequestBody UserRequest request) {
        BaseResponse response;
        //业务操作
        log.info("delete User : {}", request);
        try {
            User bean = new User();
            BeanUtils.copyProperties(request, bean);
            response = userService.delete(bean);
            log.info("delete User back: {}", response);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse page(@RequestBody UserPageParam request) {
        PageResponse response;
        try {
            //业务操作
            log.info("User page method request : {}", request);
            response = userService.page(request);
            List<User> records = response.getRecords();
            records.stream().map(o -> {
                if ("0".equals(o.getSsex())) {
                    o.setSsex("男");
                } else if ("1".equals(o.getSsex())) {
                    o.setSsex("女");
                } else {
                    o.setSsex("保密");
                }
                o.setPassword("");
                return o;
            }).collect(Collectors.toList());
            response.setRecords(records);
        } catch (Exception e) {
            log.error("error: {}", e);
            response = BackResponseUtil.getPageResponse(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

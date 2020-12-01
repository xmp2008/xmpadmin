package cn.xmp.moses.controller;

import cn.xmp.moses.entity.Person;
import cn.xmp.moses.model.response.BaseResponse;
import cn.xmp.moses.validator.ValidationGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页
 * </p>
 *
 * @package: com.xkcoding.admin.client.controller
 * @description: 首页
 * @author: yangkai.shen
 * @date: Created in 2018/10/8 2:15 PM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/demo")
public class PersonController {
    @GetMapping(value = {"", "/"})
    public String index() {
        return "This is a Spring Boot Admin Client.";
    }


    @PostMapping("/person")
    public BaseResponse getPerson(@Validated(ValidationGroup.Create.class) @RequestBody Person person) {
        BaseResponse response = new BaseResponse();
        response.setMessage("成功");
        response.setReturnCode(1000);
        response.setDataInfo(person);
        return response;
//        return BaseResponse.builder().message(ReturnCodeEnum.CODE_1000.getValue())
//                .returnCode(ReturnCodeEnum.CODE_1000.getCode())
//                .dataInfo(person)
//                .build();
    }

}

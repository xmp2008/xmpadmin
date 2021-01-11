/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.xmp.modules.security.controller;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.common.utils.JsonUtil;
import cn.xmp.modules.common.validator.AttributeValidatorException;
import cn.xmp.modules.security.entity.AuthUserDto;
import cn.xmp.modules.security.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiemopeng
 * @date 2018-11-23
 * 登录、授权
 */
//@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    @Autowired
    AuthService authService;


    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping(value = "/code")
    public BaseResponse getCode() {
        BaseResponse response = authService.getCode();
        log.info("获取验证码API返回信息: {}", JsonUtil.objectToJson(response));
        return response;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse login(@RequestBody AuthUserDto authUser) {
        BaseResponse response = new BaseResponse();
        try {
            response = authService.login(authUser);
        } catch (AttributeValidatorException e) {
            log.error("登录API异常: {}", e);
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage(e.getMessage());
        } catch (UnknownAccountException e) {//用户不存在
            log.error("登录API异常: {}", e);
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("用户不存在");
        } catch (IncorrectCredentialsException e) {//密码错误
            log.error("登录API异常: {}", e);
            response.setReturnCode(ReturnCodeEnum.CODE_1006.getCode());
            response.setMessage("密码错误");
        } catch (Exception e) {
            log.error("登录API异常: {}", e);
            response.setReturnCode(ReturnCodeEnum.CODE_1004.getCode());
            response.setMessage(e.getMessage());
        }
        log.info("登录API返回信息: {}", JsonUtil.objectToJson(response));
        return response;
    }


}

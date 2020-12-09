package cn.xmp.modules.security.shiro.controller;

import cn.xmp.modules.security.shiro.util.ClassUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenwei on 2017/6/5.
 * <p>
 * project name: security-admin-provider
 */
@RestController
@Slf4j
@Data
public class RequirePermissionController {
    private String permissionPackage;

    public RequirePermissionController(String permissionPackage) {
        this.permissionPackage = permissionPackage;
    }

    @RequestMapping("/getPermissions")
    @ResponseBody
    public Map<String, String> getPermissions() {
        log.info("------getPermissions-------");
        Map<String, String> pers = new HashMap<>();
        pers = ClassUtil.getPermissions(permissionPackage, true);
        return pers;
    }


}

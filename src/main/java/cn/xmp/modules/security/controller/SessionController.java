package cn.xmp.modules.security.controller;

import cn.xmp.modules.security.entity.ActiveUser;
import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import cn.xmp.modules.security.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@RestController
@RequestMapping("session")
@RequiredArgsConstructor
public class SessionController {

    private final ISessionService sessionService;

    @GetMapping("list")
//    @RequiresPermissions("online:view")
    public BaseResponse list(String username) throws Exception{
        BaseResponse response = new BaseResponse();
        List<ActiveUser> list = sessionService.list(username);
        Map<String, Object> data = new HashMap<>(2);
        data.put("rows", list);
        data.put("total", CollectionUtils.size(list));
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
        response.setDataInfo(data);
        return response;
    }

    @GetMapping("delete/{id}")
//    @RequiresPermissions("user:kickout")
    public BaseResponse forceLogout(@PathVariable String id) {
        sessionService.forceLogout(id);
        return BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1000.getCode());
    }
}

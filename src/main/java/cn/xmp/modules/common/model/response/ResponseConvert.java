package cn.xmp.modules.common.model.response;

import cn.xmp.modules.common.enums.ReturnCodeEnum;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
public class ResponseConvert {
    public ResponseConvert() {
    }

    public static BaseResponse convert(boolean back) {
        BaseResponse response = new BaseResponse();
        if (back) {
            response.setReturnCode(ReturnCodeEnum.CODE_1000.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1000.getValue());
        } else {
            response.setReturnCode(ReturnCodeEnum.CODE_1005.getCode());
            response.setMessage(ReturnCodeEnum.CODE_1005.getValue());
        }

        return response;
    }
}


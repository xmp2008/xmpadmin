package cn.xmp.moses.validator.exception;

import cn.xmp.moses.controller.IndexController;
import cn.xmp.moses.enums.ReturnCodeEnum;
import cn.xmp.moses.model.response.BackResponseUtil;
import cn.xmp.moses.model.response.BaseResponse;
import cn.xmp.moses.validator.AttributeValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
@Slf4j
@ControllerAdvice(assignableTypes = {IndexController.class})
public class AttributeValidatorExceptionHandler {
    @ExceptionHandler(AttributeValidatorException.class)
    @ResponseBody
    public BaseResponse handleValidationExceptions(
            AttributeValidatorException ex) {
        BaseResponse response ;
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
        log.error(ex.getMessage());
        ex.printStackTrace();
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1006.getCode());
        response.setMessage(ex.getMessage());
        return response;
//        return BaseResponse.builder()
//                .returnCode(ReturnCodeEnum.CODE_1005.getCode())
//                .message(ex.getBindingResult().getFieldError().getDefaultMessage())
//                .build();
    }
}

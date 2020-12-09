package cn.xmp.modules.common.validator.exception;

import cn.xmp.modules.common.enums.ReturnCodeEnum;
import cn.xmp.modules.common.model.response.BackResponseUtil;
import cn.xmp.modules.common.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
@ControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        BaseResponse response;
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
        log.error(ex.getMessage());
        ex.printStackTrace();
        response = BackResponseUtil.getBaseResponse(ReturnCodeEnum.CODE_1005.getCode());
        response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return response;
//        return BaseResponse.builder()
//                .returnCode(ReturnCodeEnum.CODE_1005.getCode())
//                .message(ex.getBindingResult().getFieldError().getDefaultMessage())
//                .build();
    }
}

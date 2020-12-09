package cn.xmp.modules.common.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
@Data
//@SuperBuilder
public class Response implements Serializable {
    protected Integer returnCode;
    protected String message;
}


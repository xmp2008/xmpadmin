package cn.xmp.moses.model.response;

import lombok.Data;

import java.io.Serializable;

//import javax.persistence.MappedSuperclass;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
@Data
//@MappedSuperclass
//@SuperBuilder
//@AllArgsConstructor
//@NoArgsConstructor
public class BaseResponse<T> extends Response implements Serializable {
    protected Integer returnCode;
    protected String message;
    protected T dataInfo;

}

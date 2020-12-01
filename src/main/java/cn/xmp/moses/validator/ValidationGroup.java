package cn.xmp.moses.validator;

import javax.validation.groups.Default;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/18
 */
public interface ValidationGroup {
    interface Create extends Default {}
    interface Update extends Default {}
}

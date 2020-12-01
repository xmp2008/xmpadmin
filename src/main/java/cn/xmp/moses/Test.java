package cn.xmp.moses;


import cn.vpclub.moses.utils.common.DateUtil;

import java.util.Date;

/**
 * <p>
 * <p>
 *
 * @author xiemopeng
 * @since 2020/11/27
 */
public class Test {
    public static void main(String[] args) {
        String curDay = DateUtil.formatDate(new Date(), "yyyyMMdd");
        curDay = curDay + "000000";
        System.out.println(curDay);
        String validDate = "20201201000000";
        //校验生效日期是不是次月
        if (Long.parseLong(curDay) < Long.valueOf(validDate)){
            System.out.println("adas");
        }
    }
}

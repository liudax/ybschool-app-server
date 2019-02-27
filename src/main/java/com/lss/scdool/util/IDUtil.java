package com.lss.scdool.util;

import java.util.UUID;

/**
 * @author Magic
 * @date 15:30 2018/3/31
 * @description
 */
public class IDUtil {

    public static String createId(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

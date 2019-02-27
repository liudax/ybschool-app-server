package com.lss.scdool.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用于表示该类不需要权限认证
 * @author Magic
 * @date 21:46 2018/4/3
 * @description
 */


@Retention(RetentionPolicy.RUNTIME)
public @interface NoAuthentication {
}

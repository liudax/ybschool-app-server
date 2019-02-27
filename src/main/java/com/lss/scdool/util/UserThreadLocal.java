package com.lss.scdool.util;


import com.lss.scdool.entity.UserInfo;

/**
 * @author Magic
 * @date 9:24 2018/4/2
 * @description
 */
public class UserThreadLocal extends ThreadLocal<UserInfo> {

    private static UserThreadLocal instance = new UserThreadLocal();

    @Override
    protected UserInfo initialValue() {
        return new UserInfo();
    }

    @Override
    public UserInfo get() {
        return super.get();
    }

    @Override
    public void set(UserInfo value) {
        super.set(value);
    }

    public static UserThreadLocal getInstance() {
        return instance;
    }
}

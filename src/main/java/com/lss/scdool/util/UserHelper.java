package com.lss.scdool.util;

/**
 * @author Magic
 * @date 16:51 2018/4/2
 * @description
 */
public class UserHelper {

    private static UserThreadLocal u = UserThreadLocal.getInstance();

    public static String getId() {
        return u.get().getId();
    }

    public static String getUserName() {
        return u.get().getUserName();
    }

    public static String getLoginName() {
        return u.get().getLoginName();
    }

    public static String getPassword() {
        return u.get().getPassword();
    }

    public static String getUserPhone() {
        return u.get().getUserPhone();
    }

    public static  String getWxOpenId() {
        return u.get().getWxOpenId();
    }

    public static String getSchoolId() {return u.get().getSchoolId();}

    public static String getStage() {
        return u.get().getStage();
    }

    public static String getIsEnabled() {
        return u.get().getIsEnabled();
    }

    public static String getBackup1() {
        return u.get().getBackup1();
    }

    public static String getBackup2() {
        return u.get().getBackup2();
    }

    public static String getLicenseType() {
        return u.get().getLicenseType();
    }

    public static String getUserRole(){
        return u.get().getUserRole();
    }
}

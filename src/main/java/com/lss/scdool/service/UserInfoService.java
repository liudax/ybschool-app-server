package com.lss.scdool.service;

import com.lss.scdool.entity.UserInfo;
import com.lss.scdool.util.SimpleResponse;

/**
 * @author Magic
 * @date 11:45 2018/4/15
 * @description
 */
public interface UserInfoService {

    UserInfo getUserByOpenId(String openId);

    SimpleResponse register(UserInfo info);

    SimpleResponse getTrainees();

    boolean checkOpenId();
}

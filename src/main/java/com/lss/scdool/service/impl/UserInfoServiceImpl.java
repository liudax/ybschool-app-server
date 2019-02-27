package com.lss.scdool.service.impl;

import com.lss.scdool.entity.UserInfo;
import com.lss.scdool.exception.ScException;
import com.lss.scdool.repository.UserInfoRepository;
import com.lss.scdool.service.UserInfoService;
import com.lss.scdool.util.IDUtil;
import com.lss.scdool.util.SimpleResponse;
import com.lss.scdool.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Magic
 * @date 11:45 2018/4/15
 * @description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository repository;


    @Override
    public UserInfo getUserByOpenId(String openId){
        List<UserInfo> list = repository.findByWxOpenId(openId);
        if(list!=null && list.size() != 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public SimpleResponse register(UserInfo info) {


        if(StringUtils.isEmpty(info.getWxOpenId())){
            throw new ScException("openId为空");
        }

        UserInfo user = getUserByOpenId(info.getWxOpenId());
        if(user != null){
            throw new ScException("已存在该微信用户，请勿重复注册");
        }

        if(!checkMobile(info.getUserPhone())){
            throw new ScException("该电话号码已注册");
        }

        if(StringUtils.isEmpty(info.getUserName())){
            throw new ScException("用户名为空");
        }
        if(StringUtils.isEmpty(info.getUserPhone())){
            throw new ScException("电话号码为空");
        }
        if(StringUtils.isEmpty(info.getSchoolId())){
            throw new ScException("驾校为空");
        }
        info.setId(IDUtil.createId());
        info.setUserRole("103");
         UserInfo retUser = repository.save(info);
        return new SimpleResponse(retUser);
    }

    @Override
    public SimpleResponse getTrainees() {
        String stage = UserHelper.getStage();
        String licenseType = UserHelper.getLicenseType();
        String schoolId = UserHelper.getSchoolId();
        List<UserInfo> list = repository.findByLicenseTypeAndStageAndUserRoleAndSchoolId(licenseType,
                stage,"102",schoolId);
        return new SimpleResponse(list);
    }


    @Override
    public boolean checkOpenId() {
        return false;
    }

    private boolean checkMobile(String phoneNum){
        List users = repository.findByUserPhone(phoneNum);
        return users.size()>0?false:true;
    }


}

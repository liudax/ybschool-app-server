package com.lss.scdool.repository;

import com.lss.scdool.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Magic
 * @date 11:32 2018/4/13
 * @description
 */
public interface UserInfoRepository extends  JpaRepository<UserInfo,String> {

    List<UserInfo> findByLicenseTypeAndStageAndUserRoleAndSchoolId(String licenseType,String stage,String userRole,String schoolId);

    List<UserInfo> findByWxOpenId(String wxOpenId);

    List<UserInfo> findByUserPhone(String userPhone);
}


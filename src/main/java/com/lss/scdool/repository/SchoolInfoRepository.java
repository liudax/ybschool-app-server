package com.lss.scdool.repository;

import com.lss.scdool.entity.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Magic
 * @date 11:32 2018/4/13
 * @description
 */
public interface SchoolInfoRepository extends  JpaRepository<SchoolInfo,String> {
}

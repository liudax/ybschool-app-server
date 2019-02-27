package com.lss.scdool.repository;

import com.lss.scdool.entity.TypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Magic
 * @date 11:39 2018/4/13
 * @description
 */
public interface TypeInfoRepository extends JpaRepository<TypeInfo,String>{

    TypeInfo findBySmallCodeAndTypeCode(String smallCode,String typeCode);

    List<TypeInfo> findByTypeCodeOrderBySmallCodeAsc(String typeCode);
}

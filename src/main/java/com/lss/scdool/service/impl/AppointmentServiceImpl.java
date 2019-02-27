package com.lss.scdool.service.impl;

import com.lss.scdool.entity.AppointmentInfo;
import com.lss.scdool.entity.TypeInfo;
import com.lss.scdool.exception.ScException;
import com.lss.scdool.repository.AppointmentInfoRepository;
import com.lss.scdool.repository.TypeInfoRepository;
import com.lss.scdool.service.AppointmentService;
import com.lss.scdool.util.IDUtil;
import com.lss.scdool.util.SimpleResponse;
import com.lss.scdool.util.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Magic
 * @date 12:35 2018/4/13
 * @description
 */

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;

    @Autowired
    private TypeInfoRepository typeInfoRepository;



    @Override
    public SimpleResponse getAppointmentForStudent() {
        String userId = UserHelper.getId();
        //String userId = "110cfb9dc08448c08d208ff458fa6a8e";
        List<Map<String,Object>> list = appointmentInfoRepository.findOwnAppointment(userId);
        return new SimpleResponse(list);
    }


    @Override
    @Transactional
    public synchronized SimpleResponse addAppointment(AppointmentInfo info) {
        info.setStage(UserHelper.getStage());
        info.setLicenseType(UserHelper.getLicenseType());
        info.setCoachId(UserHelper.getId());
        info.setSchoolId(UserHelper.getSchoolId());
        if(!checkTraineeHasTimes(info)){
            throw new ScException("该时段预约已满");
        }
        if(checkDate(info.getAppointmentDate())){
            throw new ScException(LocalDate.now().toString()+"的预约时间已结束");
        }

        List apps = appointmentInfoRepository.findByCoachIdAndAppointmentDate(info.getCoachId(),info.getAppointmentDate());
        if(apps.size()>0){
            throw new ScException("今天你已经预约过了");
        }
        info.setId(IDUtil.createId());
        appointmentInfoRepository.save(info);
        return SimpleResponse.SUCCESS;
    }

    @Override
    public boolean checkTraineeHasTimes(AppointmentInfo info) {

        int count = appointmentInfoRepository.getCountAppointmentByTrainee(info.getTraineeId(),
                info.getSchoolId(),info.getAppointmentDate(),info.getTimeInterval());
        String smallCode = "102".equals(info.getStage())?"sd2":"sd3";
        TypeInfo typeInfo = typeInfoRepository.findBySmallCodeAndTypeCode(info.getTimeInterval(),smallCode);
        return  Integer.valueOf(typeInfo.getBackup1())> count;
    }

    @Override
    public boolean checkDate(Date date) {
        LocalDate appointmentDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return LocalDate.now().isAfter(appointmentDate);
    }

    @Override
    public SimpleResponse getAllTimes() {
        String stage = UserHelper.getStage();
        String typeCode = "sd2";
        if("103".equals(stage)){
            typeCode = "sd3";
        }
        return new SimpleResponse(typeInfoRepository.findByTypeCodeOrderBySmallCodeAsc(typeCode));
    }
}

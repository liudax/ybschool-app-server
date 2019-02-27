package com.lss.scdool.service;

import com.lss.scdool.entity.AppointmentInfo;
import com.lss.scdool.util.SimpleResponse;

import java.util.Date;

/**
 * @author Magic
 * @date 12:35 2018/4/13
 * @description
 */
public interface AppointmentService {

    /**
     * 根据学员Id 获取自己的预约情况
     * @return
     */
    SimpleResponse getAppointmentForStudent();


    /**
     * 学员申请预约
     * @param info
     * @return
     */
    SimpleResponse addAppointment(AppointmentInfo info);

    /**
     * 检查教练某天某个时段的预约是否已满
     * @param info
     * @return
     */
    boolean checkTraineeHasTimes(AppointmentInfo info);

    /**
     * 检查预约时间是否已经结束
     * @param date
     * @return
     */
    boolean checkDate(Date date);

    /**
     * 根据驾照类型和科目类型获取所有时段
     * @return
     */
    SimpleResponse getAllTimes();

}

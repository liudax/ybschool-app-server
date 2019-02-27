package com.lss.scdool.web.controller;

import com.lss.scdool.entity.AppointmentInfo;
import com.lss.scdool.service.AppointmentService;
import com.lss.scdool.service.UserInfoService;
import com.lss.scdool.util.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Magic
 * @date 12:31 2018/4/14
 * @description
 */
@Api(tags = {"预约相关接口"})
@RestController
@RequestMapping("/app")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value="提交预约",notes = "需要预约相关信息，科目，驾照类型等")
    @PostMapping("/appointment")
    public SimpleResponse addAppointment( AppointmentInfo info){
        appointmentService.addAppointment(info);
        return SimpleResponse.SUCCESS;
    }

    @ApiOperation(value="获取所有时段",notes = "根据科目类别获取，服务端自取类型")
    @GetMapping("/appointment/times")
    public SimpleResponse getAppointmentAllTime(){
        return appointmentService.getAllTimes();
    }
    @ApiOperation(value="获取某个用户自己的所有预约",notes = "用户名服务端自取")
    @GetMapping("/appointment")
    public SimpleResponse getAppointmentByStudent(){
        return appointmentService.getAppointmentForStudent();
    }

    public SimpleResponse getAllSchool(){

        return new SimpleResponse();
    }


    @GetMapping("appointment/infos")
    @ApiOperation(value="获取预约所需信息",notes = "获取所有时段和教练")
    public SimpleResponse loadAboutAppointment(){
        SimpleResponse s1 = appointmentService.getAllTimes();
        SimpleResponse s2 = userInfoService.getTrainees();
        Map out = new HashMap(){{
            put("times",s1.getContent());
            put("trainees",s2.getContent());
        }};
        return new SimpleResponse(out);
    }
}

package com.lss.scdool.repository;

import com.lss.scdool.entity.AppointmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Magic
 * @date 11:32 2018/4/13
 * @description
 */
public interface AppointmentInfoRepository extends  JpaRepository<AppointmentInfo,String> {

    @Query(value = "SELECT count(1) FROM appointment_info where trainee_id = ?1 AND school_id = ?2 AND appointment_date = ?3 AND time_interval =?4",nativeQuery = true)
    int getCountAppointmentByTrainee( String traineeId,String schoolId ,Date appointmentDate,String timeInterval);


    @Query(value = "SELECT a.*,u1.user_name trainee_name,t1.small_name time_interval_desc,t2.small_name stage_desc FROM appointment_info a\n" +
            "LEFT JOIN user_info u1 ON a.trainee_id = u1.id\n" +
            "LEFT JOIN type_info t1 ON a.time_interval = t1.small_code AND t1.type_code like 'sd%'\n" +
            "LEFT JOIN type_info t2 ON a.time_interval = t2.small_code AND t1.type_code = 'kmlb'\n" +
            "WHERE a.coach_id = ?1 order by a.appointment_date desc",nativeQuery = true)
    List<Map<String,Object>> findOwnAppointment(String coachId);


    List<AppointmentInfo> findByCoachIdAndAppointmentDate(String coachId,Date appointmentDate);


}

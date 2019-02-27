package com.lss.scdool.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.lss.scdool.util.TransferUtil.toReplaceKeyLow;

/**
 * @author Magic
 * @date 17:08 2018/4/16
 * @description
 */

@Aspect
@Component
public class MapResultHandler {

    private static Pattern linePattern = Pattern.compile("_(\\w)");


    @Around("this(com.lss.scdool.repository.AppointmentInfoRepository)")
    public Object handlerMapResultMethod(ProceedingJoinPoint pjp) throws Throwable{
        Object object = pjp.proceed();
        if(object instanceof Map){
            return toReplaceKeyLow((Map)object);
        }

        if(object instanceof List){
            List<Map<String,Object>> retList =  new ArrayList<>();
            List list = (List)object;
            for (int i = 0;i<list.size();i++){
                Object item = list.get(i);
                if(item instanceof Map){
                    retList.add(toReplaceKeyLow((Map)item));
                }
            }
            if(retList.size()>0){
                return retList;
            }
        }
        return  object;
    }

}

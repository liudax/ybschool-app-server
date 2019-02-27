package com.lss.scdool.config;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author Magic
 * @date 18:40 2018/4/15
 * @description
 */

public class LongToDateConverter implements Converter<Long, Date> {

    @Override
    public Date convert(Long aLong) {

        try {
            Date date= new Date(aLong);
            return date;
        }catch (Exception e){
            return null;
        }
    }
}

package com.zgy.handle.stockservice.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: a4423
 * @date: 2021/2/17 23:04
 */
public class DateUtils {
    /**
     * 将date转换为localDate
     * @param date date值
     * @return
     */
    public static LocalDate convertDateToLocalDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        LocalDate localDate = LocalDate.of(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH));
        return localDate;
    }
}

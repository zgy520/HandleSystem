package com.zgy.handle.stockservice.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: a4423
 * @date: 2021/1/3 18:34
 */
//@ControllerAdvice
public class InitBinderHandler {
//    @InitBinder
    public void initBinder(WebDataBinder binder){
        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(LocalDate.class,new CustomDateEditor(dateFormat,true));
    }
}

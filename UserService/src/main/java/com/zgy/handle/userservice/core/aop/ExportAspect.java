package com.zgy.handle.userservice.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: a4423
 * @date: 2020/10/14 23:13
 */
@Aspect
@Component
@Slf4j
public class ExportAspect {
    @Pointcut("@annotation(com.zgy.excel.export.annotation.ExcelExport)")
    public void annotationPointCut(){}

    @Before(value = "annotationPointCut()")
    public void before(JoinPoint joinPoint){
        log.info("被拦截了 ！");
    }

    @PostConstruct
    public void init(){
        log.info("init exportAspect!");
    }
}

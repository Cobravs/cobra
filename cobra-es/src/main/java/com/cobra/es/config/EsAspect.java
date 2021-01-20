package com.cobra.es.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class EsAspect {


    /**
     * es annotation.
     */
    @Pointcut("@annotation(com.cobra.es.config.EsAnnotation)")
    public void esAnnotation() {
    }

    /**
     * Do before.
     */
    @Before("esAnnotation()")
    public void doBefore(final JoinPoint joinPoint) {
        EsAnnotation annotation = getDeclaredAnnotation(joinPoint);
        if (annotation == null) {
            return;
        }
        EsIndexEnum index = annotation.index();
        if (index == null) {
            return;
        }
        switch (index) {
            case CUSTOM:
                String pattern = annotation.pattern();
                if (pattern == null || pattern.equals("")) {
                    return;
                }
                EsConfig.setCallIndexNameSuffix(pattern);
                return;
            case NEXT_MONTH:
                EsConfig.setNextCallIndexNameSuffix();
                return;
            default:
                EsConfig.setCurrentCallIndexNameSuffix();
                return;
        }
    }


    @After("esAnnotation()")
    public void doAfter() {
        EsConfig.EsThreadLocal.destroy();
    }

    public EsAnnotation getDeclaredAnnotation(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        String methodName = joinPoint.getSignature().getName();
        if (null != methods && 0 < methods.length) {
            for (Method met : methods) {
                EsAnnotation es = met.getAnnotation(EsAnnotation.class);
                if (null != es && methodName.equals(met.getName())) {
                    return es;
                }
            }
        }
        return null;
    }
}

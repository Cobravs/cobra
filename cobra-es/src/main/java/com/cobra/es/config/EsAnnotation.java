package com.cobra.es.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsAnnotation {

    EsIndexEnum index() default EsIndexEnum.CURRENT_MONTH;

    String pattern() default "";
}

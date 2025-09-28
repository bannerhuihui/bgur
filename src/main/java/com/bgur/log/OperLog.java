package com.bgur.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {

    String operModule() default ""; // 操作模版

    String operType() default "";// 操作类型

    String operDesc() default "";// 操作说明

}

package com.example.demo.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @version 1.1.0
 * @author：cp
 * @time：2021-2-1
 * @Description: todo
 */
@Component
@Aspect
public class MyAspect {
    @Before("execution(* * (..))")
    public void before(){

    }
}

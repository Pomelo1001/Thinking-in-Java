package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @version 1.1.0
 * @author：cp
 * @time：2021-2-1
 * @Description: todo
 */
@Component
public class A {
    @Autowired
    private B b;
    public final void  test(){
        b.test();
    }
}

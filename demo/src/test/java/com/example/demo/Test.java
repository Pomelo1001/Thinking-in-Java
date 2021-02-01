package com.example.demo;

import com.example.demo.common.A;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @version 1.1.0
 * @author：cp
 * @time：2021-2-1
 * @Description:
 * 因为我们调用“代理对象.final方法()”是属于编译期绑定，所以会抛出如上的空指针异常。也就是此问题还是因为对象与方法的绑定问题造成的
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Test.class);
        A a = ctx.getBean(A.class);
        a.test();
    }
}

package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author：cp
 * @time：2021-2-27
 * @Description: 循环依赖测试
 */
public class CircularDependenceSingletonTest {
    public static final Log log = LogFactory.getLog(CircularDependenceSingletonTest.class);

   /* @Test
    private void test() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext();
        applicationContext.register(CircularDependenceSingletonTest.Config.class);
        applicationContext.refresh();

        log.info(applicationContext.getBean(CircularDependenceSingletonTest.A.class));
        log.info(applicationContext.getBean(CircularDependenceSingletonTest.B.class));
        log.info(applicationContext.getBean(CircularDependenceSingletonTest.C.class));

        log.info("-A--------");
        CircularDependenceSingletonTest.A a = applicationContext.getBean(CircularDependenceSingletonTest.A.class);
        log.info(a);
        log.info(a.b);
        log.info("-B--------");
        CircularDependenceSingletonTest.B b = applicationContext.getBean(CircularDependenceSingletonTest.B.class);
        log.info(b);
        log.info(b.c);
        log.info("-C--------");
        CircularDependenceSingletonTest.C c = applicationContext.getBean(CircularDependenceSingletonTest.C.class);
        log.info(c);
        log.info(c.a);
    }*/

    @Configuration
    @Import(CircularDependenceSingletonTest.AbcImportSelector.class)
    public static class Config {
    }

    public static class AbcImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{
                    CircularDependenceSingletonTest.A.class.getName(),
                    CircularDependenceSingletonTest.B.class.getName(),
                    CircularDependenceSingletonTest.C.class.getName()};
        }
    }


    @Component
    public static class A {
        @Autowired
        B b;
    }

    @Component
    public static class B {
        @Autowired
        C c;
    }

    @Component
    public static class C {
        @Autowired
        A a;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext();
        applicationContext.register(CircularDependenceSingletonTest.Config.class);
        applicationContext.refresh();

        log.info(applicationContext.getBean(CircularDependenceSingletonTest.A.class));
        log.info(applicationContext.getBean(CircularDependenceSingletonTest.B.class));
        log.info(applicationContext.getBean(CircularDependenceSingletonTest.C.class));

        log.info("-A--------");
        CircularDependenceSingletonTest.A a = applicationContext.getBean(CircularDependenceSingletonTest.A.class);
        log.info(a);
        log.info(a.b);
        log.info("-B--------");
        CircularDependenceSingletonTest.B b = applicationContext.getBean(CircularDependenceSingletonTest.B.class);
        log.info(b);
        log.info(b.c);
        log.info("-C--------");
        CircularDependenceSingletonTest.C c = applicationContext.getBean(CircularDependenceSingletonTest.C.class);
        log.info(c);
        log.info(c.a);
    }
}

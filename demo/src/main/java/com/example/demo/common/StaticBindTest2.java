package com.example.demo.common;

/**
 * @author：cp
 * @time：2021-2-1
 * @Description: 编译期绑定
 */
public class StaticBindTest2 {
    static class A {
        public void hello(Number i) {
            System.out.println("Number");
        }
        public void hello(Integer i) {
            System.out.println("Integer");
        }
        public void hello(Long i) {
            System.out.println("Long");
        }
    }
    public static void main(String[] args) {
        A a = new A();
        Number i = Integer.valueOf(1);
        Number l = Long.valueOf(1L);
        a.hello(i);
        a.hello(l);
    }
}

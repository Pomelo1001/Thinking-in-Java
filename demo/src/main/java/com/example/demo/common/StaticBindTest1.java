package com.example.demo.common;

/**
 * @author：cp
 * @time：2021-2-1
 * @Description: 编译期绑定
 */
public class StaticBindTest1 {
    static class A{
        public int i = 1;
         public static void hello(){
             System.out.println("1");
         }
    }
    static class B extends A{
        public int i = 2;
        public static void hello(){
            System.out.println("2");
        }

    }

    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.i);
        A.hello();
    }

}

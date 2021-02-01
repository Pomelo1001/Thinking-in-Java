package com.example.demo.common;

/**
 * @author：cp
 * @time：2021-2-1
 * @Description: todo
 */
public class DynamicBindTest {
    static class A {
        public void hello() {
            System.out.println("a");
        }
    }
    static class B extends A {
        public void hello() {
            System.out.println("b");
        }
    }

    public static void main(String[] args) {
        A a = new B();
        a.hello();
    }
}

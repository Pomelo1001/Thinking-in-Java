package com.example.demo.common;

/**
 * @author：cp
 * @time：2021-2-1
 * @Description: 编译期绑定
 * 但是我们知道如果是基于CGLIB的代理：
 * final的类不能生成代理对象；因为final的类不能生成代理对象；
 * final的方法不能被代理；但是还是能生成代理对象的；
 * 在我们的示例里，A的test方法是无法被代理的，但是A还是会生成一个代理对象（因为我们的切入点是execution(* *(..))，还是可以对如toString()之类的方法代理的）：
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

package com.example.demo.common;

/**
 * @author：cp
 * @time：2021-2-1
 * @Description: "a.accept(v)"，根据Element类型和Visitor类型来决定调用的是哪个方法。
 */
public class DoubleDispatchTest {
    interface Element {
        void accept(Visitor v);
    }

    static class AElement implements Element {
        public void accept(Visitor v) {
            v.visit(this);
        }
    }

    static class BElement implements Element {
        public void accept(Visitor v) {
            v.visit(this);
        }
    }


    interface Visitor {
        void visit(AElement aElement);

        void visit(BElement bElement);
    }

    static class Visitor1 implements Visitor {
        public void visit(AElement aElement) {
            System.out.println("1A");
        }

        public void visit(BElement bElement) {
            System.out.println("1B");
        }
    }

    static class Visitor2 implements Visitor {
        public void visit(AElement aElement) {
            System.out.println("2A");
        }

        public void visit(BElement bElement) {
            System.out.println("2B");
        }
    }

    public static void main(String[] args) {
        Element a = new AElement();
        Element b = new BElement();
        Visitor v1 = new Visitor1();
        Visitor v2 = new Visitor2();
        a.accept(v1);
        a.accept(v2);
        b.accept(v1);
        b.accept(v2);
    }

}

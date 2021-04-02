package com.example.demo.cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: todo
 */
public class Memorize1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memorize1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) {
        V result = cache.get(arg);
        if (result == null) {
            try {
                result = c.compute(arg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) {
        Memorize1<String, BigInteger> memorize1 = new Memorize1<>(new ExpensiveFuntion());
        System.out.println(memorize1.compute("5"));
    }
}

package com.example.demo.cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: ConcurrentHashMap
 */
public class Memorize2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorize2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public  V compute(A arg) throws InterruptedException {
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

    public static void main(String[] args) throws InterruptedException {
        Memorize2<String, BigInteger> memorize1 = new Memorize2<>(new ExpensiveFuntion());
        System.out.println(memorize1.compute("5"));
    }
}

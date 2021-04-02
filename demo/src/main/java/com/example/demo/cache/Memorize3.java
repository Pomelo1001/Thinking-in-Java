package com.example.demo.cache;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: futuretask
 */
public class Memorize3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorize3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public  V compute(A arg) throws Exception {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg,ft);
            ft.run();//这里将调用c.compute()
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw new Exception(e.getCause());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Memorize3<String, BigInteger> memorize1 = new Memorize3<>(new ExpensiveFuntion());
//        System.out.println(memorize1.compute("5"));
    }
}

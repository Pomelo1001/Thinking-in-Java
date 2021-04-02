package com.example.demo.cache;

import java.math.BigInteger;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: futuretask
 */
public class Memorize<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memorize(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws Exception {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            cache.putIfAbsent(arg, ft); //原子方法
            if (f == null) {
                f = ft;
                ft.run();//这里将调用c.compute()
            }
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new Exception(e.getCause());
        }
    }

    public static void main(String[] args) throws Exception {
        Memorize<String, BigInteger> memorize1 = new Memorize<>(new ExpensiveFuntion());
//        System.out.println(memorize1.compute("5"));
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {

        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}

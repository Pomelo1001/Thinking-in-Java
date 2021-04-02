package com.example.demo.executor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: todo
 */
public class PrimeGenerator implements Runnable {
    private volatile boolean cancelled;
    private final List<BigInteger> primes = new ArrayList<>();

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }
    public void cancel(){
        cancelled = true;
    }
    public synchronized List<BigInteger> get(){
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            primeGenerator.cancel();
        }
        System.out.println(primeGenerator.get());
    }
}

package com.example.demo.cache;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: todo
 */
public class ExpensiveFuntion implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //模拟长时间耗时操作
        TimeUnit.SECONDS.sleep(5);
        return new BigInteger(arg);
    }
}

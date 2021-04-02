package com.example.demo.cache;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: todo
 */
public interface Computable<A, V> {
    V compute(A arg) throws Exception;
}

package com.example.demo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：cp
 * @time：2021-3-16
 * @Description: 数组实现lru缓存淘汰算法
 */
public class ArrLRU<T> {
    //默认容量
    private static final int DEFALUT_CAPACITY = (1 << 3);
    private T[] holder;//存储元素的数组
    private int count;//元素个数
    //存储元素及其位置的对应关系
    private Map<T, Integer> cache;

    public ArrLRU() {
        this.holder = (T[]) new Object[DEFALUT_CAPACITY];
        this.count = 0;
        this.cache = new HashMap<>(DEFALUT_CAPACITY);
    }

    private void offer(T target) {
        Integer location = cache.get(target);
        if (location == null) {//缓存不存在该元素
            if (count == DEFALUT_CAPACITY) {
                //缓存已满,移除缓存元素，加入数组更新缓存
                T t = holder[--count];
                cache.remove(t);
                removeAndCache(t, count);
            } else {
                //不存在该元素、缓存未满
                putAbsentInCache(target,count);
            }
        } else {//已存在
            update(target,location);

        }
    }

    private void update(T target, Integer location) {
        Integer index = cache.get(target);
        rightShift(index);
        holder[0] = target;
        cache.put(target,0);
    }

    private void putAbsentInCache(T t, int end) {
        rightShift(count);
        holder[0] = t;
        cache.put(t, 0);
        count++;
    }

    private void removeAndCache(T t, int count) {
        rightShift(count);//t位置之前元素向右移动
        holder[0] = t;
        cache.put(t, 0);
        count++;
    }

    private void rightShift(int count) {
        for (int i = count - 1; i >= 0; i--) {
            holder[i + 1] = holder[i];
            cache.put(holder[i], i + 1);
        }
    }

    public static void main(String[] args) {
        ArrLRU lru = new ArrLRU<Integer>();
        lru.offer(Integer.valueOf(1));
        lru.offer(Integer.valueOf(2));
        lru.offer(Integer.valueOf(3));
        lru.offer(Integer.valueOf(4));
        lru.offer(Integer.valueOf(3));
        System.out.println(Arrays.toString(lru.holder));
    }

}

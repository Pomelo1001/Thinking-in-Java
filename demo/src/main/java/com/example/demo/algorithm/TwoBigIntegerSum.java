package com.example.demo.algorithm;

import java.math.BigInteger;

/**
 * @author cp
 * @Time 2021-6-16
 * @Des 两个大数相加(Java)
 * 1、是整数；
 * 2、两个数无限大，long都装不下；
 * 3、不能用BigInteger；
 * 4、不能用任何包装类提供的运算方法；
 * 5、两个数都是以字符串的方式提供。
 * 思路：
 * 字符串逐位相加，需要进位则进位处理，考虑两个问题：
 * 1、char怎么转换为integer, 减去'0'即可
 * 2、怎么处理对应位相加?反转字符串相加，正确处理进位即可，
 * 这样个位对应个位，十位对应十位，剩余的直接追加
 */
public class TwoBigIntegerSum {
    public String twoBigIntegerSum(String str1, String str2) {
        if (str1 == null || "".equals(str1)) {
            return str2;
        }
        if (str2 == null || "".equals(str2)) {
            return str1;
        }
        StringBuilder sb = new StringBuilder();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int index1 = str1.length() - 1;
        int index2 = str2.length() - 1;
        //缓存是否需要进位
        boolean carry = false;

        while (index1 >= 0 && index2 >= 0) {
            char cur1 = arr1[index1];
            char cur2 = arr2[index2];
            int sum = cur1 - '0' + cur2 - '0';
            sum = carry ? sum + 1 : sum;
            carry = sum >= 10 ? true : false;

            sb.append((char) ((sum % 10) + '0'));

            index1--;
            index2--;
        }

        //处理剩余元素
        while (carry || index1 >= 0 || index2 >= 0) {
            if (index1 >= 0) {
                int sum = arr1[index1] - '0' + (carry ? 1 : 0);
                carry = sum >= 10 ? true : false;
                sb.append((char) ((sum % 10) + '0'));
                index1--;
            }else if (index2 >= 0) {
                int sum = arr2[index2] - '0' + (carry ? 1 : 0);
                carry = sum >= 10 ? true : false;
                sb.append((char) ((sum % 10) + '0'));
                index2--;
            }else {
                sb.append('1');
                carry = false;
            }
        }
        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        String str1 = "11111112345678911111";
        String str2 = "2222";
        System.out.println(new TwoBigIntegerSum().twoBigIntegerSum(str1,str2));
        System.out.println(new BigInteger(str1).add(new BigInteger(str2)));
        String str3 = "999928";
        String str4 = "72";
        System.out.println(new TwoBigIntegerSum().twoBigIntegerSum(str3, str4));
        System.out.println(new BigInteger(str3).add(new BigInteger(str4)));

    }
}

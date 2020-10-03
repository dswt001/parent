package com.dk.parent.algorithm;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerPrint {

    static AtomicInteger threadNo = new AtomicInteger(1);

    public static void main(String[] args) {
        String s = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26";
        String[] aI = s.split(",");
//        char[] aI = s.toCharArray();
//        char[] aI = "1234567891011121314151617181920212223242526".toCharArray();
        char[] aC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        new Thread(() -> {
            for (String c : aI) {
                while (threadNo.get() != 1) {}
                System.out.print(c);
                threadNo.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                while (threadNo.get() != 2) {}
                System.out.print(c);
                threadNo.set(1);
            }
        }, "t2").start();
    }
}
